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
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                OldMovieCountDown()
            }
        }
    }
}

// Start building your app here!
@Composable
fun OldMovieCountDown() {
    val countdown = remember { mutableStateOf(8) }
    Surface(color = MaterialTheme.colors.background) {
        var progressOffset by remember { mutableStateOf(0f) }
        LaunchedEffect(key1 = Unit) {

            animate(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = repeatable(
                    animation = tween(durationMillis = 2000, easing = LinearEasing),
                    iterations = 1000000,
                ),
            ) { animationValue, _ -> progressOffset = animationValue }
            print("progressOffset $progressOffset")

        }

        Canvas(modifier = Modifier.fillMaxSize()) {

            withTransform(
                {
                    inset(
                        top = (size.height / 2 - size.minDimension / 2),
                        bottom = (size.height / 2 - size.minDimension / 2),
                        left = (size.width / 2 - size.minDimension / 2),
                        right = (size.width / 2 - size.minDimension / 2)
                    )
                }, {
                    drawArc(
                        color = Color.Gray,
                        startAngle = 270f,
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
                color = Color.Blue,
                style = Stroke(width = 2f),
                center = middle,
                radius = size.minDimension / 2
            )

            drawCircle(
                color = Color.Blue,
                style = Stroke(width = 2f),
                center = middle,
                radius = size.minDimension / 2.5f
            )


        }
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Text("${countdown.value}", fontSize = 246.sp, textAlign = TextAlign.Center)
        }


    }
}


@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        OldMovieCountDown()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        OldMovieCountDown()
    }
}
