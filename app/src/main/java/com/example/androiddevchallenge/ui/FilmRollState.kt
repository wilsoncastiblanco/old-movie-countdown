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

class FilmRollState {

    val filmRollVertical: MutableList<SquareRoll> = mutableListOf()

    fun build(containerHeight: Float, containerWidth: Float) {
        (1..(containerHeight.toInt() * 100)).filter { it % 64 == 0 }.forEach {
            buildVertical(containerHeight, it, containerWidth)
        }
    }

    private fun buildVertical(
        containerHeight: Float,
        it: Int,
        containerWidth: Float
    ) {
        with(filmRollVertical) {
            add(
                SquareRoll(
                    height = ((containerHeight * 1.7f) / 100),
                    width = 40f,
                    currentPostX = 0f,
                    currentPostY = it.toFloat()
                )
            )
            add(
                SquareRoll(
                    height = ((containerHeight * 1.7f) / 100),
                    width = 40f,
                    currentPostX = containerWidth - 40f,
                    currentPostY = it.toFloat()
                )
            )
        }
    }

    fun move(offset: Float) {
        filmRollVertical.forEach {
            it.move(offset)
        }
    }
}

class SquareRoll(
    val height: Float,
    val width: Float,
    var currentPostY: Float,
    val currentPostX: Float
) {

    fun move(offset: Float) {
        currentPostY -= offset
    }
}
