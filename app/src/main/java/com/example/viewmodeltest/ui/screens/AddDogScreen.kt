package com.example.viewmodeltest.ui.screens
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viewmodeltest.ui.viewModels.AddDogVM
import com.example.viewmodeltest.ui.viewModels.DogsListVM
import androidx.navigation.NavController
import com.example.viewmodeltest.ui.components.Navbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDogScreen(
    addDogViewModel: AddDogVM,
    dogsListViewModel: DogsListVM,
    onDogAdded: () -> Unit,
    scaffoldPadding: PaddingValues,
    navigationController: NavController
) {
    val name by addDogViewModel.name

    val breed by addDogViewModel.breed

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Navbar(navigationController, "addDog", scaffoldPadding)
            OutlinedTextField(
                value = name,
                onValueChange = { addDogViewModel.updateName(it) },
                label = { Text("Dog Name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = breed,
                onValueChange = { addDogViewModel.updateBreed(it) },
                label = { Text("Dog Breed") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (name.isNotBlank() && breed.isNotBlank()) {
                        dogsListViewModel.addDog(name, breed)
                        addDogViewModel.clear()
                        onDogAdded()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Dog")
            }
    }
}