package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.ShahnamehRepository
import com.example.model.ScreenTab
import com.example.viewmodel.ShahnamehViewModel
import com.example.viewmodel.SubScreen
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("درخت‌نامه شاهنامه", appName)
    }

    @Test
    fun `verify repository content and models`() {
        assertEquals(7, ShahnamehRepository.labors.size)
        assertTrue(ShahnamehRepository.characters.isNotEmpty())
        assertTrue(ShahnamehRepository.stories.isNotEmpty())
        assertTrue(ShahnamehRepository.mapWaypoints.isNotEmpty())
        val rostam = ShahnamehRepository.characters.find { it.id == "rostam" }
        assertNotNull(rostam)
        assertEquals("رستم دستان (تَهمتَن)", rostam?.name)
    }

    @Test
    fun `verify view model tab and battle simulation transitions`() {
        val viewModel = ShahnamehViewModel()
        assertEquals(ScreenTab.GENEALOGY, viewModel.currentTab.value)
        viewModel.setTab(ScreenTab.TALES)
        assertEquals(ScreenTab.TALES, viewModel.currentTab.value)

        // Subscreen transition
        viewModel.openSubScreen(SubScreen.BATTLE_SIM)
        assertEquals(SubScreen.BATTLE_SIM, viewModel.currentSubScreen.value)

        // Battle actions
        val swordSkill = ShahnamehRepository.battleSkills.first()
        val initialBossHp = viewModel.battleState.value.bossHp
        viewModel.executeBattleSkill(swordSkill)
        assertTrue(viewModel.battleState.value.bossHp < initialBossHp)

        // Finisher strike
        viewModel.executeFinisherStrike()
        assertEquals(0, viewModel.battleState.value.bossHp)
        assertEquals(SubScreen.VICTORY_HEALING, viewModel.currentSubScreen.value)
    }
}
