package com.elmandaditoexpress.mandaditoexpressapp.dto

import com.google.gson.annotations.SerializedName

open class Response {
    @SerializedName("Exito") val exito:Boolean = false
    @SerializedName("Mensaje") var mensaje : String = ""
}