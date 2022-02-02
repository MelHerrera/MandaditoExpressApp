package com.elmandaditoexpress.mandaditoexpressapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_Material_NoActionBar_SplashScreen)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runBlocking {
            GlobalScope.launch()
            {
                if(obtenerPreferenciasIntro() <= 0)
                {
                    startActivity(Intent(applicationContext, IntroActivity::class.java))
                }
                else{
                    startActivity(Intent(applicationContext, LoginActivity::class.java))
                }

            }
        }


    }

    private fun obtenerPreferenciasIntro():Int
    {
        val mySharedPrefs= Preferencias()
        return mySharedPrefs.obtenerSharedPrefsIntro(this,getString(R.string.keyNameIntro))
    }
}