package com.example.viewmodeltest.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.viewmodeltest.ui.components.Navbar
import com.example.viewmodeltest.ui.viewModels.DogDetailsVM
import com.example.viewmodeltest.ui.viewModels.DogsListVM

@Composable
fun DogDetailsScreen(
    viewModel: DogsListVM,
    dogName: String,
    onBackPressed: () -> Unit,
    scaffoldPadding: PaddingValues,
    navigationController: NavController,
    uiState: DogDetailsVM.UiState,
    retryAction: () -> Unit


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

            ContentScreen(uiState, retryAction)
        }
}

@Composable
fun ContentScreen(uiState: DogDetailsVM.UiState, retryAction: () -> Unit, modifier: Modifier = Modifier) {
    when(uiState) {
        is DogDetailsVM.UiState.Loading -> LoadingScreen(
            modifier = modifier
        )
        is DogDetailsVM.UiState.Error -> ErrorScreen(
            retryAction = retryAction, modifier = modifier
        )
        is DogDetailsVM.UiState.Success -> {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(uiState.photo.message)
                    .crossfade(true)
                    .build(),
                contentDescription = null
            )
        }

    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
        color = MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = Icons.Default.Warning, contentDescription = ""
        )
        Text(text = "Failed", modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Image(imageVector = Icons.Default.Refresh, contentDescription = null)
        }
    }
}