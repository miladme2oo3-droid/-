package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.model.ScreenTab
import com.example.ui.components.ShahnamehBottomNavBar
import com.example.ui.components.ShahnamehHeaderBar
import com.example.ui.screens.BattleSimulationScreen
import com.example.ui.screens.CharactersScreen
import com.example.ui.screens.EpicTalesScreen
import com.example.ui.screens.GenealogyScreen
import com.example.ui.screens.HaftKhanScreen
import com.example.ui.screens.MythicMapScreen
import com.example.ui.screens.VictoryScreen
import com.example.ui.theme.ShahnamehTheme
import com.example.viewmodel.ShahnamehViewModel
import com.example.viewmodel.SubScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShahnamehTheme {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    ShahnamehApp()
                }
            }
        }
    }
}

@Composable
fun ShahnamehApp(
    viewModel: ShahnamehViewModel = viewModel()
) {
    val currentTab by viewModel.currentTab.collectAsState()
    val currentSubScreen by viewModel.currentSubScreen.collectAsState()
    val selectedCharacter by viewModel.selectedCharacter.collectAsState()
    val isDossierExpanded by viewModel.isDossierExpanded.collectAsState()
    val selectedKhanNumber by viewModel.selectedKhanNumber.collectAsState()
    val selectedWaypoint by viewModel.selectedWaypoint.collectAsState()
    val selectedEra by viewModel.selectedEra.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val isPlayingNaqqali by viewModel.isPlayingNaqqali.collectAsState()
    val battleState by viewModel.battleState.collectAsState()

    // Handle back button for sub-screens and tab switching
    BackHandler(enabled = currentSubScreen != SubScreen.NONE || currentTab != ScreenTab.GENEALOGY) {
        if (currentSubScreen != SubScreen.NONE) {
            viewModel.closeSubScreen()
        } else if (currentTab != ScreenTab.GENEALOGY) {
            viewModel.setTab(ScreenTab.GENEALOGY)
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            if (currentSubScreen == SubScreen.NONE) {
                ShahnamehHeaderBar(
                    selectedEra = selectedEra,
                    onEraSelected = { viewModel.setEra(it) },
                    onSearchClick = { viewModel.setTab(ScreenTab.CHARACTERS) },
                    onBookmarkClick = { viewModel.setTab(ScreenTab.CHARACTERS) }
                )
            }
        },
        bottomBar = {
            if (currentSubScreen == SubScreen.NONE) {
                ShahnamehBottomNavBar(
                    currentTab = currentTab,
                    onTabSelected = { viewModel.setTab(it) }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedContent(
                targetState = currentSubScreen,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "SubScreenTransition"
            ) { subScreen ->
                when (subScreen) {
                    SubScreen.HAFT_KHAN -> {
                        HaftKhanScreen(
                            selectedKhanIndex = selectedKhanNumber,
                            onSelectKhan = { viewModel.selectKhan(it) },
                            onEnterBattleSim = { viewModel.openSubScreen(SubScreen.BATTLE_SIM) },
                            onBack = { viewModel.closeSubScreen() }
                        )
                    }
                    SubScreen.BATTLE_SIM -> {
                        BattleSimulationScreen(
                            battleState = battleState,
                            onExecuteSkill = { viewModel.executeBattleSkill(it) },
                            onUseNoshdaru = { viewModel.useNoshdaru() },
                            onUseOil = { viewModel.useCaveOil() },
                            onExecuteFinisher = { viewModel.executeFinisherStrike() },
                            onClose = { viewModel.closeSubScreen() }
                        )
                    }
                    SubScreen.VICTORY_HEALING -> {
                        VictoryScreen(
                            onGoToGenealogy = {
                                viewModel.closeSubScreen()
                                viewModel.setTab(ScreenTab.GENEALOGY)
                            },
                            onContinueStory = {
                                viewModel.closeSubScreen()
                                viewModel.setTab(ScreenTab.TALES)
                            },
                            onClose = { viewModel.closeSubScreen() }
                        )
                    }
                    SubScreen.NONE -> {
                        AnimatedContent(
                            targetState = currentTab,
                            transitionSpec = { fadeIn() togetherWith fadeOut() },
                            label = "TabContentTransition"
                        ) { tab ->
                            when (tab) {
                                ScreenTab.GENEALOGY -> {
                                    GenealogyScreen(
                                        selectedCharacter = selectedCharacter,
                                        isDossierExpanded = isDossierExpanded,
                                        onSelectCharacter = { viewModel.selectCharacter(it) },
                                        onToggleDossier = { viewModel.toggleDossier() },
                                        onOpenHaftKhan = { viewModel.openSubScreen(SubScreen.HAFT_KHAN) }
                                    )
                                }
                                ScreenTab.TALES -> {
                                    EpicTalesScreen(
                                        isPlayingNaqqali = isPlayingNaqqali,
                                        onToggleNaqqali = { viewModel.toggleNaqqali() },
                                        onOpenHaftKhan = { viewModel.openSubScreen(SubScreen.HAFT_KHAN) },
                                        onOpenGenealogy = { viewModel.setTab(ScreenTab.GENEALOGY) }
                                    )
                                }
                                ScreenTab.MAP -> {
                                    MythicMapScreen(
                                        selectedWaypoint = selectedWaypoint,
                                        onSelectWaypoint = { viewModel.selectWaypoint(it) },
                                        onOpenBattleSim = { viewModel.openSubScreen(SubScreen.BATTLE_SIM) },
                                        onOpenGenealogy = { viewModel.setTab(ScreenTab.GENEALOGY) }
                                    )
                                }
                                ScreenTab.CHARACTERS -> {
                                    CharactersScreen(
                                        searchQuery = searchQuery,
                                        onSearchQueryChange = { viewModel.setSearchQuery(it) },
                                        onSelectCharacter = {
                                            viewModel.selectCharacter(it)
                                        },
                                        onOpenGenealogy = { viewModel.setTab(ScreenTab.GENEALOGY) },
                                        onOpenHaftKhan = { viewModel.openSubScreen(SubScreen.HAFT_KHAN) }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
