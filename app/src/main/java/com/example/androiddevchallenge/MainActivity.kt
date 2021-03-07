/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.AndroidPlay
import com.example.androiddevchallenge.ui.CountDownCanvas
import com.example.androiddevchallenge.ui.CountDownText
import com.example.androiddevchallenge.ui.FilmRollCanvas
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                val countDownViewModel = remember { OldMovieCountDownViewModel() }
                OldMovieCountDown(modifier = Modifier.fillMaxSize(), viewModel = countDownViewModel)
            }
        }
    }
}

@Composable
fun OldMovieCountDown(modifier: Modifier, viewModel: OldMovieCountDownViewModel) {

    val countDownUiState = viewModel.uiState.collectAsState()

    MeasureCanvas(modifier, viewModel)

    var progressOffset by remember { mutableStateOf(0f) }
    var rollOffset by remember { mutableStateOf(0f) }

    Surface(color = MaterialTheme.colors.background) {

        if (countDownUiState.value is CountDownUiState.Finished) {
            AndroidPlay(
                modifier.clickable {
                    viewModel.restart()
                }
            )
            return@Surface
        }

        LaunchedEffect(key1 = Unit) {
            animate(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 2000, easing = LinearEasing),
                ),
            ) { animationValue, _ -> progressOffset = animationValue }
        }

        LaunchedEffect(key1 = Unit) {
            animate(
                initialValue = 0f,
                targetValue = 50f,
            ) { animationValue, _ -> rollOffset = animationValue }
        }

        viewModel.updateCount(progressOffset)

        CountDownCanvas(modifier, progressOffset)
        FilmRollCanvas(rollOffset, viewModel)
        CountDownText(countDownUiState)
    }
}

@Composable
private fun MeasureCanvas(
    modifier: Modifier,
    viewModel: OldMovieCountDownViewModel
) {
    BoxWithConstraints(modifier) {
        viewModel.measure(
            containerHeight = with(LocalDensity.current) { maxHeight.toPx() },
            containerWidth = with(LocalDensity.current) { maxWidth.toPx() },
        )
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        OldMovieCountDown(
            modifier = Modifier.fillMaxSize(),
            viewModel = OldMovieCountDownViewModel()
        )
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        OldMovieCountDown(
            modifier = Modifier.fillMaxSize(),
            viewModel = OldMovieCountDownViewModel()
        )
    }
}
