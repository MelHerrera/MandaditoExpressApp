package com.elmandaditoexpress.mandaditoexpressapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.elmandaditoexpress.mandaditoexpressapp.dto.enums.EnumRoles
import com.elmandaditoexpress.mandaditoexpressapp.fragments.AdminMenuFragment
import com.elmandaditoexpress.mandaditoexpressapp.fragments.ClienteMenuFragment
import com.elmandaditoexpress.mandaditoexpressapp.fragments.MotorizadoMenuFragment
import com.elmandaditoexpress.mandaditoexpressapp.fragments.ResourcesDeniedFragment

class MenuActivity : AppCompatActivity() {
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
            roles.contains(EnumRoles.Motorizado.toString()) -> MotorizadoMenuFragment()
//            roles.contains(EnumRoles.Admin.toString()) -> AdminMenuFragment()
            else -> ResourcesDeniedFragment()
        }
        supportFragmentManager.beginTransaction().replace(R.id.mainMenuContainer,mFrag,null).commit()
    }

    //override fun onBackPressed() {}
}