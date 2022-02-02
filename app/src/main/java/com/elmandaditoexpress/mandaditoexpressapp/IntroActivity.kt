package com.elmandaditoexpress.mandaditoexpressapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.elmandaditoexpress.mandaditoexpressapp.adapters.IntroAdapter
import com.elmandaditoexpress.mandaditoexpressapp.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    private lateinit var binding : ActivityIntroBinding
    private lateinit var carrusel: ViewPager2
    private lateinit var btnIntro: Button
    private lateinit var indicatorsContainer: LinearLayout
    private val introAdapter = IntroAdapter(Intro.intros)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        carrusel = binding.carrusel
        btnIntro = binding.BtnIntro
        indicatorsContainer = binding.indicatorsContainer

        carrusel.adapter = introAdapter
        setupIndicators()
        setCurrentIndicator(0)

        carrusel.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })

        btnIntro.setOnClickListener {
            setPreferencias()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun setPreferencias() {
        val prefs = Preferencias()

        if(!prefs.guardarSharedPrefsIntro(this,
                setOf(getString(R.string.keyNameIntro),1.toString())))
        {
            Toast.makeText(this,"Error al guardar Preferencias", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(introAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        layoutParams.setMargins(12, 0, 12, 0)

        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            indicatorsContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index:Int)
    {
        val childCount = indicatorsContainer.childCount
        for(i in 0 until childCount){
            val imageView = indicatorsContainer[i] as ImageView
            if(i == index)
            {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }

    override fun onBackPressed() {}


}