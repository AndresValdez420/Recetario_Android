package com.example.recetariobase.componentes

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recetariobase.R

@Composable
fun PlatilloCard(
    nombre: String = "Platillo",
    tiempo: String = "5 min",
    porciones: String = "1 porción",
    calorias: String = "Calorías",
    dificultad: String = "Dificultad"
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(2.dp, Color(0xFFCCCCCC), RoundedCornerShape(24.dp))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.heart_outline),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = porciones, fontSize = 14.sp)

                    Spacer(modifier = Modifier.width(12.dp))

                    Icon(
                        painter = painterResource(R.drawable.heart_outline),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = calorias, fontSize = 14.sp)
                }

                Text(
                    text = dificultad,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Column(
                modifier = Modifier.align(Alignment.BottomStart)
            ) {
                Text(
                    text = tiempo,
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Text(
                    text = nombre,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            IconButton(
                onClick = { },
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Icon(
                    painter = painterResource(R.drawable.heart_outline),
                    contentDescription = "Favorito",
                    modifier = Modifier.size(36.dp),
                    tint = Color(0xFFCCCCCC)
                )
            }
        }
    }
}
