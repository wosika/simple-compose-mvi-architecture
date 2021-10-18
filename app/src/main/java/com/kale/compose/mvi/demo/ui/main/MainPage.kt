package com.kale.compose.mvi.demo.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kale.compose.mvi.demo.ui.example.ExamplePage
import com.kale.compose.mvi.demo.ui.paging.PagingPage

@Composable
fun MainPage() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "first_page"
    ) {
        composable("first_page") {
            Buttons(navController)
        }
        composable("example_page") {
            ExamplePage()
        }
        composable("paging_page") {
            PagingPage()
        }
    }
}


@Composable
private fun Buttons(navController: NavHostController) {


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            navController.navigate("example_page")
        }) {
            Text(text = "to mvi example")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            navController.navigate("paging_page")
        }) {
            Text(text = "to paging example")
        }
    }
}

