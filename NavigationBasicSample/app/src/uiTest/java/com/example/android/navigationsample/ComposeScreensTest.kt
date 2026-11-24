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
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.navigationsample.ui.GameOverScreen
import com.example.android.navigationsample.ui.InGameScreen
import com.example.android.navigationsample.ui.LeaderboardScreen
import com.example.android.navigationsample.ui.MatchScreen
import com.example.android.navigationsample.ui.RegisterScreen
import com.example.android.navigationsample.ui.ResultsWinnerScreen
import com.example.android.navigationsample.ui.TitleScreen
import com.example.android.navigationsample.ui.UserProfileScreen
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComposeScreensTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun titleScreen_invokesNavigationCallbacks() {
        var playClicked = false
        var leaderboardClicked = false

        composeTestRule.setContent {
            TitleScreen(
                onPlayClick = { playClicked = true },
                onLeaderboardClick = { leaderboardClicked = true },
            )
        }

        composeTestRule.onNodeWithText("Play").performClick()
        assertThat(playClicked).isTrue()

        composeTestRule.onNodeWithText("Leaderboards").performClick()
        assertThat(leaderboardClicked).isTrue()
    }

    @Test
    fun registerScreen_invokesSignUpCallback() {
        var signUpClicked = false

        composeTestRule.setContent {
            RegisterScreen(onSignUpClick = { signUpClicked = true })
        }

        composeTestRule.onNodeWithText("sign up").performScrollTo().performClick()
        assertThat(signUpClicked).isTrue()
    }

    @Test
    fun leaderboardScreen_passesSelectedUserName() {
        var selectedUser: String? = null

        composeTestRule.setContent {
            LeaderboardScreen(onUserClick = { selectedUser = it })
        }

        composeTestRule.onNodeWithText("Flo").performClick()
        assertThat(selectedUser).isEqualTo("Flo")
    }

    @Test
    fun matchScreen_invokesStartCallback() {
        var startClicked = false

        composeTestRule.setContent {
            MatchScreen(onStartClick = { startClicked = true })
        }

        composeTestRule.onNodeWithText("Start").performClick()
        assertThat(startClicked).isTrue()
    }

    @Test
    fun inGameScreen_invokesCorrectAndWrongAnswerCallbacks() {
        var correctClicked = false
        var wrongClicked = false

        composeTestRule.setContent {
            InGameScreen(
                onCorrectAnswer = { correctClicked = true },
                onWrongAnswer = { wrongClicked = true },
            )
        }

        composeTestRule.onNodeWithText("22").performClick()
        assertThat(wrongClicked).isTrue()

        composeTestRule.onNodeWithText("42").performClick()
        assertThat(correctClicked).isTrue()
    }

    @Test
    fun resultsWinnerScreen_invokesNavigationCallbacks() {
        var nextMatchClicked = false
        var leaderboardClicked = false

        composeTestRule.setContent {
            ResultsWinnerScreen(
                onNextMatchClick = { nextMatchClicked = true },
                onLeaderboardClick = { leaderboardClicked = true },
            )
        }

        composeTestRule.onNodeWithText("Next Match").performClick()
        assertThat(nextMatchClicked).isTrue()

        composeTestRule.onNodeWithText("Leaderboards").performClick()
        assertThat(leaderboardClicked).isTrue()
    }

    @Test
    fun gameOverScreen_invokesTryAgainCallback() {
        var tryAgainClicked = false

        composeTestRule.setContent {
            GameOverScreen(onTryAgainClick = { tryAgainClicked = true })
        }

        composeTestRule.onNodeWithText("Try Again?").performClick()
        assertThat(tryAgainClicked).isTrue()
    }

    @Test
    fun userProfileScreen_displaysUserName() {
        composeTestRule.setContent {
            UserProfileScreen(userName = "Flo")
        }

        composeTestRule.onNodeWithText("Flo").assertIsDisplayed()
    }
}
