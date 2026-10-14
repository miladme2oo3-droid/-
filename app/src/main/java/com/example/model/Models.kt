package com.example.model

enum class ScreenTab(val title: String, val englishTitle: String) {
    GENEALOGY("شجره‌نامه", "Genealogy"),
    TALES("داستان‌ها", "Epic Tales"),
    MAP("نقشه اساطیری", "Mythic Map"),
    CHARACTERS("شخصیت‌ها", "Characters")
}

data class CharacterNode(
    val id: String,
    val name: String,
    val englishName: String,
    val title: String,
    val dynasty: String,
    val imageUrl: String,
    val badge: String = "",
    val badgeIcon: String = "",
    val quote: String = "",
    val father: String = "",
    val mother: String = "",
    val spouse: String = "",
    val children: String = "",
    val powerPercent: Int = 90,
    val farrPercent: Int = 90,
    val lifespan: String = "۱۵۰ سال",
    val steed: String = "اسب نجیب",
    val realm: String = "ایران‌زمین",
    val biography: String = "",
    val timelineHighlights: List<String> = emptyList()
)

data class LaborItem(
    val number: Int,
    val title: String,
    val subtitle: String,
    val tag: String,
    val location: String,
    val verse: String,
    val description: String,
    val keyToVictory: String,
    val ally: String = "رخش",
    val isCompleted: Boolean = true
)

data class Story(
    val id: String,
    val title: String,
    val subtitle: String,
    val chapter: String,
    val location: String,
    val versesCount: String,
    val readTime: String,
    val excerpt: String,
    val coupletLines: List<String>,
    val imageUrl: String,
    val tags: List<String>,
    val isRead: Boolean = false,
    val statusBadge: String = ""
)

data class ThematicCollection(
    val id: String,
    val title: String,
    val description: String,
    val countBadge: String,
    val category: String
)

data class MythicWaypoint(
    val id: String,
    val name: String,
    val label: String,
    val xPercent: Float,
    val yPercent: Float,
    val khanNumber: String,
    val isSelected: Boolean = false
)

data class BattleSkill(
    val id: String,
    val name: String,
    val cost: Int,
    val damage: Int,
    val heal: Int = 0,
    val description: String
)

data class BattleState(
    val heroHp: Int = 840,
    val heroMaxHp: Int = 1000,
    val heroFarr: Int = 92,
    val bossHp: Int = 320,
    val bossMaxHp: Int = 1200,
    val darkMagic: Int = 35,
    val turn: Int = 3,
    val secondsLeft: Int = 24,
    val latestLog: String = "رستم خنجر آبگون برکشید؛ دیو سپید غریو برآورد و خاره‌سنگ پرتاب کرد!",
    val logSpeaker: String = "رویداد رزم",
    val noshdaruUsed: Boolean = false,
    val oilUsed: Boolean = false,
    val isFinished: Boolean = false
)
