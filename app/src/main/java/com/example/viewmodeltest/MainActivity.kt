package com.example.viewmodeltest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viewmodeltest.ui.screens.vievmodels.CounterViewModel
import com.example.viewmodeltest.ui.screens.vievmodels.UserViewModel
import com.example.viewmodeltest.ui.theme.ViewmodelTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(
    userViewModel: UserViewModel = viewModel(),
    counterViewModel: CounterViewModel = viewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Sekcja dla UserViewModel
        TextField(
            value = userViewModel.userName.value,
            onValueChange = { userViewModel.updateUserName(it) },
            label = { Text("Imię") }
        )
        TextField(
            value = userViewModel.userAge.value.toString(),
            onValueChange = { userViewModel.updateUserAge(it.toIntOrNull() ?: 0) },
            label = { Text("Wiek") }
        )
        Text("Imię: ${userViewModel.userName.value}, Wiek: ${userViewModel.userAge.value}")

        Spacer(modifier = Modifier.height(16.dp))

        // Sekcja dla CounterViewModel
        Text("Licznik: ${counterViewModel.count.value}")
        Button(onClick = { counterViewModel.incrementCount() }) {
            Text("Zwiększ licznik")
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ViewmodelTestTheme {
        Greeting("Android")
    }
}