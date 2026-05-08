package com.example.recetariobase.datos

import android.content.Context
import com.example.recetariobase.modelos.Receta
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.sequences.forEach

object Datos {
    private val jsonObject = Json{ignoreUnknownKeys=true}

    fun getRecetas(context : Context?) : Map<String,List<Receta>>{
        return try{
            val jsonString = readJsonAsset(context, "recetas.json")
            jsonObject.decodeFromString<List<Receta>>(jsonString)
                .groupBy(keySelector = { it.cuisine }, valueTransform = {it})
        } catch(e: Exception){
            emptyMap()
        }
    }

    private fun readJsonAsset(context: Context?, path: String) : String{
        return try {
            val file = context?.assets?.open(path)
            val bufferedReader = BufferedReader(InputStreamReader(file))
            val stringBuilder = StringBuilder()
            bufferedReader.useLines { lines ->
                lines.forEach {
                    stringBuilder.append(it)
                }
            }
            stringBuilder.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}