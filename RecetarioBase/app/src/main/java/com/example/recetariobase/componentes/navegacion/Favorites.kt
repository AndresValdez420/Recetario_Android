package com.example.recetariobase.componentes.navegacion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recetariobase.R
import com.example.recetariobase.componentes.PlatilloCard
import com.example.recetariobase.modelos.Receta

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Favoritos(
    recetasFavoritas: List<Receta>,
    onRecetaClick: (Receta) -> Unit,
    onRemoveFavorite: (Receta) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        Text(
            text = stringResource(R.string.Favorites),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "— Recientes",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp, start = 8.dp)
        )

        if (recetasFavoritas.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = stringResource(R.string.no_tienes_favoritos), color = Color.Gray)
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(bottom = 16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    items = recetasFavoritas,
                    key = { it.id }
                ) { receta ->
                    val estado = rememberSwipeToDismissBoxState(
                        confirmValueChange = { valor ->
                            if (valor == SwipeToDismissBoxValue.EndToStart) {
                                onRemoveFavorite(receta)
                                true
                            } else {
                                false
                            }
                        }
                    )

                    SwipeToDismissBox(
                        state = estado,
                        backgroundContent = {
                            val color = when (estado.dismissDirection) {
                                SwipeToDismissBoxValue.EndToStart -> Color.Red.copy(alpha = 0.8f)
                                else -> Color.Transparent
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp)
                                    .background(color, RoundedCornerShape(24.dp))
                                    .wrapContentSize(Alignment.CenterEnd)
                                    .padding(end = 16.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.fire),
                                    contentDescription = "Eliminar",
                                    tint = Color.White
                                )
                            }
                        },
                        enableDismissFromStartToEnd = false
                    ) {
                        PlatilloCard(
                            receta = receta,
                            isFavorite = true,
                            onFavoriteClick = { onRemoveFavorite(receta) },
                            onClick = { onRecetaClick(receta) }
                        )
                    }
                }
            }
        }
    }
}