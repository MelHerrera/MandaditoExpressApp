package com.elmandaditoexpress.mandaditoexpressapp.network

import com.elmandaditoexpress.mandaditoexpressapp.dto.*
import retrofit2.http.*

interface ApiClient {

    //iniciar sesion
    @POST("LoginWS/Login")
    suspend fun Login(
        @Query("user") user: String,
        @Query("pass") pass: String)
    : LoginResponse

    //Obtener los créditos de un cliente
    @GET("CreditosWS/GetCreditosDelCliente")
    suspend fun getCreditosDelCliente(
        @Query("ClienteId") ClienteId:Int)
    : ResponseWsCreditos

    //Obtener los pagos de un cliente
    @GET("PagosWS/GetPagosDelCliente")
    suspend fun getPagosDelCliente(
        @Query("ClienteId") ClienteId:Int)
            : ResponseWsPagos

    //Obtener los tipos de servicio
    @GET("TipoDeServicioWS/GetAllTiposDeServicio")
    suspend fun getTiposDeServicio(
    ): ResponseWsTiposDeServicio

    //Obtener cotizaciones de un cliente filtrada por tipos de servicio
    @GET("CotizacionesWS/GetCotizacionesDelClienteXTipoServicio")
    suspend fun getCotizacionesClienteTipoDeServicio(
        @Query("TipoDeServicio") tipoDeServicioId:Int,
        @Query("ClienteId") clienteId:Int
    ): ResponseWsCotizaciones

    //Obtener la imagen de perfil de una persona
    @GET("PersonaWS/GetImagePerfilPersona")
    suspend fun getImagenPerfilPersona(
        @Query("PersonaId") personaId:Int,
    ):ResponseWsImagenPersona

    //Registro
    @FormUrlEncoded
    @POST("PersonaWS/Registro")
    suspend fun Registro(@FieldMap registro: HashMap<String, String>
    ): ResponseWsRegistro

    //Obtener envios de un cliente
    @GET("EnviosWS/GetEnviosDelCliente")
    suspend fun getEnviosDelCliente(
        @Query("ClienteId") clienteId:Int
    ): ResponseWsEnvios

    //Enviar vinculo de reseto mediante correo electrónico
    @POST("PersonaWS/ResetPassword")
    suspend fun resetPassword(
        @Query("Email") email: String
    ): ResponseWsResetPassword
}