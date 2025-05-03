package com.example.viewmodeltest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.viewmodeltest.ui.theme.MyDogAppTheme

import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.viewmodeltest.model.AddDog
import com.example.viewmodeltest.model.DogScreen
import com.example.viewmodeltest.model.DogsList
import com.example.viewmodeltest.ui.screens.AddDogScreen
import com.example.viewmodeltest.ui.screens.DogDetailsScreen
import com.example.viewmodeltest.ui.screens.DogsScreen
import com.example.viewmodeltest.ui.viewModels.AddDogVM
import com.example.viewmodeltest.ui.viewModels.DogDetailsVM
import com.example.viewmodeltest.ui.viewModels.DogsListVM

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: DogsListVM by viewModels()
            val detailsViewModel: DogDetailsVM by viewModels()
            val addDogViewModel: AddDogVM by viewModels()
            MyDogAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyDogApp(viewModel, detailsViewModel, addDogViewModel)
                }
            }
        }
    }
}

@Composable
fun MyDogApp(
    viewModel: DogsListVM,
    detailsViewModel: DogDetailsVM,
    addDogViewModel: AddDogVM
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = DogsList
    ) {
        composable(DogsList) {
            DogsScreen(
                viewModel = viewModel,
                navigationController = navController
            )
        }
        composable(
            route = "$DogScreen/{dogName}",
            arguments = listOf(navArgument("dogName") { type = NavType.StringType })
        ) { backStackEntry ->
            val dogName = backStackEntry.arguments?.getString("dogName")
            DogDetailsScreen(
                viewModel = detailsViewModel,
                dogName = dogName,
                onBackPressed = { navController.popBackStack() }
            )
        }
        composable(AddDog) {
            AddDogScreen(
                addDogViewModel = addDogViewModel,
                dogsListViewModel = viewModel,
                onDogAdded = { navController.popBackStack() }
            )
        }
    }
}