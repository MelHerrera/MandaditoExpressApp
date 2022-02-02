package com.elmandaditoexpress.mandaditoexpressapp

data class Intro(var name:String, var des:String?, var foto:Int?)
{
    companion object
    {
        var intros=  listOf(
            Intro(
                "GESTIONA",
                "Gestiona de forma fácil, rápida y ordenada, toda la información de tus transacciones.",
                R.drawable.onboard
            ),
            Intro(
                "AHORRA",
                "Ahorre tiempo y dinero en largas esperas de atención al cliente.",
                R.drawable.logo
            ),
            Intro(
                "DIVIERTETE",
                "Realiza distintas transacciones de manera intituiva",
                R.drawable.login
            )
        )
    }
}
