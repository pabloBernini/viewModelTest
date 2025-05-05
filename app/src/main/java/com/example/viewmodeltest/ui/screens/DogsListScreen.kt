package com.example.viewmodeltest.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    navigationController: NavController
) {
    val items by viewModel.uiState.collectAsStateWithLifecycle()
    val name by viewModel.name

    Scaffold(
        modifier = Modifier.fillMaxSize())
        { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            /////// navbar ////////
            Navbar(navigationController, "main")



            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { viewModel.name.value = it }, // Update name in VM
                    modifier = Modifier.weight(1f) // Use weight for flexible sizing
                )

                OutlinedButton(
                    enabled = name.isNotEmpty() == false,
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
}

@Composable
fun DogsList(
    dogs: List<Dog>,
    navController: NavController,
    onFav: (id: Int) -> Unit,
    onTrash: (id: Int) -> Unit
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

                val brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF6A5ACD),
                        Color(0xFFFFC0CB)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(50f, 100f)
                )

                IconButton(
                    onClick = {
                        onFav(dog.id)
                    }
                ) {
                    Icon(
                        modifier = Modifier
                            .graphicsLayer(alpha = 0.99f)
                            .drawWithCache {
                                onDrawWithContent {
                                    drawContent()
                                    drawRect(brush, blendMode = BlendMode.SrcAtop)
                                }
                            },
                        imageVector = if (dog.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = null,
                    )
                }

                IconButton(onClick = {
                    onTrash(dog.id)
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