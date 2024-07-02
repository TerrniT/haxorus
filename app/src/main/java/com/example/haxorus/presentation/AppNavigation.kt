package com.example.haxorus.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.haxorus.presentation.screens.AddNoteScreen
import com.example.haxorus.presentation.screens.NotesScreen

@Composable
fun AppNavigation(
    state: NoteState,
    currentRoute: String,
    navController: NavHostController,
    onEvent: (NotesEvent) -> Unit
) {
    Log.d(currentRoute, "AppNavigation: $currentRoute")
    NavHost(
        navController = navController,
        startDestination = "NotesScreen"
    ) {
        composable("NotesScreen") {
            NotesScreen(
                state = state,
                navController = navController,
                onEvent = onEvent
            )
        }
        composable("AddNoteScreen") {
            AddNoteScreen(
                state = state,
                navController = navController,
                onEvent = onEvent
            )
        }
    }
}