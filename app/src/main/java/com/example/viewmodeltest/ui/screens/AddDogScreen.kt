package com.example.viewmodeltest.ui.screens
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.viewmodeltest.ui.viewModels.AddDogVM
import com.example.viewmodeltest.ui.viewModels.DogsListVM
import androidx.compose.runtime.mutableStateOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDogScreen(
    addDogViewModel: AddDogVM,
    dogsListViewModel: DogsListVM,
    onDogAdded: () -> Unit
) {
    val name by addDogViewModel.name
    val breed by addDogViewModel.breed
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { addDogViewModel.updateName(it) },
                label = { Text("Dog Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = breed,
                onValueChange = { addDogViewModel.updateBreed(it) },
                label = { Text("Dog Breed") },
                modifier = Modifier.fillMaxWidth()
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
}