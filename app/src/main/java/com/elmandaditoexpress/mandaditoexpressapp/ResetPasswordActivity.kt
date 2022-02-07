package com.elmandaditoexpress.mandaditoexpressapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.elmandaditoexpress.mandaditoexpressapp.databinding.ActivityCotizacionesBinding
import com.elmandaditoexpress.mandaditoexpressapp.databinding.ActivityResetPasswordBinding
import com.elmandaditoexpress.mandaditoexpressapp.dto.ResponseWsCotizaciones
import com.elmandaditoexpress.mandaditoexpressapp.dto.ResponseWsResetPassword
import com.elmandaditoexpress.mandaditoexpressapp.network.ApiAdapter
import com.elmandaditoexpress.mandaditoexpressapp.services.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ResetPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.vBtnRestablecer.setOnClickListener {
            if(binding.vEdtEmail.text.toString().isEmpty())
                Toast.makeText(this,"Debe proporcionar su correo electrónico", Toast.LENGTH_LONG).show()

            if(!Utils.validarEmail(binding.vEdtEmail.text.toString()))
                Toast.makeText(this,"Debe proporcionar un correo electronico válido", Toast.LENGTH_LONG).show()
            else
                enviarCorreoReseteo(binding.vEdtEmail.text.toString())
        }
    }

    fun enviarCorreoReseteo(email:String) {
        lateinit var apiResponse: ResponseWsResetPassword

        binding.vBtnRestablecer.isEnabled = false

        lifecycleScope.launch {
            withContext(Dispatchers.IO)
            {
                try {
                    apiResponse = ApiAdapter.apiClient.resetPassword(email)
                } catch (e: Exception) {
                    withContext(Dispatchers.Main){
                        Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }

            if(apiResponse != null){
                binding.vResetContainer.visibility = View.GONE
                binding.vPasswordConfirmContainer.visibility = View.VISIBLE

                binding.vResetPasswordConfirmationMsg.text = apiResponse.mensaje
            }
            binding.vBtnRestablecer.isEnabled = true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}