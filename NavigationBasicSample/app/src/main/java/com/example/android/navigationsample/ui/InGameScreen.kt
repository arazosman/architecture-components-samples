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

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android.navigationsample.R

private val InGameBackgroundColor = Color(0xFF7DFFFF)

private data class AnswerOption(
    @param:StringRes val textRes: Int,
    val isCorrect: Boolean,
)

private val answerOptions = listOf(
    AnswerOption(textRes = R.string.answer_1, isCorrect = false),
    AnswerOption(textRes = R.string.answer_2, isCorrect = false),
    AnswerOption(textRes = R.string.answer_3, isCorrect = true),
    AnswerOption(textRes = R.string.answer_4, isCorrect = false),
)

/**
 * Shows a trivia question and four answer options, invoking the corresponding outcome callback.
 */
@Composable
fun InGameScreen(
    onCorrectAnswer: () -> Unit,
    onWrongAnswer: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(InGameBackgroundColor)
            .verticalScroll(rememberScrollState())
            .navigationBarsPadding()
            .padding(horizontal = 8.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.image_category_tvmovies_raster),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(width = 362.dp, height = 225.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.question_1),
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 36.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 26.dp),
        )

        Spacer(modifier = Modifier.height(32.dp))

        answerOptions.forEach { option ->
            val onSelected = if (option.isCorrect) onCorrectAnswer else onWrongAnswer
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp, vertical = 4.dp)
                    .clickable(onClick = onSelected),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = false,
                    onCheckedChange = { onSelected() },
                )
                Text(
                    text = stringResource(option.textRes),
                    color = Color.Black,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun InGameScreenPreview() {
    InGameScreen(
        onCorrectAnswer = {},
        onWrongAnswer = {},
    )
}
