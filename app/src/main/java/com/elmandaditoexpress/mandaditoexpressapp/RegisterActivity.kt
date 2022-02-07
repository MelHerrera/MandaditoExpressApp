package com.elmandaditoexpress.mandaditoexpressapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.elmandaditoexpress.mandaditoexpressapp.databinding.ActivityRegisterBinding
import com.elmandaditoexpress.mandaditoexpressapp.dto.ResponseWsRegistro
import com.elmandaditoexpress.mandaditoexpressapp.network.ApiAdapter
import com.elmandaditoexpress.mandaditoexpressapp.services.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.regex.Pattern


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnGuardar.setOnClickListener {
            validarCampos()
            val params = HashMap <String, String>()
            params["nombre"]= binding.txtNombre.editText?.text.toString().trim()
            params["primerApellido"] = binding.txtPrimerApellido.editText?.text.toString().trim()
            params["segundoApellido"] = binding.txtSegundoApellido.editText?.text.toString().trim()
            params["cedula"] = binding.txtCedula.editText?.text.toString().trim()
            params["correo"] =  binding.txtCorreo.editText?.text.toString().trim()
            params["movil"] = binding.txtMovil.editText?.text.toString().trim()
            params["genero"] = if(binding.rbFemenino.isChecked) getString(R.string.femeninio) else getString(R.string.masculino)
            params["direccion"] = binding.txtDireccion.editText?.text.toString().trim()
            params["contrasena"] = binding.txtContra.editText?.text.toString().trim()
            params["isEmpresa"] = if(binding.cbIsEmpresa.isChecked) "true" else "false"
            params["nombreEmpresa"] = binding.txtNombreEmpresa.editText?.text.toString().trim()
            params["numeroRUC"] = binding.txtNumeroRuc.editText?.text.toString().trim()
            params["aceptaTerminos"] = if(binding.cbAceptoTermminos.isChecked) "true" else "false"

            lifecycleScope.launch {
                var apiResponse = ResponseWsRegistro()
                var exception = ""
                withContext(Dispatchers.IO)
                {
                    try {
                        apiResponse = ApiAdapter.apiClient.Registro(params)
                    } catch (e: Exception) {
                        exception = e.localizedMessage
                    }
                }

                if(apiResponse != null){
                    Toast.makeText(this@RegisterActivity,apiResponse.mensaje,Toast.LENGTH_SHORT).show()
                }
                else
                    Toast.makeText(applicationContext, apiResponse.mensaje, Toast.LENGTH_LONG).show()

            }
        }

    }

    //igualdad de contraseña
    //Correo correcto
    //Numero de cedula, celular
    //Numerp RUC
    private fun validarCampos(): Boolean {
        var isValidate = true

        val nombreInput: String = binding.txtNombre.editText?.text.toString().trim()
        val primerApellidoInput: String = binding.txtPrimerApellido.editText?.text.toString().trim()
        val segundoApellidoInput: String = binding.txtSegundoApellido.editText?.text.toString().trim()
        val cedulaInput: String = binding.txtCedula.editText?.text.toString().trim()
        val correoInput: String = binding.txtCorreo.editText?.text.toString().trim()
        val movilInput: String = binding.txtMovil.editText?.text.toString().trim()
        val direccionInput: String = binding.txtDireccion.editText?.text.toString().trim()
        val contrasenaInput: String = binding.txtContra.editText?.text.toString().trim()
        val repertircontrasenaInput: String = binding.txtRepetirContra.editText?.text.toString().trim()
        val nombreEmpresaInput: String = binding.txtNombreEmpresa.editText?.text.toString().trim()
        val numeroRUCInput: String = binding.txtNumeroRuc.editText?.text.toString().trim()

        when{
            nombreInput.isEmpty() ->
            {
                Toast.makeText(this,"Campo Nombres requerido",Toast.LENGTH_SHORT).show()
                return false
            }
            primerApellidoInput.isEmpty() ->
            {
                Toast.makeText(this,"Campo 1er apellido requerido",Toast.LENGTH_SHORT).show()
                return false
            }
            segundoApellidoInput.isEmpty() ->
            {
                Toast.makeText(this,"Campo 2do apellido requerido",Toast.LENGTH_SHORT).show()
                return false
            }
            cedulaInput.isEmpty() ->
            {
                Toast.makeText(this,"Campo cédula requerido",Toast.LENGTH_SHORT).show()
                return false
            }
            cedulaInput.length > 16 || cedulaInput.length < 16 ->
            {
                Toast.makeText(this,"Cedula invalida o debe incluir guión",Toast.LENGTH_SHORT).show()
            }

            correoInput.isEmpty() ->
            {
                isValidate = false
                binding.txtCorreo.error = "Campo Requerido"
                return isValidate
            }

            !Utils.validarEmail(correoInput) ->
            {
                Toast.makeText(this,"Email Invalido",Toast.LENGTH_SHORT).show()
            }

            movilInput.isEmpty()  ->
            {
                isValidate = false
                binding.txtMovil.error = "Campo Requerido"
                return isValidate
            }

            movilInput.length > 8 || movilInput.length < 8 ->
            {
                Toast.makeText(this,"Numero Invalido",Toast.LENGTH_SHORT).show()
            }

            binding.rgGenero.checkedRadioButtonId == -1 -> {
                Toast.makeText(this,"Debe Seleccionar al menos un genero",Toast.LENGTH_SHORT).show()

            }
            direccionInput.isEmpty() ->
            {
                isValidate = false
                binding.txtDireccion.error = "Campo Requerido"
                return isValidate
            }
            contrasenaInput.isEmpty() ->
            {
                isValidate = false
                binding.txtContra.error = "Campo Requerido"
                return isValidate
            }
            repertircontrasenaInput.isEmpty() ->
            {
                isValidate = false
                binding.txtRepetirContra.error = "Campo Requerido"
                return isValidate
            }
            contrasenaInput!=repertircontrasenaInput->
            {
                Toast.makeText(this,"Las contraseñas deben conincidir",Toast.LENGTH_SHORT).show()
            }

            binding.cbIsEmpresa.isChecked ->{
                if(nombreEmpresaInput.isEmpty())
                {
                    isValidate = false
                    binding.txtNombreEmpresa.error = "Campo Requerido"
                    return isValidate
                }
                else if(numeroRUCInput.isEmpty())
                {
                    isValidate = false
                    binding.txtNumeroRuc.error = "Campo Requerido"
                    return isValidate
                }
                else if(numeroRUCInput.length > 14 || numeroRUCInput.length < 14)
                {
                    Toast.makeText(this,"Numero RUC Invalido",Toast.LENGTH_SHORT).show()
                }
            }

            !binding.cbAceptoTermminos.isChecked -> {
                Toast.makeText(this,"Debe Aceptar los terminos y condiciones para registrarse en la plataforma",Toast.LENGTH_SHORT).show()
            }

        }

        return isValidate
    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}