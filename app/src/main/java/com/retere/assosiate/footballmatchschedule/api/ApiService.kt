package com.retere.assosiate.footballmatchschedule.api

import java.net.URL

class ApiService{
    fun doRequest(url:String):String{
        return URL(url).readText()
    }
}