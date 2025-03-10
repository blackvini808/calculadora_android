package com.example.dora_la_calculadora

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val lista_botones = listOf(
    "C", "(",")","/",
    "7","8","9","*",
    "4","5","6","+",
    "1","2","3","-",
    "AC","0",".","="
)

@Composable
fun Calculadora(modifier: Modifier=Modifier, viewModel: CalculadoraViewModel){

    val Operacion = viewModel.textoOperacion.observeAsState()
    val Resultado = viewModel.textoResultado.observeAsState()

    Box(modifier = modifier){
        Column(
            modifier=modifier.fillMaxSize().background(Color.Black),
            horizontalAlignment = Alignment.End
        ){
            Text(

                text = Operacion.value?:"",
                style = TextStyle(
                    fontSize = 30.sp,
                    textAlign = TextAlign.End
                ),
                color = Color(0xFFFFBF00),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.weight(1f),)
            Text(
                text =Resultado.value?:"",
                style = TextStyle(
                    fontSize = 60.sp,
                    textAlign = TextAlign.End
                ),
                color = Color(0xFFFFBF00)
            )
            Spacer(modifier = Modifier.height(10.dp)) //Spacer: espacio en blanco

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
            ) {
                items(lista_botones){
                    BotonesCalculadora(btn = it, onClick = {
                        viewModel.PresionarBoton(it)
                    })
                }
            }
        }
    }
}

@Composable
fun BotonesCalculadora(btn: String,onClick: ()-> Unit){
    Box(modifier = Modifier.padding(10.dp)){
        FloatingActionButton(
            onClick = onClick,
            modifier = Modifier.size(80.dp),
            shape = CircleShape,
            contentColor = Color.White,
            containerColor = ColorBotones(btn)
        ) {
            Text(text = btn, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }
    }
}

fun ColorBotones(btn: String):Color{
    if(btn=="C" || btn=="AC")
        return Color.Cyan
    if(btn=="(" || btn==")")
        return Color.Magenta
    if(btn=="/" || btn=="*" || btn=="+" || btn=="-" || btn=="=")
        return Color.Green
    return Color.Black
}