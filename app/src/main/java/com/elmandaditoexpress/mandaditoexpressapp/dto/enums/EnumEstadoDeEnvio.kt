package com.elmandaditoexpress.mandaditoexpressapp.dto.enums

enum class EnumEstadoDeEnvio(val valor: Int, val color: String) {
   SOLICITUD(1, "#1c2dc6"),
    ENPROCESO(2, "#1ab394"),
    REALIZADO(3, "#23c6c8"),
    RECHAZADO(4, "#e6071e"),
    PREDETERMINADO(-1,"#ffffff");
}