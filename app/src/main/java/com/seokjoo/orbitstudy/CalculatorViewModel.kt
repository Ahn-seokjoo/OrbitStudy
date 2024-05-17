package com.seokjoo.orbitstudy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

// Case 1
class CalculatorViewModel : ContainerHost<CalculatorState, CalculatorSideEffect>, ViewModel() {
    override val container =
        viewModelScope.container<CalculatorState, CalculatorSideEffect>(CalculatorState()) {
            reduce {
                state.copy(

                )
            }
        }
    
    fun add(number: Int) = intent {
        postSideEffect(CalculatorSideEffect.Toast("Adding ${number} to ${state.total}"))

        // this is pure function?
        // state.total 값에 따라 다른 결과를 내지 않나?
        // state는 결과로 보지 않는건가 ?
        // 단순 예시라서 순수 함수로 만들지 않은 것인가? -> 이건 아닌것 같고...
        reduce {
            state.copy(total = state.total + number)
        }
    }
}