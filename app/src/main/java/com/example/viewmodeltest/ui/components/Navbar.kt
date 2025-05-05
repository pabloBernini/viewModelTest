package com.example.viewmodeltest.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.viewmodeltest.model.AddDog
import com.example.viewmodeltest.model.Profile

@Composable
fun Navbar(navigationController: NavController, screenType: String = "main") {
    var navTitle = ""
    val mainTitle = "DoggosApp"
    val profileTitle = "Profile"
    val settingsTitle = "Settings"
    val addDogTitle = "Add Doggo"

    when (screenType) {
        "main" -> navTitle = mainTitle
        "profile" -> navTitle = profileTitle
        "settings" -> navTitle = settingsTitle
        "AddDog" -> navTitle = addDogTitle
    }






    ///// NAVBAR /////


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.Gray)
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(screenType == "main") {

        IconButton(
            onClick = { /*TODO*/ }

        ) {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = "settings",
            )
        }} else {
            IconButton(
                onClick = { navigationController.popBackStack() }

            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "back",
                )
        }}


        Text(
            navTitle,
            style = TextStyle(fontSize = 24.sp)
        )


        if(screenType == "main") {

        IconButton(
            onClick = { navigationController.navigate(Profile)}
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "profile",
            )
        }
        } else {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "back",
                tint = Color.Transparent
            )
        }
    }}
