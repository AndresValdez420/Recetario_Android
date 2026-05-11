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
import com.example.navegacion.componentes.BarraInferior
import com.example.navegacion.componentes.Recetas
import com.example.navegacion.componentes.HomeScreen
import com.example.navegacion.componentes.Registros
import com.example.navegacion.componentes.Favoritos
import com.example.navegacion.modelos.Pantallas
import com.example.recetariobase.componentes.ContenidoHojaInferior
import com.example.recetariobase.componentes.ListaPlatillos
import com.example.recetariobase.datos.Datos
import com.example.recetariobase.modelos.Receta
import com.example.recetariobase.ui.theme.RecetarioBaseTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val controlador = rememberNavController()
            val backStackEntry by controlador.currentBackStackEntryAsState()
            val destinoActual = backStackEntry?.destination

            RecetarioBaseTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BarraInferior(destinoActual){
                            controlador.navigate(it.ruta){
                                popUpTo(controlador.graph.findStartDestination().id){
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = controlador,
                        startDestination = Pantallas.Inicio,
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ){
                        composable<Pantallas.Inicio>{
                            HomeScreen()
                        }
                        composable<Pantallas.Recetas>{
                            Recetas()
                        }
                        composable<Pantallas.Registros>{
                            Registros()
                        }
                        composable<Pantallas.Favoritos>{
                            Favoritos()
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecetasListaConCategorias(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val recetasMap = remember { Datos.getRecetas(context) }
    val todasLasRecetas = remember(recetasMap) { recetasMap.values.flatten() }

    var recetaSeleccionada by remember { mutableStateOf<Receta?>(null) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Box(modifier = modifier.fillMaxSize()) {
        ListaPlatillos(
            recetas = todasLasRecetas,
            onRecetaClick = { receta ->
                recetaSeleccionada = receta
            }
        )

        if (recetaSeleccionada != null) {
            ModalBottomSheet(
                onDismissRequest = { recetaSeleccionada = null },
                sheetState = sheetState
            ) {
                ContenidoHojaInferior(receta = recetaSeleccionada!!)
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