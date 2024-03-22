package com.example.moneyregister.repository

import com.example.moneyregister.datasource.RegisterDataSource
import com.example.moneyregister.model.Register
import javax.inject.Inject

class RegisterRepository @Inject constructor(
    private val dataSource: RegisterDataSource
) {
    fun createRegister(name: String, value: Float, type: String): Register {
        return dataSource.createRegister(name, value, type)
    }

    fun getAllRegister(): MutableList<Register> {
        return dataSource.getAllRegister()
    }
}

