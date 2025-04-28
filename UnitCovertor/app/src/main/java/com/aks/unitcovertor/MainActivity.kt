package com.aks.unitcovertor

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.aks.unitcovertor.ui.theme.UnitCovertorTheme
import kotlin.math.roundToInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.text.input.KeyboardType


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: UnitViewModel = viewModel();
            UnitCovertorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConvertor(
                        modifier = Modifier.padding(innerPadding), viewModel
                    )
                }
            }
        }
    }
}


@Composable
fun UnitConvertor(modifier: Modifier, viewModel: UnitViewModel) {

    fun convertUnits() {
        val inputValueDouble = viewModel.inputValue.value.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * viewModel.conversionFactor.value * 100.0 /
                viewModel.oConversionFactor.value).roundToInt() / 100.0
        viewModel.setOutputValue(result.toString())
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Unit Convertor",
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = viewModel.inputValue.value,
            onValueChange = { input ->
                // Allow only numeric input with optional decimal point
                if (input.all { it.isDigit() || it == '.' }) {
                    viewModel.setInputValue(input)
                    convertUnits()
                }
            },
            label = { Text(text = "Enter Value") },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                Button(onClick = { viewModel.setIExpanded(!viewModel.iExpanded.value) }) {
                    Text(text = viewModel.inputUnit.value)
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow",
                        modifier = Modifier.rotate(if (viewModel.iExpanded.value) 180f else 0f)
                    )
                }
                DropdownMenu(
                    expanded = viewModel.iExpanded.value,
                    onDismissRequest = { viewModel.setIExpanded(false) }
                ) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            viewModel.setInputUnit("Centimeters")
                            viewModel.setConversionFactor(0.01)
                            viewModel.setIExpanded(false)
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            viewModel.setInputUnit("Feet")
                            viewModel.setConversionFactor(0.3048)
                            viewModel.setIExpanded(false)
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeter") },
                        onClick = {
                            viewModel.setInputUnit("Millimeter")
                            viewModel.setConversionFactor(0.001)
                            viewModel.setIExpanded(false)
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meter") },
                        onClick = {
                            viewModel.setInputUnit("Meter")
                            viewModel.setConversionFactor(1.0)
                            viewModel.setIExpanded(false)
                            convertUnits()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { viewModel.setOExpanded(!viewModel.oExpanded.value) }) {
                    Text(text = viewModel.outputUnit.value)
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow",
                        modifier = Modifier.rotate(if (viewModel.oExpanded.value) 180f else 0f)
                    )
                }
                DropdownMenu(
                    expanded = viewModel.oExpanded.value,
                    onDismissRequest = { viewModel.setOExpanded(false) }
                ) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            viewModel.setOutputUnit("Centimeters")
                            viewModel.setOConversionFactor(0.01)
                            viewModel.setOExpanded(false)
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            viewModel.setOutputUnit("Feet")
                            viewModel.setOConversionFactor(0.3048)
                            viewModel.setOExpanded(false)
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeter") },
                        onClick = {
                            viewModel.setOutputUnit("Millimeter")
                            viewModel.setOConversionFactor(0.001)
                            viewModel.setOExpanded(false)
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meter") },
                        onClick = {
                            viewModel.setOutputUnit("Meter")
                            viewModel.setOConversionFactor(1.0)
                            viewModel.setOExpanded(false)
                            convertUnits()
                        }
                    )
                }
            }
        }

        Spacer(
            modifier =
            Modifier.height(16.dp)
        )
        Text(
            text = "Result : ${viewModel.outputValue.value} ${viewModel.outputUnit.value}",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}