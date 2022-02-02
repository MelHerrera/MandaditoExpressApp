package com.elmandaditoexpress.mandaditoexpressapp.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponse(
    @SerializedName("Exito") var exito : Boolean,
    @SerializedName("Mensaje") var mensaje : String,
    @SerializedName("IsConfirmed") var isConfirmed : Boolean,
    @SerializedName("Roles") var roles : ArrayList<String>,
    @SerializedName("PersonaId") var personaId : Int
):Serializable