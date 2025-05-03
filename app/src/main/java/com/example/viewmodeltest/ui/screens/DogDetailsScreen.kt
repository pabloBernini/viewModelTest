package com.example.viewmodeltest.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viewmodeltest.ui.viewModels.DogDetailsVM

@Composable
fun DogDetailsScreen(
    viewModel: DogDetailsVM,
    dogName: String?,
    onBackPressed: () -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Dog Details Screen")
            if (dogName != null) {
                Text(text = "Dog Name: $dogName")
            } else {
                Text(text = "Dog name not provided")
            }
            Button(onClick = { onBackPressed() }) {
                Text("Go Back")
            }
        }
    }
}