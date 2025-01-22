package com.example.unitconvertor

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconvertor.ui.theme.UnitConvertorTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConvertorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    uniConvetor()
                }
            }
        }
    }

    @Composable
    fun uniConvetor() {
        var inputValue by remember { mutableStateOf("") }
        var outputValue by remember { mutableStateOf("") }
        var inputUnit by remember { mutableStateOf("Meter") }
        var outUnit by remember { mutableStateOf("Meter") }
        var inConversionFactor by remember { mutableStateOf(1.00) }
        var outConversionFactor by remember { mutableStateOf(1.00) }
        var iExpand by remember { mutableStateOf(false) }
        var oExpand by remember { mutableStateOf(false) }

        val customInput = TextStyle(
            fontSize = 25.sp,
            fontFamily = FontFamily.Default,
            color = Color.Magenta
        )

        fun unitConverstion(){
            val inputValue = inputValue.toDoubleOrNull() ?: 0.0
            val result = (inputValue * (inConversionFactor * 100.0 / outConversionFactor)).roundToInt()/100
             outputValue = result.toString()
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Uniconverrtor", style = customInput)
            Spacer(modifier = Modifier.padding(6.dp))
            OutlinedTextField(value = inputValue, onValueChange = {
                inputValue = it
                unitConverstion()
            })
            Spacer(modifier = Modifier.padding(6.dp))
            Row {
                Box {

                Button(onClick = { iExpand = true }) {
                    Text(text = inputUnit)
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down",
                    )
                }
                    DropdownMenu(expanded = iExpand, onDismissRequest = { iExpand = false }) {


                        DropdownMenuItem(text = { Text(text = "Centimeter") }, onClick = {
                            inputUnit = "centimeter"
                            iExpand = false
                            inConversionFactor = 0.01
                            unitConverstion()
                        })
                        DropdownMenuItem(text = { Text(text = "Meter") }, onClick = {
                            inputUnit = "meter"
                            iExpand = false
                            inConversionFactor = 1.00
                            unitConverstion()
                        })
                        DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                            inputUnit = "feet"
                            iExpand = false
                            inConversionFactor = 0.3048
                            unitConverstion()
                        })

                    }
                }
                Spacer(modifier = Modifier.padding(6.dp))

                Box {
                    Button(onClick = { oExpand = true }) {
                        Text(text = outUnit)
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Arrow Down",
                        )
                    }
                    DropdownMenu(expanded = oExpand, onDismissRequest = { oExpand = false }) {

                        DropdownMenuItem(text = { Text(text = "Centimeter") }, onClick = {
                            outUnit = "centimeter"
                            oExpand = false
                            outConversionFactor = 0.01
                            unitConverstion()
                        })
                        DropdownMenuItem(text = { Text(text = "Meter") }, onClick = {
                            outUnit = "meter"
                            oExpand = false
                            outConversionFactor = 1.00
                            unitConverstion()
                        })
                        DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                            outUnit = "feet"
                            oExpand = false
                            outConversionFactor = 0.3048
                            unitConverstion()
                        })
                    }
                }
            }
            Text(text = "Result $outputValue $outUnit", style = TextStyle(
                fontSize = 20.sp,
                color = Color.DarkGray))
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun uniconvertorPreview() {
        uniConvetor()
    }
}

