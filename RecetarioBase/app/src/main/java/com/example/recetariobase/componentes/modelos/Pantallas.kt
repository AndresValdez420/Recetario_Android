package com.example.navegacion.modelos

import kotlinx.serialization.Serializable
@Serializable
sealed class Pantallas(){
    @Serializable data object Inicio: Pantallas()
    @Serializable data object Descubrir: Pantallas()
    @Serializable data object Reproducir: Pantallas()
    @Serializable data object Canciones: Pantallas()
}