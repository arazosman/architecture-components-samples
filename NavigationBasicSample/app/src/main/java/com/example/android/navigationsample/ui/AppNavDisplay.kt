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

package com.example.android.navigationsample.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.android.navigationsample.R
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoute : NavKey

@Serializable
data object TitleRoute : AppRoute

@Serializable
data object RegisterRoute : AppRoute

@Serializable
data object LeaderboardRoute : AppRoute

@Serializable
data object MatchRoute : AppRoute

@Serializable
data object InGameRoute : AppRoute

@Serializable
data object ResultsWinnerRoute : AppRoute

@Serializable
data object GameOverRoute : AppRoute

@Serializable
data class UserProfileRoute(val userName: String) : AppRoute

/**
 * Hosts the sample's Compose destinations using Navigation 3's [NavDisplay] and a saveable
 * [NavBackStack] rooted at [TitleRoute].
 */
@Composable
fun AppNavDisplay() = AppNavDisplay(backStack = rememberNavBackStack(TitleRoute))

/**
 * Renders [NavDisplay] inside a [Scaffold] backed by the caller-provided [backStack].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavDisplay(backStack: NavBackStack<NavKey>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.app_name)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.colorPrimary),
                    titleContentColor = Color.White,
                ),
            )
        },
    ) { innerPadding ->
        val topPadding = PaddingValues(top = innerPadding.calculateTopPadding())
        NavDisplay(
            backStack = backStack,
            modifier = Modifier
                .fillMaxSize()
                .padding(topPadding)
                .consumeWindowInsets(topPadding),
            onBack = { backStack.removeLastOrNull() },
            entryProvider = entryProvider {
                entry<TitleRoute> {
                    TitleScreen(
                        onPlayClick = { backStack.add(RegisterRoute) },
                        onLeaderboardClick = { backStack.add(LeaderboardRoute) },
                    )
                }
                entry<RegisterRoute> {
                    RegisterScreen(
                        onSignUpClick = { backStack.add(MatchRoute) },
                    )
                }
                entry<LeaderboardRoute> {
                    LeaderboardScreen(
                        onUserClick = { userName -> backStack.add(UserProfileRoute(userName)) },
                    )
                }
                entry<MatchRoute> {
                    MatchScreen(
                        onStartClick = { backStack.add(InGameRoute) },
                    )
                }
                entry<InGameRoute> {
                    InGameScreen(
                        onCorrectAnswer = {
                            backStack.popUpTo(MatchRoute)
                            backStack.add(ResultsWinnerRoute)
                        },
                        onWrongAnswer = {
                            backStack.popUpTo(MatchRoute)
                            backStack.add(GameOverRoute)
                        },
                    )
                }
                entry<ResultsWinnerRoute> {
                    ResultsWinnerScreen(
                        onNextMatchClick = { backStack.popUpTo(MatchRoute) },
                        onLeaderboardClick = {
                            backStack.popUpTo(TitleRoute)
                            backStack.add(LeaderboardRoute)
                        },
                    )
                }
                entry<GameOverRoute> {
                    GameOverScreen(
                        onTryAgainClick = { backStack.popUpTo(MatchRoute) },
                    )
                }
                entry<UserProfileRoute> { route ->
                    UserProfileScreen(userName = route.userName)
                }
            },
        )
    }
}

private fun MutableList<NavKey>.popUpTo(target: NavKey) {
    val targetIndex = lastIndexOf(target)
    if (targetIndex >= 0) {
        subList(targetIndex + 1, size).clear()
    }
}
