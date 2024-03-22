package com.example.moneyregister.datasource

import com.example.moneyregister.model.Register
import javax.inject.Inject

class RegisterDataSource @Inject constructor() {
    var registers: MutableList<Register> = mutableListOf()

    fun createRegister(name: String, value: Float, type: String): Register {
        val register = Register(name, value, type)
        registers.add(register)

        return register;
    }

    fun getAllRegister(): MutableList<Register> {
        return registers
    }
}

