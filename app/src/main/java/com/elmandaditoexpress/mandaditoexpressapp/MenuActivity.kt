package com.elmandaditoexpress.mandaditoexpressapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.elmandaditoexpress.mandaditoexpressapp.dto.enums.EnumRoles
import com.elmandaditoexpress.mandaditoexpressapp.fragments.AdminMenuFragment
import com.elmandaditoexpress.mandaditoexpressapp.fragments.ClienteMenuFragment
import com.elmandaditoexpress.mandaditoexpressapp.fragments.MotorizadoMenuFragment
import com.elmandaditoexpress.mandaditoexpressapp.fragments.ResourcesDeniedFragment

class MenuActivity : AppCompatActivity() {
    private val intervalo = 2000 //2 segundos para salir
    private var tiempoPrimerClick: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val userRoles:ArrayList<String> = intent.extras?.getStringArrayList(getString(R.string.keyNameRoles)) as ArrayList<String>
        val message:String? =  intent.extras?.getString(getString(R.string.keyNameWelcomeMsj))

        setMenu(userRoles, message)
    }

    private fun setMenu(roles:ArrayList<String>, message:String?){
        val mFrag: Fragment = when {
            roles.contains(EnumRoles.Cliente.toString()) -> ClienteMenuFragment(message)
            roles.contains(EnumRoles.Motorizado.toString()) -> MotorizadoMenuFragment(message)
//            roles.contains(EnumRoles.Admin.toString()) -> AdminMenuFragment()
            else -> ResourcesDeniedFragment()
        }
        supportFragmentManager.beginTransaction().replace(R.id.mainMenuContainer,mFrag,null).commit()
    }

    override fun onBackPressed() {
        if (tiempoPrimerClick + intervalo > System.currentTimeMillis()){
            super.onBackPressed()
            this.finishAffinity()
        }
        else
            Toast.makeText(this, "Presiona 2 Veces Para Salir", Toast.LENGTH_SHORT).show()

        tiempoPrimerClick = System.currentTimeMillis()
    }
}