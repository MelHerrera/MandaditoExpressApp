package com.elmandaditoexpress.mandaditoexpressapp.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseWsTipoDeServicio(
    @SerializedName("Id") var id: Int,
    @SerializedName("DescripcionTipoDeServicio") var descripcion: String,
    @SerializedName("EstadoTipoDeServicio") var estado: Boolean
)

data class ResponseWsTiposDeServicio(
    @SerializedName("TiposDeServicio") var tiposDeServicio : ArrayList<ResponseWsTipoDeServicio>
): Serializable, Response()