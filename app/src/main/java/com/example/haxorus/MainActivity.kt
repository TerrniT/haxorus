package com.example.haxorus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.haxorus.data.NotesDatabase
import com.example.haxorus.presentation.NotesViewModel
import com.example.haxorus.ui.theme.HaxorusTheme
import androidx.compose.runtime.getValue

class MainActivity : ComponentActivity() {

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            NotesDatabase::class.java,
            "notes.db"
        ).build()
    }

    private val viewModel by viewModels<NotesViewModel> (
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun<T: ViewModel> create(modelClass: Class<T>): T {
                    return NotesViewModel(database.dao) as T
                }
            }
        }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HaxorusTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val state by viewModel.state.collectAsState()
                    val navController = rememberNavController()
                    val currentRoute = navController.currentBackStackEntry?.destination?.route

                    App(
                        viewModel = viewModel,
                        state = state,
                        currentRoute = currentRoute.toString(),
                        navController = navController
                    )
                }
            }
        }
    }
}
