package com.example.recetariobase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.recetariobase.componentes.navegacion.BarraInferior
import com.example.recetariobase.componentes.navegacion.Recetas
import com.example.navegacion.componentes.HomeScreen
import com.example.recetariobase.componentes.navegacion.Perfil
import com.example.recetariobase.componentes.navegacion.Favoritos
import com.example.navegacion.modelos.Pantallas
import com.example.recetariobase.componentes.ContenidoHojaInferior
import com.example.recetariobase.componentes.ListaPlatillos
import com.example.recetariobase.datos.Datos
import com.example.recetariobase.modelos.Receta
import com.example.recetariobase.ui.theme.RecetarioBaseTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val controlador = rememberNavController()
            val backStackEntry by controlador.currentBackStackEntryAsState()
            val destinoActual = backStackEntry?.destination

            var recetaSeleccionada by remember { mutableStateOf<Receta?>(null) }
            val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

            var favoritos by remember { mutableStateOf(setOf<Receta>()) }

            RecetarioBaseTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BarraInferior(destinoActual) {
                            controlador.navigate(it.ruta) {
                                popUpTo(controlador.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
                        NavHost(
                            navController = controlador,
                            startDestination = Pantallas.Inicio,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            composable<Pantallas.Inicio> {
                                val context = LocalContext.current
                                val recetasMap = remember { Datos.getRecetas(context) }
                                val todasLasRecetas = remember(recetasMap) { recetasMap.values.flatten() }

                                HomeScreen(
                                    recetas = todasLasRecetas,
                                    favoritos = favoritos,
                                    onToggleFavorite = { receta ->
                                        favoritos = if (favoritos.contains(receta)) favoritos - receta else favoritos + receta
                                    },
                                    onRecetaClick = { receta ->
                                        recetaSeleccionada = receta
                                    }
                                )
                            }
                            composable<Pantallas.Recetas> {
                                RecetasListaConCategorias(
                                    favoritos = favoritos,
                                    onToggleFavorite = { receta ->
                                        favoritos = if (favoritos.contains(receta)) favoritos - receta else favoritos + receta
                                    },
                                    onRecetaClick = { recetaSeleccionada = it }
                                )
                            }
                            composable<Pantallas.Favoritos> {
                                Favoritos(
                                    recetasFavoritas = favoritos.toList(),
                                    onRecetaClick = { recetaSeleccionada = it },
                                    onRemoveFavorite = { receta -> favoritos = favoritos - receta }
                                )
                            }
                            composable<Pantallas.Perfil> {
                                val context = LocalContext.current
                                val recetasMap = remember {
                                    Datos.getRecetas(context)
                                }
                                val todasLasRecetas = remember(recetasMap) {
                                    recetasMap.values.flatten()
                                }
                                Perfil(
                                    recetas = todasLasRecetas
                                )
                            }
                        }

                        if (recetaSeleccionada != null) {
                            ModalBottomSheet(
                                onDismissRequest = { recetaSeleccionada = null },
                                sheetState = sheetState
                            ) {
                                ContenidoHojaInferior(
                                    receta = recetaSeleccionada!!,
                                    isFavorite = favoritos.contains(recetaSeleccionada!!),
                                    onFavoriteClick = {
                                        val receta = recetaSeleccionada!!
                                        favoritos = if (favoritos.contains(receta)) favoritos - receta else favoritos + receta
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecetasListaConCategorias(
    favoritos: Set<Receta> = emptySet(),
    onToggleFavorite: (Receta) -> Unit = {},
    onRecetaClick: (Receta) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val recetasMap = remember { Datos.getRecetas(context) }
    val todasLasRecetas = remember(recetasMap) { recetasMap.values.flatten() }

    var recetaInternaSeleccionada by remember { mutableStateOf<Receta?>(null) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Box(modifier = modifier.fillMaxSize()) {
        ListaPlatillos(
            recetas = todasLasRecetas,
            favoritos = favoritos,
            onToggleFavorite = onToggleFavorite,
            onRecetaClick = { receta ->
                recetaInternaSeleccionada = receta
                onRecetaClick(receta)
            }
        )

        if (recetaInternaSeleccionada != null) {
            ModalBottomSheet(
                onDismissRequest = { recetaInternaSeleccionada = null },
                sheetState = sheetState
            ) {
                ContenidoHojaInferior(
                    receta = recetaInternaSeleccionada!!,
                    isFavorite = favoritos.contains(recetaInternaSeleccionada!!),
                    onFavoriteClick = { onToggleFavorite(recetaInternaSeleccionada!!) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecetarioBaseTheme {
        RecetasListaConCategorias()
    }
}
