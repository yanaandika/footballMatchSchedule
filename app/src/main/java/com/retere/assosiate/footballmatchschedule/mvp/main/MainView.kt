package com.retere.assosiate.footballmatchschedule.mvp.main

import com.retere.assosiate.footballmatchschedule.model.EventsItem

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showLastMatch(data : List<EventsItem>)
    fun shoNextMatch(data : List<EventsItem>)
}