package com.elmandaditoexpress.mandaditoexpressapp.services

import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
    }
}