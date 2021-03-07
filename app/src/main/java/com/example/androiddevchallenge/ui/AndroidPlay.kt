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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.withTransform

@Composable
fun AndroidPlay(modifier: Modifier) {
    Canvas(modifier = modifier) {
        withTransform(
            {
                rotate(345f)
            },
            {
                drawRect(
                    color = Color.Green,
                    size = Size(800f, 100f),
                    topLeft = Offset((size.width / 2) - 400f, (size.height / 2) - 150f)
                )
                drawOval(
                    color = Color.Green,
                    size = Size(800f, 200f),
                    topLeft = Offset((size.width / 2) - 400f, (size.height / 2) - 250f)
                )
                drawCircle(
                    color = Color.White,
                    radius = 30f,
                    center = Offset((size.width / 2) - 200f, (size.height / 2) - 150f)
                )
                drawCircle(
                    color = Color.White,
                    radius = 30f,
                    center = Offset((size.width / 2) + 200f, (size.height / 2) - 160f)
                )
            }
        )
        drawRect(
            color = Color.Green,
            size = Size(800f, 400f),
            topLeft = Offset(size.width / 2 - (400f), size.height / 2)
        )
        val trianglePath = Path().apply {
            moveTo((size.width / 2) - 100f, (size.height / 2) + 90f)
            lineTo((size.width / 2) - 100f, (size.height / 2) + 310f)
            lineTo((size.width / 2) + 180f, (size.height / 2) + 200f)
        }
        drawPath(
            color = Color.White,
            path = trianglePath
        )
        withTransform(
            {
                rotate(320f)
            },
            {
                drawRoundRect(
                    color = Color.Green,
                    cornerRadius = CornerRadius(20f, 20f),
                    size = Size(30f, 120f),
                    topLeft = Offset((size.width / 2) - 100, (size.height / 2) - 400)
                )
            }
        )

        withTransform(
            {
                rotate(15f)
            },
            {
                drawRoundRect(
                    color = Color.Green,
                    cornerRadius = CornerRadius(20f, 20f),
                    size = Size(30f, 120f),
                    topLeft = Offset((size.width / 2) + 60, (size.height / 2) - 400)
                )
            }
        )
    }
}
