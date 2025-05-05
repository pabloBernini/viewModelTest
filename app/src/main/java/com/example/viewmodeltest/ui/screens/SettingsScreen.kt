package com.example.viewmodeltest.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.viewmodeltest.ui.components.Navbar


@Composable
fun SettingsScreen(navigationController: NavController,
                   scaffoldPadding: PaddingValues)  {



    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
       Navbar(navigationController, "settings", scaffoldPadding)



}}