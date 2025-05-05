package com.example.viewmodeltest.ui.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.viewmodeltest.ui.components.Navbar


@Composable
fun ProfileScreen(navigationController: NavController,
                  scaffoldPadding: PaddingValues)  {



    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
       Navbar(navigationController, "profile", scaffoldPadding)
        Image(
            painter = painterResource(id = com.example.viewmodeltest.R.drawable.profilowe),
            contentDescription = "Doggo Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .aspectRatio(1f)
        )

        Text("Majk Wazowski")
    }
}