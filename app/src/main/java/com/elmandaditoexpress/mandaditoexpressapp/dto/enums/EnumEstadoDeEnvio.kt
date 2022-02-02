package com.elmandaditoexpress.mandaditoexpressapp.dto.enums

enum class EnumEstadoDeEnvio(valor: Int, color: String) {
   Solicitud(1, "#1c84c6"),
    EnProceso(2, "#1ab394"),
    Realizado(3, "#23c6c8"),
    Rechazado(4, "#ed5565")
}