package com.example.moneyregister.services

import com.example.moneyregister.model.Register
import com.example.moneyregister.util.Constants
import javax.inject.Inject

class CalcService @Inject constructor() {
    // O cálculo executado é muito simples mas imagine um serviço externo que iria calcular isso

    fun calcBalance(registers: MutableList<Register>?): Float {
        var result = 0.0f

        registers?.forEach {register ->
            if (register.type == Constants.Register.DEBT) result -= register.value
            else result += register.value
        }

        return result
    }
}
