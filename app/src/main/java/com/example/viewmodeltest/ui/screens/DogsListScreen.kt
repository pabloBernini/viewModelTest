package com.example.viewmodeltest.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.viewmodeltest.model.AddDog
import com.example.viewmodeltest.model.Dog
import com.example.viewmodeltest.model.DogScreen
import com.example.viewmodeltest.ui.components.Navbar
import com.example.viewmodeltest.ui.viewModels.DogsListVM


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogsScreen(
    viewModel: DogsListVM,
    navigationController: NavController,
    scaffoldPadding: PaddingValues
) {
    val items by viewModel.uiState.collectAsStateWithLifecycle()
    val searchInput by viewModel.searchInput



    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

    /////// navbar ////////
    Navbar(navigationController, "main", scaffoldPadding)



        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = searchInput,
                onValueChange = { newValue ->
                    viewModel.searchInput.value = newValue
                    viewModel.filterList()
                },
                modifier = Modifier.weight(1f),
                singleLine = true,
                placeholder = { Text("Search for doggos") }
            )

            OutlinedButton(
                enabled = searchInput.isNotEmpty() == false,
                onClick = {
                    navigationController.navigate(AddDog)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }

        Row(modifier = Modifier.padding(10.dp)) {
            Text("\uD83D\uDC36: ${viewModel.dogCount()} ")
            Text("\uD83D\uDC97: ${viewModel.favCount()}")
        }

        if (items is DogsListVM.UiState.Success) {
            DogsList(
                dogs = (items as DogsListVM.UiState.Success).data,
                navController = navigationController,
                onFav = viewModel::triggerFav,
                onTrash = viewModel::removeDog
            )
        }
    }
}
@Composable
fun DogsList(
    dogs: List<Dog>,
    navController: NavController,
    onFav: (name: String) -> Unit,
    onTrash: (name: String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 16.dp)
    ) {
        items(dogs) { dog ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .clickable {
                        navController.navigate("$DogScreen/${dog.name}")
                    }
            ) {
                Text(
                    text = dog.name,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(Modifier.weight(1f))
                Text(
                    text = dog.breed,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.weight(1f))


                IconButton(
                    onClick = {
                        onFav(dog.name)
                    }
                ) {
                    if (dog.isFavorite) {

                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "pin")
                    }
                           else {
                                Icon(
                                    imageVector = Icons.Default.FavoriteBorder,
                                    contentDescription = "pinned"
                                )
                            }
                }

                IconButton(onClick = {
                    onTrash(dog.name)
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
            }
        }
    }
}