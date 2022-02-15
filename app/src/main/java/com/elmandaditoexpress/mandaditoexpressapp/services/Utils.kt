package com.elmandaditoexpress.mandaditoexpressapp.services

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.GradientDrawable
import android.util.Patterns
import com.elmandaditoexpress.mandaditoexpressapp.dto.enums.EnumEstadoDeEnvio
import com.elmandaditoexpress.mandaditoexpressapp.dto.enums.EnumRoles
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.ArrayList
import kotlin.reflect.KMutableProperty1

class Utils {
    companion object
    {
        var personaId:Int = -1
        var personaRol:EnumRoles? = null

        fun <T, Y> ArrayList<T>.arrayListValuesOfField(property: KMutableProperty1<T, Y>): ArrayList<Y> {
            val output = ArrayList<Y>()

            this.forEach {t: T ->
                output.add(property.get(t))
            }
            return output
        }

        fun ByteArray.toBitmap(): Bitmap {
            return BitmapFactory.decodeByteArray(this,0,size)
        }

        fun getEnumEstadoDelEnvioValueOf(valor:Int): EnumEstadoDeEnvio {
            return when(valor){
                EnumEstadoDeEnvio.SOLICITUD.valor -> EnumEstadoDeEnvio.SOLICITUD
                EnumEstadoDeEnvio.ENPROCESO.valor -> EnumEstadoDeEnvio.ENPROCESO
                EnumEstadoDeEnvio.REALIZADO.valor -> EnumEstadoDeEnvio.REALIZADO
                EnumEstadoDeEnvio.RECHAZADO.valor -> EnumEstadoDeEnvio.RECHAZADO
                else -> EnumEstadoDeEnvio.PREDETERMINADO
            }
        }
        fun getEnumEstadoDelEnvioValueOf(name:String): Int {
            return when(name.uppercase(Locale.getDefault())){
                EnumEstadoDeEnvio.SOLICITUD.name -> EnumEstadoDeEnvio.SOLICITUD.valor
                EnumEstadoDeEnvio.ENPROCESO.name -> EnumEstadoDeEnvio.ENPROCESO.valor
                EnumEstadoDeEnvio.REALIZADO.name -> EnumEstadoDeEnvio.REALIZADO.valor
                EnumEstadoDeEnvio.RECHAZADO.name -> EnumEstadoDeEnvio.RECHAZADO.valor
                else -> EnumEstadoDeEnvio.PREDETERMINADO.valor
            }
        }

        fun GradientDrawable.createDrawable(
            color: Int,
            width: Int,
            height: Int,
            shape: Int,
            topLeftRadius: Float,
            topRightRadius: Float,
            bottomRightRadius: Float,
            bottomLeftRadius:Float
        ): GradientDrawable {
            val mDrawable = GradientDrawable()

            mDrawable.color = ColorStateList.valueOf(color)
            mDrawable.setSize(width, height)
            mDrawable.shape = shape
            mDrawable.cornerRadii = floatArrayOf(topLeftRadius, topLeftRadius, topRightRadius, topRightRadius, bottomRightRadius, bottomRightRadius, bottomLeftRadius, bottomLeftRadius)
            return mDrawable
        }

        fun validarEmail(email: String): Boolean {
            val pattern: Pattern = Patterns.EMAIL_ADDRESS
            return pattern.matcher(email).matches()
        }
    }
}