package com.elmandaditoexpress.mandaditoexpressapp.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseWsImagenPersona(
    @SerializedName("Imagen") var imagen:ByteArray
): Serializable, Response() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ResponseWsImagenPersona

        if (!imagen.contentEquals(other.imagen)) return false

        return true
    }

    override fun hashCode(): Int {
        return imagen.contentHashCode()
    }
}

class ResponseWsResetPassword(): Serializable, Response()