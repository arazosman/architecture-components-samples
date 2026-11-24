/*
 * Copyright 2026, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigationsample

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.navigationsample.ui.AppNavDisplay
import com.example.android.navigationsample.ui.InGameRoute
import com.example.android.navigationsample.ui.LeaderboardRoute
import com.example.android.navigationsample.ui.MatchRoute
import com.example.android.navigationsample.ui.RegisterRoute
import com.example.android.navigationsample.ui.TitleRoute
import com.example.android.navigationsample.ui.UserProfileRoute
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppNavDisplayTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun leaderboardFlow_navigatesToSelectedUserProfile() {
        val backStack = NavBackStack<NavKey>(TitleRoute)
        composeTestRule.setContent {
            AppNavDisplay(backStack = backStack)
        }

        composeTestRule.onNodeWithText("Trivia!").assertIsDisplayed()
        composeTestRule.onNodeWithText("Leaderboards").performClick()

        assertThat(backStack.toList()).containsExactly(TitleRoute, LeaderboardRoute).inOrder()
        composeTestRule.onNodeWithText("Ly").performClick()

        assertThat(backStack.toList())
            .containsExactly(TitleRoute, LeaderboardRoute, UserProfileRoute("Ly"))
            .inOrder()
        composeTestRule.onNodeWithText("Ly").assertIsDisplayed()
    }

    @Test
    fun gameOverFlow_popsInGameAndReturnsToMatchOnRetry() {
        val backStack = NavBackStack<NavKey>(TitleRoute)
        composeTestRule.setContent {
            AppNavDisplay(backStack = backStack)
        }

        composeTestRule.onNodeWithText("Play").performClick()
        composeTestRule.onNodeWithText("sign up").performScrollTo().performClick()
        composeTestRule.onNodeWithText("Start").performClick()
        composeTestRule.onNodeWithText("22").performClick()

        composeTestRule.onNodeWithText("Game Over").assertIsDisplayed()
        assertThat(backStack).doesNotContain(InGameRoute)

        composeTestRule.onNodeWithText("Try Again?").performClick()
        composeTestRule.onNodeWithText("VS").assertIsDisplayed()
        assertThat(backStack.toList())
            .containsExactly(TitleRoute, RegisterRoute, MatchRoute)
            .inOrder()
    }

    @Test
    fun winnerFlow_supportsNextMatchAndPopToTitleForLeaderboards() {
        val backStack = NavBackStack<NavKey>(TitleRoute)
        composeTestRule.setContent {
            AppNavDisplay(backStack = backStack)
        }

        composeTestRule.onNodeWithText("Play").performScrollTo().performClick()
        composeTestRule.onNodeWithText("sign up").performScrollTo().performClick()
        composeTestRule.onNodeWithText("Start").performScrollTo().performClick()
        composeTestRule.onNodeWithText("42").performScrollTo().performClick()

        composeTestRule.onNodeWithText("Congratulations!").assertIsDisplayed()

        composeTestRule.onNodeWithText("Next Match").performScrollTo().performClick()
        assertThat(backStack.toList())
            .containsExactly(TitleRoute, RegisterRoute, MatchRoute)
            .inOrder()

        composeTestRule.onNodeWithText("Start").performScrollTo().performClick()
        composeTestRule.onNodeWithText("42").performScrollTo().performClick()
        composeTestRule.onNodeWithText("Leaderboards").performScrollTo().performClick()

        assertThat(backStack.toList())
            .containsExactly(TitleRoute, LeaderboardRoute)
            .inOrder()
        composeTestRule.onNodeWithText("Flo").assertIsDisplayed()
    }

    @Test
    fun topBar_doesNotOverlapScreenContent() {
        val backStack = NavBackStack<NavKey>(LeaderboardRoute)
        composeTestRule.setContent {
            AppNavDisplay(backStack = backStack)
        }

        val topBarBounds = composeTestRule.onNodeWithText("Navigation Sample").getUnclippedBoundsInRoot()
        val firstRowBounds = composeTestRule.onNodeWithText("Flo").getUnclippedBoundsInRoot()
        assertThat(firstRowBounds.top).isAtLeast(topBarBounds.bottom)
    }
}
