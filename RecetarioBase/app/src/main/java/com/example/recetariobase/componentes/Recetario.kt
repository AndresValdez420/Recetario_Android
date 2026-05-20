package com.example.recetariobase.componentes

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.recetariobase.R
import com.example.recetariobase.modelos.Receta

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlatilloCard(
    receta: Receta,
    isFavorite: Boolean = false,
    onFavoriteClick: () -> Unit = {},
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tagBackgroundColor = Color.Black.copy(alpha = 0.4f)
    val tagShape = RoundedCornerShape(8.dp)

    val heartScale by animateFloatAsState(
        targetValue = if (isFavorite) 1.2f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "heartScale"
    )

    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = receta.image,
                contentDescription = receta.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f)
                            ),
                            startY = 100f
                        )
                    )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .background(tagBackgroundColor, tagShape)
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.food_takeout_box),
                        contentDescription = "Porcion",
                        modifier = Modifier.size(14.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = pluralStringResource(
                            R.plurals.servings,
                            receta.servings.toInt(),
                            receta.servings
                        ),
                        fontSize = 12.sp,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.width(8.dp))
                    Box(modifier = Modifier
                        .width(1.dp)
                        .height(10.dp)
                        .background(Color.White.copy(0.3f)))
                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        painter = painterResource(R.drawable.fire),
                        contentDescription = "Calorias",
                        modifier = Modifier.size(14.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${receta.caloriesPerServing} kcal",
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }

                Text(
                    text = receta.difficulty,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .background(tagBackgroundColor, tagShape)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = "${receta.prepTimeMinutes + receta.cookTimeMinutes} min",
                    fontSize = 12.sp,
                    color = Color.White,
                    modifier = Modifier
                        .background(tagBackgroundColor, tagShape)
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = receta.name,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(0.8f),
                    lineHeight = 30.sp
                )
            }

            IconButton(
                onClick = { onFavoriteClick() },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
            ) {
                Icon(
                    painter = painterResource(if (isFavorite) R.drawable.heart else R.drawable.heart_outline),
                    contentDescription = "Favorito",
                    modifier = Modifier.size(28.dp).scale(heartScale),
                    tint = if (isFavorite) Color.Red else Color.White
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlatilloCardFavorito(
    receta: Receta,
    isFavorite: Boolean = false,
    onFavoriteClick: () -> Unit = {},
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tagBackgroundColor = Color.Black.copy(alpha = 0.4f)
    val tagShape = RoundedCornerShape(8.dp)

    val heartScale by animateFloatAsState(
        targetValue = if (isFavorite) 1.2f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "heartScaleFav"
    )

    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = receta.image,
                contentDescription = receta.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f)
                            ),
                            startY = 100f
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = "${receta.prepTimeMinutes + receta.cookTimeMinutes} min",
                    fontSize = 12.sp,
                    color = Color.White,
                    modifier = Modifier
                        .background(tagBackgroundColor, tagShape)
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = receta.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(0.8f),
                    lineHeight = 30.sp
                )
            }

            IconButton(
                onClick = { onFavoriteClick() },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
            ) {
                Icon(
                    painter = painterResource(if (isFavorite) R.drawable.heart else R.drawable.heart_outline),
                    contentDescription = "Favorito",
                    modifier = Modifier.size(18.dp).scale(heartScale),
                    tint = if (isFavorite) Color.Red else Color.White
                )
            }
        }
    }
}

@Composable
fun ListaPlatillos(
    recetas: List<Receta>,
    onRecetaClick: (Receta) -> Unit,
    favoritos: Set<Receta> = emptySet(),
    onToggleFavorite: (Receta) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Text(
            text = stringResource(R.string.Recipies),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text(stringResource(R.string.buscar)) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(50.dp),
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_search_24),
                    contentDescription = stringResource(R.string.buscar)
                )
            },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(recetas, key = { it.id }) { receta ->
                PlatilloCard(
                    modifier = Modifier.animateItem(),
                    receta = receta,
                    isFavorite = favoritos.contains(receta),
                    onFavoriteClick = { onToggleFavorite(receta) },
                    onClick = { onRecetaClick(receta) }
                )
            }
        }
    }
}

@Composable
fun ContenidoHojaInferior(
    receta: Receta,
    isFavorite: Boolean = false,
    onFavoriteClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    val heartScale by animateFloatAsState(
        targetValue = if (isFavorite) 1.2f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "heartScaleSheet"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .padding(24.dp)
    ) {
        Text(
            text = receta.difficulty,
            fontSize = 14.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = receta.name,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 36.sp,
            modifier = Modifier.padding(vertical = 4.dp)
        )

        //Categorías
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier.weight(1f)) {
                receta.tags.take(3).forEach { tag ->
                    Box(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .background(
                                Color.LightGray.copy(alpha = 0.3f),
                                RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Text(text = tag, fontSize = 12.sp, color = Color.DarkGray)
                    }
                }
            }
            IconButton(onClick = onFavoriteClick) {
                Icon(
                    painter = painterResource(if (isFavorite) R.drawable.heart else R.drawable.heart_outline),
                    contentDescription = "Favoritos",
                    tint = if (isFavorite) Color.Red else Color.Gray,
                    modifier = Modifier.size(32.dp).scale(heartScale)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            //Lista
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(R.string.ingredientes),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                receta.ingredients.forEach { ingrediente ->
                    Text(
                        text = "• $ingrediente",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }
            }

            //Imagen lateral
            Column(
                modifier = Modifier.width(130.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.size(130.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    AsyncImage(
                        model = receta.image,
                        contentDescription = "Imagen platillo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.food_takeout_box),
                        contentDescription = "Porciones",
                        modifier = Modifier.size(14.dp),
                        tint = Color.Gray
                    )
                    Text(text = " ${receta.servings} porc.", fontSize = 11.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(R.drawable.fire),
                        contentDescription = "Calorias",
                        modifier = Modifier.size(14.dp),
                        tint = Color.Gray
                    )
                    Text(text = " ${receta.caloriesPerServing}", fontSize = 11.sp, color = Color.Gray)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        //Preparación
        Text(
            text = stringResource(R.string.preparacion),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${receta.prepTimeMinutes + receta.cookTimeMinutes} min",
            fontSize = 13.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Column {

            receta.instructions.forEachIndexed { index, paso ->

                Text(
                    text = "${index + 1}. $paso",
                    fontSize = 15.sp,
                    color = Color.Gray,
                    lineHeight = 22.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}
