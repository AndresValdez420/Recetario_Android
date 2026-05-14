package com.example.recetariobase.componentes.navegacion

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
            R.drawable.home_outline,
            R.drawable.home,
            Pantallas.Inicio
        ),
        DestinoSuperior(
            stringResource(R.string.Recipies),
            R.drawable.invoice_list_outline,
            R.drawable.invoice_list,
            Pantallas.Recetas
        ),
        DestinoSuperior(
            stringResource(R.string.Favorites),
            R.drawable.heart_outline,
            R.drawable.heart,
            Pantallas.Perfil
        ),
        DestinoSuperior(
            stringResource(R.string.Profile),
            R.drawable.account_circle_outline,
            R.drawable.account_circle,
            Pantallas.Favoritos
        )
    )

    NavigationBar(
        tonalElevation = 0.dp
    ) {
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
