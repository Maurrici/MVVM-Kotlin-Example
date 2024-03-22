package com.example.moneyregister.ui.home

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moneyregister.model.Register
import com.example.moneyregister.repository.RegisterRepository
import com.example.moneyregister.services.CalcService
import com.example.moneyregister.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val application: Application,
    private val registerRepository: RegisterRepository,
    private val calcService: CalcService
): ViewModel() {
    private var _registers = MutableLiveData<MutableList<Register>>(null)
    var registers: LiveData<MutableList<Register>> = _registers

    fun getRegisters() {
        _registers.value = registerRepository.getAllRegister()
    }

    fun createGainRegister(name: String, value: Float){
        if(name != "" && value > 0){
            registerRepository.createRegister(name, value, Constants.Register.GAIN)

            getRegisters()
        } else {
            Toast.makeText(application, "Campos não preenchidos", Toast.LENGTH_LONG)
                .show()
        }
    }

    fun createDebtRegister(name: String, value: Float){
        if(name != "" && value > 0){
            registerRepository.createRegister(name, value, Constants.Register.DEBT)

            getRegisters()
        } else {
            Toast.makeText(application, "Campos não preenchidos", Toast.LENGTH_LONG)
                .show()
        }
    }

    fun getBalanceText(): String {
        val balanceValue = calcService.calcBalance(registers.value)

        return "Seu saldo é: ${balanceValue}"
    }
}