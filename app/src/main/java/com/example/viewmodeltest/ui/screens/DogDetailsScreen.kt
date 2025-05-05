package com.example.viewmodeltest.ui.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.viewmodeltest.ui.components.Navbar
import com.example.viewmodeltest.ui.viewModels.DogsListVM

@Composable
fun DogDetailsScreen(
    viewModel: DogsListVM,
    dogName: String?,
    onBackPressed: () -> Unit,
    scaffoldPadding: PaddingValues,
    navigationController: NavController

) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Navbar(navigationController,
                "dogDetails",
                scaffoldPadding,
                dogName,
                onDelete = { // Receive the dogName as a parameter
                    viewModel.removeDog(dogName.toString()) // Pass the dogName to removeDog
                    // Navigate back to the DogsList screen after deletion
                    onBackPressed()
                })

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