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
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import com.example.androiddevchallenge.OldMovieCountDownViewModel

@Composable
fun FilmRollCanvas(rollOffset: Float, viewModel: OldMovieCountDownViewModel) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawLine(
            start = Offset(x = 40f, y = 0f),
            end = Offset(x = 40f, y = size.height),
            color = Color.Black,
            strokeWidth = 3f
        )

        drawLine(
            start = Offset(x = size.width - 40f, y = 0f),
            end = Offset(x = size.width - 40f, y = size.height),
            color = Color.Black,
            strokeWidth = 3f
        )
        viewModel.filmRollMeasures().forEach {
            drawRect(
                color = Color.Black,
                size = Size(it.width, it.height),
                topLeft = Offset(it.currentPostX, it.currentPostY)
            )
        }
        viewModel.moveFilmRoll(rollOffset)
    }
}
