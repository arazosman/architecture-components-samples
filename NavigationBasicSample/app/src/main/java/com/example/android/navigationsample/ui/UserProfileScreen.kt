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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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

private val StatLabelColor = Color(0xFF757575)
private val StatDividerColor = Color(0xFFD8D8D8)

/**
 * Shows the profile card and statistics for [userName].
 */
@Composable
fun UserProfileScreen(
    userName: String,
) {
    val accentColor = colorResource(R.color.colorAccent)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(accentColor),
    ) {
        Image(
            painter = painterResource(R.drawable.avatar_6_raster),
            contentDescription = stringResource(R.string.profile_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 64.dp)
                .size(240.dp)
                .clip(CircleShape)
                .align(Alignment.TopCenter),
        )

        Surface(
            color = Color.White,
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(top = 16.dp, bottom = 24.dp, start = 16.dp, end = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = userName,
                    color = accentColor,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(12.dp))

                ProfileStatRow(
                    label = stringResource(R.string.rank),
                    value = stringResource(R.string.rank_1),
                )
                Spacer(modifier = Modifier.height(8.dp))
                ProfileStatRow(
                    label = stringResource(R.string.wins),
                    value = stringResource(R.string.wins_amount),
                )
                Spacer(modifier = Modifier.height(8.dp))
                ProfileStatRow(
                    label = stringResource(R.string.losses),
                    value = stringResource(R.string.losses_amount),
                )
            }
        }
    }
}

@Composable
private fun ProfileStatRow(
    label: String,
    value: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            color = StatLabelColor,
            fontSize = 18.sp,
        )
        HorizontalDivider(
            color = StatDividerColor,
            thickness = 2.dp,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
        )
        Text(
            text = value,
            color = StatLabelColor,
            fontSize = 13.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserProfileScreenPreview() {
    UserProfileScreen(userName = "Ali Connors")
}
