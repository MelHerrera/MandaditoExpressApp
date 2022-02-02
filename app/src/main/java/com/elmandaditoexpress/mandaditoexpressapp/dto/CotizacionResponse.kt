package com.elmandaditoexpress.mandaditoexpressapp.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseWsCotizacion(
    @SerializedName("Id") var id : Int,
    @SerializedName("DescripcionDeCotizacion") var descripcion : String,
    @SerializedName("CodigoDeCotizacion") var codigo : String,
    @SerializedName("FechaDeLaCotizacion") var fecha : String,
    @SerializedName("FechaDeValidez") var fechaDeValidez : String,
    @SerializedName("LugarDestino") var lugarDestino : String,
    @SerializedName("DistanciaOrigenDestino") var distanciaOD : Float,
    @SerializedName("MontoDeDinero") var montoDinero : Double,
    @SerializedName("EsEspecial") var urgente : Boolean,
    @SerializedName("MontoTotal") var montoTotal : Double,
    @SerializedName("ClienteId") var clienteId : Int,
    @SerializedName("ClienteNombres") var nombresDelCliente : String,
    @SerializedName("TipoDeServicioId") var tipoDeServicioId : Int,
    @SerializedName("TipoDeServicioDescripción") var tipoDeServicioDescripcion : String
)

data class ResponseWsCotizaciones(
    @SerializedName("Cotizaciones") var cotizaciones : ArrayList<ResponseWsCotizacion>
): Serializable, Response()