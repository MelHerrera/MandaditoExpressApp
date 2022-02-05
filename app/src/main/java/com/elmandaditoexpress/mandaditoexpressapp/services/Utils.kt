package com.elmandaditoexpress.mandaditoexpressapp.services

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.GradientDrawable
import com.elmandaditoexpress.mandaditoexpressapp.dto.enums.EnumEstadoDeEnvio
import kotlin.reflect.KMutableProperty1

class Utils {
    companion object
    {
        var personaId:Int = -1

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
    }
}