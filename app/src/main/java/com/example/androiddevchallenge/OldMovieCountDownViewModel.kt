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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.ui.FilmRollState
import com.example.androiddevchallenge.ui.SquareRoll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.RoundingMode

private const val BASE_COUNTDOWN = 8

class OldMovieCountDownViewModel(private val filmRollState: FilmRollState = FilmRollState()) :
    ViewModel() {
    private var countDown = BASE_COUNTDOWN
    private val _uiState = MutableStateFlow<CountDownUiState>(CountDownUiState.Counting(countDown))
    val uiState: StateFlow<CountDownUiState> = _uiState

    fun updateCount(progress: Float) {
        viewModelScope.launch {
            val truncatedProgress =
                progress.toBigDecimal().setScale(2, RoundingMode.CEILING).toFloat()
            if (countDown == 0) {
                _uiState.value = CountDownUiState.Finished
            } else if (truncatedProgress == 1f) {
                _uiState.value = CountDownUiState.Counting(countDown--)
            }
        }
    }

    fun restart() {
        viewModelScope.launch {
            countDown = BASE_COUNTDOWN
            _uiState.value = CountDownUiState.Counting(BASE_COUNTDOWN)
        }
    }

    fun measure(containerHeight: Float, containerWidth: Float) {
        filmRollState.build(
            containerHeight = containerHeight,
            containerWidth = containerWidth,
        )
    }

    fun filmRollMeasures(): List<SquareRoll> {
        return filmRollState.filmRollVertical
    }

    fun moveFilmRoll(rollOffset: Float) {
        filmRollState.move(rollOffset)
    }
}

sealed class CountDownUiState {
    data class Counting(val count: Int) : CountDownUiState()
    object Finished : CountDownUiState()
}
