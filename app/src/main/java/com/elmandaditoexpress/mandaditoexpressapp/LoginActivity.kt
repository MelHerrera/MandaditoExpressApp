package com.elmandaditoexpress.mandaditoexpressapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import com.elmandaditoexpress.mandaditoexpressapp.databinding.ActivityLoginBinding
import com.elmandaditoexpress.mandaditoexpressapp.dto.LoginResponse
import com.elmandaditoexpress.mandaditoexpressapp.network.ApiAdapter
import com.elmandaditoexpress.mandaditoexpressapp.services.Utils
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.ArrayList

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var txtUsuario: TextInputLayout
    private lateinit var etUsuario: TextInputEditText
    private lateinit var txtContra: TextInputLayout
    private lateinit var etContra: TextInputEditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button
    private lateinit var switch: SwitchCompat
    private lateinit var progressBar: ProgressBar
    private lateinit var viewLogin: ConstraintLayout


    private val PREF_NAME = "MySharedPreferencesFile"
    private lateinit var mySharedPreferences: SharedPreferences
    private val KEY = "KEY"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        btnLogin.setOnClickListener {
            if (etUsuario.text.isNullOrEmpty() || etContra.text.isNullOrEmpty()) {
                Toast.makeText(this, "Debe llenar los campos de usurio y contraseña", Toast.LENGTH_SHORT).show()
            } else {
                login()
            }
        }

        btnRegister.setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }

        mySharedPreferences = getSharedPreferences(PREF_NAME, 0)
        if (mySharedPreferences.contains(KEY)){
            switch.isChecked = true
            etUsuario.setText(mySharedPreferences.getString("KEY","No encontrada"))
        }

        switch.setOnClickListener {
            if(switch.isChecked)
            {
                guardarPreferencia(true)
            }else
            {
                guardarPreferencia(false)
            }
        }

        binding.tvOlvidoContraseA.setOnClickListener {
            startActivity(Intent(applicationContext,ResetPasswordActivity::class.java))
        }

    }

    private fun guardarPreferencia(estado: Boolean) {
        val editor: SharedPreferences.Editor = mySharedPreferences.edit()
        if(estado){
            if(etUsuario.text.isNullOrEmpty())
            {
                Toast.makeText(this,"Debe digirtar el usuario a guardar",Toast.LENGTH_SHORT).show()
                switch.isChecked = false
                return
            }

            val text  = etUsuario.text.toString()
            editor.putString(KEY,text)
            editor.apply()
            Toast.makeText(this,"Almacenado con exito",Toast.LENGTH_SHORT).show()
        }
        else{
            if(mySharedPreferences.contains(KEY)){
                editor.remove(KEY)
                editor.apply()
                etUsuario.text?.clear()
                Toast.makeText(this,"Eliminado con exito",Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun login() {
        progressBar.visibility = View.VISIBLE
        viewLogin.visibility = View.GONE
        lifecycleScope.launch {
            var apiResponse: LoginResponse? = null
            var exception= ""
            withContext(Dispatchers.IO)
            {
                try {
                    apiResponse = ApiAdapter.apiClient.Login(etUsuario.text.toString(), etContra.text.toString())
                } catch (e: Exception) {
                    exception = e.localizedMessage
                }
            }

            if(apiResponse!=null){
                if (apiResponse!!.exito && apiResponse!!.isConfirmed) {
                    viewLogin.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    Toast.makeText(applicationContext, apiResponse?.mensaje, Toast.LENGTH_SHORT).show()

                    //Guardar información importante de la respuesta de la Api
                    Utils.personaId = apiResponse!!.personaId

                    launchActivity(apiResponse?.roles, apiResponse?.mensaje);
                } else {
                    Toast.makeText(applicationContext, apiResponse?.mensaje, Toast.LENGTH_SHORT).show()
                    progressBar.visibility = View.GONE
                    viewLogin.visibility = View.VISIBLE
                }
            }
            else{
                progressBar.visibility = View.GONE
                viewLogin.visibility = View.VISIBLE
                Toast.makeText(applicationContext, exception, Toast.LENGTH_LONG).show();
            }

        }
    }

    private fun launchActivity(roles:ArrayList<String>? , message:String?){
        try {
            val mIntent = Intent(applicationContext, MenuActivity::class.java)
            mIntent.putStringArrayListExtra(getString(R.string.keyNameRoles), roles)
            mIntent.putExtra(getString(R.string.keyNameWelcomeMsj), message)
            startActivity(mIntent)
        }catch (e:Exception){
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show();
        }
    }

    private fun initViews() {
        txtUsuario = binding.txtUsuario
        etUsuario = binding.ettUsuario
        txtContra = binding.txtContra
        etContra = binding.ettContra
        btnLogin = binding.btnIniciarSesion
        btnRegister = binding.btnRegistrarse
        switch = binding.switchGuardarInicio
        progressBar = binding.progressBar
        viewLogin = binding.viewLogin
    }

    override fun onBackPressed() {}
}
