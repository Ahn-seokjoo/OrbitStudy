package com.seokjoo.orbitstudy

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

// Case 1
class CalculatorViewModel : ContainerHost<CalculatorState, CalculatorSideEffect>, ViewModel() {
    override val container = container<CalculatorState, CalculatorSideEffect>(CalculatorState())

    fun add(number: Int) = intent {
        postSideEffect(CalculatorSideEffect.Toast("Adding ${number} to ${state.total}"))

        reduce {
            state.copy(total = state.total + number)
        }
    }
}