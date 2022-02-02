package com.elmandaditoexpress.mandaditoexpressapp.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseWsPago(
    @SerializedName("Id") var id: Int,
    @SerializedName("NumeroDePago") var numeroDePago: String,
    @SerializedName("FechaDePago") var fechaDePago: String,
    @SerializedName("MontoDelPago") var montoDelPago: Double,
    @SerializedName("Cambio") var cambio: Double,
    @SerializedName("MonedaDescripcion") var moneda: String,
    @SerializedName("TipoDePagoDescripcion") var tipoDePago: String,
    @SerializedName("EnvioCodigo") var envioCodigo: String?,
    @SerializedName("CreditoCodigo") var creditoCodigo: String?,
    @SerializedName("ConceptoDelPago") var conceptoDelPago: String,
    @SerializedName("ClienteNombres") var nombresDelCliente: String
)

data class ResponseWsPagos(
    @SerializedName("Pagos") var pagos : ArrayList<ResponseWsPago>
): Serializable, Response()