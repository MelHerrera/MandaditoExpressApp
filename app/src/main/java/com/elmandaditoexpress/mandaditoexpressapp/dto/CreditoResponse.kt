package com.elmandaditoexpress.mandaditoexpressapp.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseWsCredito(
    @SerializedName("Id") var id : Int,
    @SerializedName("CodigoDelCredito") var codigoDelCredito : String,
    @SerializedName("Descripcion") var descripcion : String,
    @SerializedName("FechaDeInicio") var fechaDeInicio : String,
    @SerializedName("FechaDeVencimiento") var fechaDeVencimiento : String,
    @SerializedName("MontoDelCredito") var monto : Double,
    @SerializedName("FechaDeCancelacion") var fechaDeCancelacion : String,
    @SerializedName("ClienteId") var clienteId : Int,
    @SerializedName("ClienteNombres") var NombresDelCliente : String
)

data class ResponseWsCreditos(
    @SerializedName("Creditos") var creditos : ArrayList<ResponseWsCredito>
):Serializable, Response()