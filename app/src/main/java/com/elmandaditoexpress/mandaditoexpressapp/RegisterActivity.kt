package com.elmandaditoexpress.mandaditoexpressapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.elmandaditoexpress.mandaditoexpressapp.databinding.ActivityRegisterBinding
import java.util.regex.Pattern


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.btnGuardar.setOnClickListener {
//            validarCampos()
//            val params = HashMap <String, String>()
//            params["nombre"]= txtNombres.editText?.text.toString().trim()
//            params["primerApellido"] =  txtPrimerApellido.editText?.text.toString().trim()
//            params["segundoApellido"] = txtSegundoApellido.editText?.text.toString().trim()
//            params["cedula"] = txtCedula.editText?.text.toString().trim()
//            params["correo"] =  txtCorreo.editText?.text.toString().trim()
//            params["movil"] = txtMovil.editText?.text.toString().trim()
//            params["genero"] = if(rbFemenino.isChecked) "Femenino" else "Masculino"
//            params["direccion"] = txtDireccion.editText?.text.toString().trim()
//            params["contrasena"] = txtContrasena.editText?.text.toString().trim()
//            params["isEmpresa"] = if(cbIsEmpresa.isChecked) "true" else "false"
//            params["nombreEmpresa"] = txtNombreEmpresa.editText?.text.toString().trim()
//            params["numeroRUC"] = txtNumeroRUC.editText?.text.toString().trim()
//            params["aceptaTerminos"] = if(cbAceptoTerminos.isChecked) "true" else "false"
//
//
//            lifecycleScope.launch {
//                var apiResponse = ResponseWsRegistro()
//                var exception = ""
//                withContext(Dispatchers.IO)
//                {
//                    try {
//                        apiResponse = ApiAdapter.apiClient.Registro(params)
//                    } catch (e: Exception) {
//                        exception = e.localizedMessage
//                    }
//                }
//
//                if(apiResponse != null){
//                    Toast.makeText(this@RegisterActivity,apiResponse.mensaje,Toast.LENGTH_SHORT).show()
//                }
//                else
//                    Toast.makeText(applicationContext, apiResponse.mensaje, Toast.LENGTH_LONG).show()
//
//            }
//        }

    }

    //igualdad de contraseña
    //Correo correcto
    //Numero de cedula, celular
    //Numerp RUC
//    private fun validarCampos(): Boolean {
//        var isValidate = true
//
//        val nombreInput: String = txtNombres.editText?.text.toString().trim()
//        val primerApellidoInput: String = txtPrimerApellido.editText?.text.toString().trim()
//        val segundoApellidoInput: String = txtSegundoApellido.editText?.text.toString().trim()
//        val cedulaInput: String = txtCedula.editText?.text.toString().trim()
//        val correoInput: String = txtCorreo.editText?.text.toString().trim()
//        val movilInput: String = txtMovil.editText?.text.toString().trim()
//        val direccionInput: String = txtDireccion.editText?.text.toString().trim()
//        val contrasenaInput: String = txtContrasena.editText?.text.toString().trim()
//        val repertircontrasenaInput: String = txtRepetirContrasena.editText?.text.toString().trim()
//        val nombreEmpresaInput: String = txtNombreEmpresa.editText?.text.toString().trim()
//        val numeroRUCInput: String = txtNumeroRUC.editText?.text.toString().trim()
//
//        when{
//            nombreInput.isEmpty() ->
//            {
//                Toast.makeText(this,"Campo Nombres requerido",Toast.LENGTH_SHORT).show()
//                return false
//            }
//            primerApellidoInput.isEmpty() ->
//            {
//                Toast.makeText(this,"Campo 1er apellido requerido",Toast.LENGTH_SHORT).show()
//                return false
//            }
//            segundoApellidoInput.isEmpty() ->
//            {
//                Toast.makeText(this,"Campo 2do apellido requerido",Toast.LENGTH_SHORT).show()
//                return false
//            }
//            cedulaInput.isEmpty() ->
//            {
//                Toast.makeText(this,"Campo cédula requerido",Toast.LENGTH_SHORT).show()
//                return false
//            }
//            cedulaInput.length > 16 || cedulaInput.length < 16 ->
//            {
//                Toast.makeText(this,"Cedula invalida o debe incluir guión",Toast.LENGTH_SHORT).show()
//            }
//
//            correoInput.isEmpty() ->
//            {
//                isValidate = false
//                txtCorreo.error = "Campo Requerido"
//                return isValidate
//            }
//
//            !validarEmail(correoInput) ->
//            {
//                Toast.makeText(this,"Email Invalido",Toast.LENGTH_SHORT).show()
//            }
//
//            movilInput.isEmpty()  ->
//            {
//                isValidate = false
//                txtMovil.error = "Campo Requerido"
//                return isValidate
//            }
//
//            movilInput.length > 8 || movilInput.length < 8 ->
//            {
//                Toast.makeText(this,"Numero Invalido",Toast.LENGTH_SHORT).show()
//            }
//
//            rbGenero.checkedRadioButtonId == -1 -> {
//                Toast.makeText(this,"Debe Seleccionar al menos un genero",Toast.LENGTH_SHORT).show()
//
//            }
//            direccionInput.isEmpty() ->
//            {
//                isValidate = false
//                txtDireccion.error = "Campo Requerido"
//                return isValidate
//            }
//            contrasenaInput.isEmpty() ->
//            {
//                isValidate = false
//                txtContrasena.error = "Campo Requerido"
//                return isValidate
//            }
//            repertircontrasenaInput.isEmpty() ->
//            {
//                isValidate = false
//                txtRepetirContrasena.error = "Campo Requerido"
//                return isValidate
//            }
//            contrasenaInput!=repertircontrasenaInput->
//            {
//                Toast.makeText(this,"Las contraseñas deben conincidir",Toast.LENGTH_SHORT).show()
//            }
//
//            cbIsEmpresa.isChecked ->{
//                if(nombreEmpresaInput.isEmpty())
//                {
//                    isValidate = false
//                    txtNombreEmpresa.error = "Campo Requerido"
//                    return isValidate
//                }
//                else if(numeroRUCInput.isEmpty())
//                {
//                    isValidate = false
//                    txtNumeroRUC.error = "Campo Requerido"
//                    return isValidate
//                }
//                else if(numeroRUCInput.length > 14 || numeroRUCInput.length < 14)
//                {
//                    Toast.makeText(this,"Numero RUC Invalido",Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            !cbAceptoTerminos.isChecked -> {
//                Toast.makeText(this,"Debe Aceptar los terminos y condiciones para registrarse en la plataforma",Toast.LENGTH_SHORT).show()
//            }
//
//        }
//
//        return isValidate
//    }

    private fun validarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

}