package com.example.unitconvertor

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconvertor.ui.theme.UnitConvertorTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConvertorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConvertor()
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UnitConvertor(){

    var inputValue by remember {mutableStateOf("") }

    var outputValue by remember { mutableStateOf("") }

    var inputUnit by remember { mutableStateOf("Meter")}

    var outputUnit by remember { mutableStateOf("Meter") }

    var iExpanded by remember { mutableStateOf(false) }

    var oExpanded by remember { mutableStateOf(false) }

    val conversionFactor = remember{ mutableStateOf(1.00) }

    val oConversionFactor = remember { mutableStateOf(1.00) }


    val customTextStyle = androidx.compose.ui.text.TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 32.sp,
        color = Color.Black
    )

    fun convertUnits(){
        val  inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0

        // ?: -- elvis operator

        val result  = (inputValueDouble * conversionFactor.value *100.0/oConversionFactor.value).roundToInt()/100.0

        outputValue = result.toString()


    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Unit Convertor", /*modifier = Modifier.padding(16.dp)*/

            style = customTextStyle
        )

        Spacer(modifier  = Modifier.height(16.dp))

        OutlinedTextField(value = inputValue, onValueChange ={
        // Here goes what should be do with the convertor } )
            inputValue = it
            convertUnits()
        }, label =  { Text("Enter Value")})

        Spacer(modifier  = Modifier.height(16.dp))

            Row{
//                val context  = LocalContext.current
//                //here all ui will be stacked next to each other
//                Button(onClick = {
//                    Toast.makeText(context,"Thanks for clicking!",
//                    Toast.LENGTH_LONG).show() })
//                {
//                    Text("Click Me!")
//                }

                //input box
                Box {
                    //output btn
                    Button(onClick = { iExpanded = true }) {
                        Text(text= inputUnit)
                        Icon(Icons.Default.ArrowDropDown,
                            contentDescription = "Arrow Down")
                    }

                    DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                        DropdownMenuItem(text =
                        { Text("Centimeter") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "Centimeter"
                                conversionFactor.value = 0.01
                                convertUnits()

                            }
                        )

                        DropdownMenuItem(text =
                        { Text("Meter") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "Meter"
                                conversionFactor.value = 1.00
                                convertUnits()
                            }
                        )

                        DropdownMenuItem(text =
                        { Text("Feet") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "Feet"
                                conversionFactor.value = 0.3048
                                convertUnits()
                            }
                        )

                        DropdownMenuItem(text =
                        { Text("Millimeter") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "Millimeter"
                                conversionFactor.value = 0.001
                                convertUnits()
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                //output box
                Box {
                    Button(onClick = { oExpanded = true }) {
                        Text(text = outputUnit)
                        Icon(Icons.Default.ArrowDropDown,
                            contentDescription = "Arrow Down")
                    }

                    DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                        DropdownMenuItem(text =
                        { Text("Centimeter") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "Centimeter"
                                oConversionFactor.value = 0.01
                                convertUnits()
                            }
                        )

                        DropdownMenuItem(text =
                        { Text("Meters") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "Meter"
                                oConversionFactor.value = 1.00
                                convertUnits()
                            }
                        )

                        DropdownMenuItem(text =
                        { Text("Feet") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "Feet"
                                oConversionFactor.value = 0.3048
                                convertUnits()
                            }
                        )

                        DropdownMenuItem(text =
                        { Text("Millimeter") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "Millimeter"
                                oConversionFactor.value = 0.001
                                convertUnits()
                            }
                        )
                    }
                }
        }
        Spacer(modifier  = Modifier.height(16.dp))
        Text(text = " Result : $outputValue $outputUnit",
             style = MaterialTheme.typography.headlineMedium
            )
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun UnitConvertorPreview(){
    UnitConvertor()
}