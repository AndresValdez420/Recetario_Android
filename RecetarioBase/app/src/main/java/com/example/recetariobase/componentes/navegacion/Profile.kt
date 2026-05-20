package com.example.recetariobase.componentes.navegacion


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recetariobase.R
import org.w3c.dom.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import com.example.recetariobase.RecetasListaConCategorias
import com.example.recetariobase.modelos.Receta
import com.example.recetariobase.ui.theme.RecetarioBaseTheme

@Composable
fun Perfil(
    recetas: List<Receta>,
) {
    val recetasGuardadas = recetas.drop(45)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE9E9E9))
            .padding(24.dp)
    ) {

        // Título
        Text(
            text = stringResource(R.string.usuario),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Círculo principal
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = "https://static.wikia.nocookie.net/battleblocktheateres/images/c/cb/Hatty_Hattington.jpg/revision/latest?cb=20170825205059&path-prefix=es",
                contentDescription = "Foto de perfil",

                modifier = Modifier
                    .size(180.dp)
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

        // Tarjetas inferiores
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // Tarjeta calorías
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp),
                    )

                    Text(
                        text = stringResource(R.string.calorias_sem),
                        fontSize = 12.sp
                    )

                    Text(
                        text = "1,840",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Tarjeta kcal
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp),
                    )

                    Text(
                        text = "250",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = stringResource(R.string.kcal_hoy),
                        fontSize = 12.sp,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))

        //Recetas guardadas
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(recetasGuardadas) { receta ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp),

                    shape = RoundedCornerShape(22.dp)
                ) {

                    Box {

                        // Imagen
                        AsyncImage(
                            model = receta.image,
                            contentDescription = receta.name,

                            modifier = Modifier.fillMaxSize(),

                            contentScale = ContentScale.Crop
                        )

                        // Oscurecer imagen
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Color.Black.copy(alpha = 0.28f)
                                )
                        )

                        // Barra superior
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),

                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            // Calorías
                            Surface(
                                shape = RoundedCornerShape(50),
                                color = Color.Black.copy(alpha = 0.35f),
                            ) {

                                Row(
                                    modifier = Modifier.padding(
                                        horizontal = 10.dp,
                                        vertical = 4.dp
                                    ),

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

                            // Tipo de comida
                            Text(
                                text = receta.mealType.joinToString(" • "),
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            )
                        }

                        // Parte inferior
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
}

