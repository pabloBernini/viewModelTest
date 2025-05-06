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
    dogName: String,
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
                onDelete = {
                    viewModel.removeDogByName(dogName) // Pass the dogName to removeDog
                    onBackPressed()
                })

            Text(text = "Dog Details Screen")

            Button(onClick = { onBackPressed() }) {
                Text("Go Back")
            }
        }
}