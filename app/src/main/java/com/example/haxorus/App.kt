package com.example.haxorus

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.haxorus.presentation.AppNavigation
import com.example.haxorus.presentation.NoteState
import com.example.haxorus.presentation.NotesViewModel

@Composable
fun App(
    viewModel: NotesViewModel,
    state: NoteState,
    currentRoute: String,
    navController: NavHostController
) {
    AppNavigation(currentRoute = currentRoute, navController = navController , state = state, onEvent = viewModel::onEvent)
}
