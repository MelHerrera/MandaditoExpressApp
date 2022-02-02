package com.elmandaditoexpress.mandaditoexpressapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.elmandaditoexpress.mandaditoexpressapp.R

class ResourcesDeniedFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val mView = inflater.inflate(R.layout.fragment_resources_denied, container, false)

        mView.findViewById<Button>(R.id.vBtnBack).setOnClickListener {
            this.activity?.finishAffinity()
        }

        return mView
    }
}