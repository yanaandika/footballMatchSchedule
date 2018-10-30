package com.retere.assosiate.footballmatchschedule.mvp.main

import com.google.gson.Gson
import com.retere.assosiate.footballmatchschedule.api.ApiService
import com.retere.assosiate.footballmatchschedule.api.TheSportDBApi
import com.retere.assosiate.footballmatchschedule.model.EventsItemResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(
    private val view: MainView,
    private val apiService : ApiService,
    private val apiRepository: TheSportDBApi,
    private val gson: Gson
) {
    fun getLastMatch() {
        view.showLoading()
        doAsync {
            val data =
                gson.fromJson(
                    apiService.doRequest(TheSportDBApi.getLastMatch())
                    , EventsItemResponse::class.java
                )

            uiThread {
                view.hideLoading()
                view.showLastMatch(data.events)
            }
        }
    }

    fun getNextMatch() {
        view.showLoading()
        doAsync {
            val data =
                gson.fromJson(apiService.doRequest(TheSportDBApi.getNextMatch())
                    ,EventsItemResponse::class.java
                )
            uiThread {
                view.hideLoading()
                view.shoNextMatch(data.events)
            }
        }
    }
}