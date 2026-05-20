package com.example.recetariobase.componentes.navegacion

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.recetariobase.R
import com.example.recetariobase.modelos.Receta

@Composable
fun Perfil(
    recetas: List<Receta>,
) {
    val recetasGuardadas = recetas.drop(45)
    val state = rememberLazyListState()
    val density = LocalDensity.current

    //dinámico
    val imageSize by remember {
        derivedStateOf {
            val scrollOffset = if (state.firstVisibleItemIndex == 0) state.firstVisibleItemScrollOffset else 500
            val scrollDp = with(density) { scrollOffset.toDp() }
            (180.dp - scrollDp).coerceAtLeast(80.dp)
        }
    }

    LazyColumn(
        state = state,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Encabezado del perfil
        item {
            Column {
                Text(
                    text = stringResource(R.string.usuario),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(24.dp))

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = "https://static.wikia.nocookie.net/battleblocktheateres/images/c/cb/Hatty_Hattington.jpg/revision/latest?cb=20170825205059&path-prefix=es",
                        contentDescription = "Foto de perfil",
                        modifier = Modifier
                            .size(imageSize)
                            .clip(CircleShape)
                            .border(
                                width = 4.dp,
                                color = Color.LightGray,
                                shape = CircleShape
                            ),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .height(100.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            LinearProgressIndicator(
                                progress = { 0.7f },
                                modifier = Modifier.fillMaxWidth().height(8.dp),
                            )
                            Text(text = stringResource(R.string.calorias_sem), fontSize = 12.sp)
                            Text(text = "1,840", fontSize = 32.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Card(
                        modifier = Modifier
                            .width(110.dp)
                            .height(100.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            LinearProgressIndicator(
                                progress = { 0.45f },
                                modifier = Modifier.fillMaxWidth().height(6.dp),
                            )
                            Text(text = "250", fontSize = 32.sp, fontWeight = FontWeight.Bold)
                            Text(text = stringResource(R.string.kcal_hoy), fontSize = 12.sp)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        // Listado de recetas
        items(recetasGuardadas) { receta ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp),
                shape = RoundedCornerShape(22.dp)
            ) {
                Box {
                    AsyncImage(
                        model = receta.image,
                        contentDescription = receta.name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.28f))
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = RoundedCornerShape(50),
                            color = Color.Black.copy(alpha = 0.35f),
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.fire),
                                    contentDescription = "Calorías",
                                    tint = Color.White,
                                    modifier = Modifier.size(14.dp)
                                )
                                Text(
                                    text = "${receta.caloriesPerServing} kcal",
                                    color = Color.White,
                                    fontSize = 12.sp
                                )
                            }
                        }
                        Text(
                            text = receta.mealType.joinToString(" • "),
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                    }
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = receta.name,
                            color = Color.White,
                            fontSize = 32.sp,
                            lineHeight = 34.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
