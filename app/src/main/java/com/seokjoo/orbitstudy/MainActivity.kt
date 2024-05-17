package com.seokjoo.orbitstudy

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.seokjoo.orbitstudy.ui.theme.OrbitStudyTheme
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<CalculatorViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrbitStudyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SomeScreen(viewModel)
                }
            }
        }

        // do
//        viewModel.observe(
//            state = ::render,
//            sideEffect = ::handleSideEffect,
//            lifecycleOwner = this,
//            lifecycleState = Lifecycle.State.STARTED,
//        )

        // or
//        lifecycleScope.launch {
//            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                launch {
//                    viewModel.container.stateFlow.collect { render(it) }
//                }
//                launch {
//                    viewModel.container.sideEffectFlow.collect { handleSideEffect(it) }
//                }
//            }
//        }
    }

    @Composable
    fun SomeScreen(viewModel: CalculatorViewModel) {
        val state by viewModel.collectAsState()

        viewModel.collectSideEffect {
            when(it) {
                is CalculatorSideEffect.Toast -> createToast(it.text)
            }
        }
        ButtonText(viewModel, state)
    }

    @Composable
    private fun ButtonText(
        viewModel: CalculatorViewModel,
        state: CalculatorState
    ) {
        Button(onClick = { viewModel.add(1234) }) {
            Text("안녕 나는 ${state.total} 이야")
        }
    }


    private fun handleSideEffect(sideEffect: CalculatorSideEffect) {
        when (sideEffect) {
            is CalculatorSideEffect.Toast -> createToast(sideEffect.text)
        }
    }

    private fun createToast(text: String) {
        Toast.makeText(
            this@MainActivity,
            text,
            Toast.LENGTH_SHORT
        ).show()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OrbitStudyTheme {
    }
}