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

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android.navigationsample.R

private data class LeaderboardEntry(
    val name: String,
    @param:DrawableRes val avatarRes: Int,
)

private val leaderboardEntries = listOf(
    LeaderboardEntry(name = "Flo", avatarRes = R.drawable.avatar_1_raster),
    LeaderboardEntry(name = "Ly", avatarRes = R.drawable.avatar_2_raster),
    LeaderboardEntry(name = "Jo", avatarRes = R.drawable.avatar_3_raster),
)

/**
 * Shows a static leaderboard list where selecting a row triggers navigation to that user's profile.
 */
@Composable
fun LeaderboardScreen(
    onUserClick: (userName: String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentPadding = WindowInsets.navigationBars.asPaddingValues(),
    ) {
        items(leaderboardEntries) { entry ->
            LeaderboardRow(
                entry = entry,
                onClick = { onUserClick(entry.name) },
            )
        }
    }
}

@Composable
private fun LeaderboardRow(
    entry: LeaderboardEntry,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(entry.avatarRes),
            contentDescription = stringResource(R.string.profile_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(69.dp)
                .clip(CircleShape),
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = entry.name,
                fontSize = 22.sp,
            )
            Text(
                text = stringResource(R.string.user_points),
                fontSize = 16.sp,
            )
        }

        Text(
            text = stringResource(R.string.user_rank),
            color = Color.Gray,
            fontSize = 24.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LeaderboardScreenPreview() {
    LeaderboardScreen(onUserClick = {})
}
