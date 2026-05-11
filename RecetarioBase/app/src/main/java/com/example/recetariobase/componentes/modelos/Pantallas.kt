package com.example.navegacion.modelos

import kotlinx.serialization.Serializable
@Serializable
sealed class Pantallas(){
    @Serializable data object Inicio: Pantallas()

    @Serializable data object Recetas: Pantallas()
    @Serializable data object Registros: Pantallas()
    @Serializable data object Favoritos: Pantallas()
}