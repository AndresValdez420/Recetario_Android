package com.example.recetariobase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.recetariobase.componentes.ListaPlatillos
import com.example.recetariobase.datos.Datos
import com.example.recetariobase.ui.theme.RecetarioBaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecetarioBaseTheme(dynamicColor = false) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainContent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val recetasMap = remember { Datos.getRecetas(context) }
    val todasLasRecetas = remember(recetasMap) { recetasMap.values.flatten() }

    ListaPlatillos(recetas = todasLasRecetas, modifier = modifier)
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecetarioBaseTheme {
        MainContent()
    }
}