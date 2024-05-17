package com.seokjoo.orbitstudy

data class CalculatorState(
    val total: Int = 0,
)

sealed class CalculatorSideEffect {
    data class Toast(val text: String) : CalculatorSideEffect()
}