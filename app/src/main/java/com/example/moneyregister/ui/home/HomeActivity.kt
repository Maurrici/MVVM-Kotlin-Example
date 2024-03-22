package com.example.moneyregister.ui.home

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.moneyregister.databinding.ActivityHomeBinding
import com.example.moneyregister.model.Register
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var bindind : ActivityHomeBinding

    private lateinit var adapter: ArrayAdapter<Register>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindind = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(bindind.root)

        // Ações dos botões
        bindind.btnDebt.setOnClickListener {
            val name = bindind.fieldName.text.toString()
            val value = bindind.fieldValue.text.toString().toFloat()

            homeViewModel.createDebtRegister(name, value)
            clearFields()
        }

        bindind.btnGain.setOnClickListener {
            val name = bindind.fieldName.text.toString()
            val value = bindind.fieldValue.text.toString().toFloat()

            homeViewModel.createGainRegister(name, value)
            clearFields()
        }

        // Listagem de dados
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)

        bindind.registerList.adapter = adapter
        homeViewModel.registers.observe(this) { registers ->
            if(registers != null){
                adapter.clear()
                adapter.addAll(registers)

                bindind.balanceText.text = homeViewModel.getBalanceText()
            }
        }
    }

    private fun clearFields() {
        bindind.fieldName.setText("")
        bindind.fieldValue.setText("")
    }
}
