package com.example.dora_la_calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.dora_la_calculadora.ui.theme.Dora_la_calculadoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val VisualizarCalculadora = ViewModelProvider(this)[CalculadoraViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            Dora_la_calculadoraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Calculadora(modifier = Modifier.padding(innerPadding), VisualizarCalculadora)
                }
            }
        }
    }
}
