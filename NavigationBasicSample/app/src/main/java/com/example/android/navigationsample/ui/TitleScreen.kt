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

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android.navigationsample.R

/**
 * Shows the main title screen with navigation actions to start registration or view leaderboards.
 */
@Composable
fun TitleScreen(
    onPlayClick: () -> Unit,
    onLeaderboardClick: () -> Unit,
) {
    val accentColor = colorResource(R.color.colorAccent)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .navigationBarsPadding()
            .padding(horizontal = 8.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.trivia),
            color = accentColor,
            fontSize = 62.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )

        Image(
            painter = painterResource(R.drawable.image_category_entertainment_raster),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(width = 362.dp, height = 225.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = onPlayClick,
            border = BorderStroke(2.dp, accentColor),
            modifier = Modifier
                .width(144.dp)
                .height(48.dp),
        ) {
            Text(
                text = stringResource(R.string.play),
                color = accentColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = onLeaderboardClick) {
            Text(
                text = stringResource(R.string.leaderboards),
                color = accentColor,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TitleScreenPreview() {
    TitleScreen(
        onPlayClick = {},
        onLeaderboardClick = {},
    )
}
