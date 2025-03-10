package com.example.dora_la_calculadora

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

class CalculadoraViewModel : ViewModel(){

    private val txtOperacion = MutableLiveData("") //Subclase de LiveData que permite modificar los datos almacenados
    val textoOperacion : LiveData<String> = txtOperacion

    private val txtResultado = MutableLiveData("0")
    val textoResultado : LiveData<String> = txtResultado

    fun PresionarBoton(btn : String){
        Log.i("Botón presionado", btn)//Revisar si esta presionando los botones

        txtOperacion.value?.let {
            if (btn == "AC") {
                txtOperacion.value = ""
                txtResultado.value = "0"
                return
            }
            if (btn == "C") {
                if (it.isNotEmpty()) {
                    txtOperacion.value = it.substring(0, it.length - 1)
                }
                return
            }
            if (btn == "=") {
                txtOperacion.value = txtResultado.value
                return
            }
            txtOperacion.value = it + btn

            Log.i("Operación", txtOperacion.value.toString())//Revisar si concatena

            //TRY & CATCH PARA CUIDAR AL USUARIO
            try {
                txtResultado.value = calcularResultado(txtOperacion.value.toString())
            }catch(_ :Exception){}
        }
    }


    fun calcularResultado(operacion : String) : String{
        val context : Context = Context.enter()
        context.optimizationLevel = -1
        val scriptable : Scriptable = context.initStandardObjects()
        var resultadoFinal = context.evaluateString(scriptable,operacion,"Javascript", 1, null).toString()
        if(resultadoFinal.endsWith(".0")){
            resultadoFinal = resultadoFinal.replace(".0","")
        }
        return resultadoFinal
    }

}