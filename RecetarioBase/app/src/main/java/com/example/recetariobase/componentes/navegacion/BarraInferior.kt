package com.example.navegacion.componentes

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.recetariobase.R
import com.example.navegacion.modelos.DestinoSuperior
import com.example.navegacion.modelos.Pantallas

@Composable
fun BarraInferior(
    destinoActual : NavDestination?,
    onDestinoClicked: (DestinoSuperior)->Unit
){
    val destinos = listOf(
        DestinoSuperior(
            stringResource(R.string.Home),
            R.drawable.food_takeout_box,
            R.drawable.food_takeout_box,
            Pantallas.Inicio
        ),
        DestinoSuperior(
            stringResource(R.string.Recipies),
            R.drawable.food_takeout_box,
            R.drawable.food_takeout_box,
            Pantallas.Recetas
        ),
        DestinoSuperior(
            stringResource(R.string.Records),
            R.drawable.food_takeout_box,
            R.drawable.food_takeout_box,
            Pantallas.Registros
        ),
        DestinoSuperior(
            stringResource(R.string.Favorites),
            R.drawable.food_takeout_box,
            R.drawable.food_takeout_box,
            Pantallas.Favoritos
        )
    )

    NavigationBar{
        destinos.forEach {
            val seleccionado = destinoActual?.hierarchy?.any{h ->
                h.hasRoute(it.ruta::class)
            }
            val icono = if(seleccionado == true) it.iconoRelleno else  it.iconoRegular
            NavigationBarItem(
                selected = seleccionado == true,
                onClick = {
                    onDestinoClicked(it)
                },
                label = {
                    Text(text = it.titulo)
                },
                icon = {
                    Icon(
                        painter = painterResource(icono),
                        contentDescription = it.titulo
                    )
                }
            )
        }
    }
}