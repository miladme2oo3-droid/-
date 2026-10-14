package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ShahnamehRepository
import com.example.model.BattleSkill
import com.example.model.BattleState
import com.example.model.CharacterNode
import com.example.model.MythicWaypoint
import com.example.model.ScreenTab
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class SubScreen {
    NONE,
    HAFT_KHAN,
    BATTLE_SIM,
    VICTORY_HEALING
}

class ShahnamehViewModel : ViewModel() {

    private val _currentTab = MutableStateFlow(ScreenTab.GENEALOGY)
    val currentTab: StateFlow<ScreenTab> = _currentTab.asStateFlow()

    private val _currentSubScreen = MutableStateFlow(SubScreen.NONE)
    val currentSubScreen: StateFlow<SubScreen> = _currentSubScreen.asStateFlow()

    private val _selectedCharacter = MutableStateFlow(ShahnamehRepository.characters.first { it.id == "rostam" })
    val selectedCharacter: StateFlow<CharacterNode> = _selectedCharacter.asStateFlow()

    private val _isDossierExpanded = MutableStateFlow(true)
    val isDossierExpanded: StateFlow<Boolean> = _isDossierExpanded.asStateFlow()

    private val _selectedKhanNumber = MutableStateFlow(3)
    val selectedKhanNumber: StateFlow<Int> = _selectedKhanNumber.asStateFlow()

    private val _selectedWaypoint = MutableStateFlow(ShahnamehRepository.mapWaypoints.last())
    val selectedWaypoint: StateFlow<MythicWaypoint> = _selectedWaypoint.asStateFlow()

    private val _selectedEra = MutableStateFlow("کیانیان و خاندان سام")
    val selectedEra: StateFlow<String> = _selectedEra.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _characterFilter = MutableStateFlow("همه")
    val characterFilter: StateFlow<String> = _characterFilter.asStateFlow()

    private val _isPlayingNaqqali = MutableStateFlow(false)
    val isPlayingNaqqali: StateFlow<Boolean> = _isPlayingNaqqali.asStateFlow()

    private val _battleState = MutableStateFlow(BattleState())
    val battleState: StateFlow<BattleState> = _battleState.asStateFlow()

    private var timerJob: Job? = null

    init {
        startTurnTimer()
    }

    private fun startTurnTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                _battleState.update { current ->
                    if (current.secondsLeft > 1) {
                        current.copy(secondsLeft = current.secondsLeft - 1)
                    } else {
                        current.copy(secondsLeft = 30)
                    }
                }
            }
        }
    }

    fun setTab(tab: ScreenTab) {
        _currentTab.value = tab
        _currentSubScreen.value = SubScreen.NONE
    }

    fun openSubScreen(subScreen: SubScreen) {
        _currentSubScreen.value = subScreen
    }

    fun closeSubScreen() {
        _currentSubScreen.value = SubScreen.NONE
    }

    fun selectCharacter(character: CharacterNode) {
        _selectedCharacter.value = character
        _isDossierExpanded.value = true
    }

    fun toggleDossier() {
        _isDossierExpanded.value = !_isDossierExpanded.value
    }

    fun selectKhan(number: Int) {
        _selectedKhanNumber.value = number
    }

    fun selectWaypoint(waypoint: MythicWaypoint) {
        _selectedWaypoint.value = waypoint
    }

    fun setEra(era: String) {
        _selectedEra.value = era
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setCharacterFilter(filter: String) {
        _characterFilter.value = filter
    }

    fun toggleNaqqali() {
        _isPlayingNaqqali.value = !_isPlayingNaqqali.value
    }

    // Battle Actions
    fun executeBattleSkill(skill: BattleSkill) {
        val current = _battleState.value
        if (current.heroFarr < skill.cost) {
            _battleState.update {
                it.copy(
                    logSpeaker = "هشدار",
                    latestLog = "فرّ ایزدی کافی نیست! نیایش یا پدافند کن."
                )
            }
            return
        }

        val newFarr = (current.heroFarr - skill.cost).coerceAtLeast(0)
        var newBossHp = current.bossHp
        var newHeroHp = current.heroHp
        val speaker = "رستم"
        val logMsg: String

        if (skill.damage > 0) {
            newBossHp = (current.bossHp - skill.damage).coerceAtLeast(0)
            logMsg = when (skill.id) {
                "sword" -> "تیغ بران فرود آمد و ${skill.damage} آسیب به دیو وارد کرد!"
                "lasso" -> "کمند شست‌خم پهلوان، گردن و دست دیو را در کام کشید!"
                "rakhsh" -> "رخش شیهه‌زنان سم بر سنگ کوفت و تمرکز دیو درهم شکست!"
                else -> "${skill.name} اجرا گردید و ${skill.damage} آسیب وارد آورد."
            }
        } else if (skill.heal > 0) {
            newHeroHp = (current.heroHp + skill.heal).coerceAtMost(current.heroMaxHp)
            logMsg = "سپر ببر بیان ضربه دیو را دفع نمود و توان بازگشت (+${skill.heal})"
        } else {
            logMsg = "${skill.name} اجرا شد."
        }

        _battleState.update {
            it.copy(
                heroFarr = newFarr,
                bossHp = newBossHp,
                heroHp = newHeroHp,
                logSpeaker = speaker,
                latestLog = logMsg,
                turn = it.turn + 1
            )
        }
    }

    fun useNoshdaru() {
        val current = _battleState.value
        if (current.noshdaruUsed) return
        val healedHp = (current.heroHp + 300).coerceAtMost(current.heroMaxHp)
        _battleState.update {
            it.copy(
                heroHp = healedHp,
                noshdaruUsed = true,
                logSpeaker = "نوشدارو",
                latestLog = "جرعه‌ای از اکسیر شفا نوشیده شد؛ زخم‌ها التیام یافت (+۳۰۰ سلامت)."
            )
        }
    }

    fun useCaveOil() {
        val current = _battleState.value
        if (current.oilUsed) return
        _battleState.update {
            it.copy(
                oilUsed = true,
                logSpeaker = "روشنایی",
                latestLog = "روغن مقدس مشتعل شد؛ تاریکی وهم‌آور غار برای ۳ نوبت زدوده شد."
            )
        }
    }

    fun executeFinisherStrike() {
        _battleState.update {
            it.copy(
                bossHp = 0,
                isFinished = true,
                logSpeaker = "پیروزی خوان هفتم",
                latestLog = "«زدش بر زمین همچو یک کوهسار / جدا کرد سر از تن نابکار» جگر دیو سپید برای شفای کاووس در دست تهمتن است!"
            )
        }
        _currentSubScreen.value = SubScreen.VICTORY_HEALING
    }

    fun resetBattle() {
        _battleState.value = BattleState()
    }
}
