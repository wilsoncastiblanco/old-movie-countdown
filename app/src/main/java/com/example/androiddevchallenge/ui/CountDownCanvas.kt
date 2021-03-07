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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform

@Composable
fun CountDownCanvas(modifier: Modifier, progressOffset: Float) {
    Canvas(modifier = modifier.fillMaxSize()) {

        withTransform(
            {
                inset(
                    top = (size.height / 2 - size.minDimension / 2),
                    bottom = (size.height / 2 - size.minDimension / 2),
                    left = (size.width / 2 - size.minDimension / 2),
                    right = (size.width / 2 - size.minDimension / 2)
                )
            },
            {
                drawArc(
                    color = Color.Gray,
                    startAngle = 270f,
                    alpha = 0.7f,
                    sweepAngle = progressOffset * 360,
                    useCenter = true,
                    size = size,
                    style = Fill
                )
            }
        )

        val middle =
            Offset(size.width / 2, size.height / 2)

        val middleTopToBottom = Offset(size.width / 2, 0f)

        drawLine(
            color = Color.Black,
            start = middleTopToBottom,
            end = Offset(size.width / 2, size.maxDimension), strokeWidth = 15f
        )

        val middleRightToLeft = Offset(0f, size.height / 2)

        drawLine(
            color = Color.Black,
            start = middleRightToLeft,
            end = Offset(size.maxDimension, size.height / 2), strokeWidth = 15f
        )

        drawCircle(
            color = Color.Black,
            style = Stroke(width = 2f),
            center = middle,
            radius = size.minDimension / 2
        )

        drawCircle(
            color = Color.Black,
            style = Stroke(width = 2f),
            center = middle,
            radius = size.minDimension / 2.5f
        )
    }
}
