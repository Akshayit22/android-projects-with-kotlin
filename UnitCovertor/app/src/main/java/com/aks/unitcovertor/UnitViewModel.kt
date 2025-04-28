package com.aks.unitcovertor

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class UnitViewModel : ViewModel() {

    private val _inputValue = mutableStateOf("")
    private val _outputValue = mutableStateOf("")
    private val _inputUnit = mutableStateOf("Meters")
    private val _outputUnit = mutableStateOf("Meters")
    private val _iExpanded = mutableStateOf(false)
    private val _oExpanded = mutableStateOf(false)
    private val _conversionFactor = mutableStateOf(1.00)
    private val _oConversionFactor = mutableStateOf(1.00)

    var inputValue: MutableState<String> = _inputValue
    val outputValue: MutableState<String> = _outputValue
    var inputUnit: MutableState<String> = _inputUnit
    var outputUnit: MutableState<String> = _outputUnit
    var iExpanded: MutableState<Boolean> = _iExpanded
    var oExpanded: MutableState<Boolean> = _oExpanded
    var conversionFactor: MutableState<Double> = _conversionFactor
    var oConversionFactor: MutableState<Double> = _oConversionFactor

    fun setInputValue(value: String) {
        _inputValue.value = value
    }

    fun setOutputValue(value: String) {
        _outputValue.value = value
    }

    fun setInputUnit(unit: String) {
        _inputUnit.value = unit
    }

    fun setOutputUnit(unit: String) {
        _outputUnit.value = unit
    }

    fun setIExpanded(expanded: Boolean) {
        _iExpanded.value = expanded
    }

    fun setOExpanded(expanded: Boolean) {
        _oExpanded.value = expanded
    }

    fun setConversionFactor(factor: Double) {
        _conversionFactor.value = factor
    }

    fun setOConversionFactor(factor: Double) {
        _oConversionFactor.value = factor
    }
}