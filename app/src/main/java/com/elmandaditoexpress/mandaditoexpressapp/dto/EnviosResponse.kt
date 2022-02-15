package com.elmandaditoexpress.mandaditoexpressapp.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseWsEnvio(
    @SerializedName("Id") var id: Int,
    @SerializedName("CodigoDeEnvio") var codigo: String,
    @SerializedName("DescripcionDeEnvio") var descripcion: String,
    @SerializedName("FechaDelEnvio") var fecha: String,
    @SerializedName("MontoTotalDelEnvio") var montoTotal: Double,
    @SerializedName("Cliente") var nombresDelCliente: String,
    @SerializedName("NombresDelMotorizado") var nombresDelMotorizado: String,
    @SerializedName("MotivoDeRechazo") var motivoRechazo: String,
    @SerializedName("EstadoDelEnvio") var estado: Int
)

data class ResponseWsEnvios(
    @SerializedName("Envios") var envios: ArrayList<ResponseWsEnvio>
) : Serializable, Response()

class ResponseWsFinalizarEnvio(
) : Serializable, Response()