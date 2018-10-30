package com.retere.assosiate.footballmatchschedule

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import com.retere.assosiate.footballmatchschedule.R.color.colorPrimary
import com.retere.assosiate.footballmatchschedule.adapter.MainAdapter
import com.retere.assosiate.footballmatchschedule.api.ApiService
import com.retere.assosiate.footballmatchschedule.api.TheSportDBApi
import com.retere.assosiate.footballmatchschedule.model.EventsItem
import com.retere.assosiate.footballmatchschedule.mvp.main.MainPresenter
import com.retere.assosiate.footballmatchschedule.mvp.main.MainView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*

class MatchFragment: Fragment(),MainView{

    private lateinit var progressBar: ProgressBar
    private lateinit var listEvent : RecyclerView
    private lateinit var refreshLayout: SwipeRefreshLayout

    private var events : MutableList<EventsItem> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter : MainAdapter


    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showLastMatch(data: List<EventsItem>) {
        refreshLayout.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun shoNextMatch(data: List<EventsItem>) {
        refreshLayout.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance() : MatchFragment {
            var lastMatchFragment = MatchFragment()
            var args = Bundle()
            lastMatchFragment.arguments = args
            return lastMatchFragment
        }


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view =  UI {
            verticalLayout(){
                lparams(width = matchParent , height = wrapContent)
                padding = dip(16)
                refreshLayout = swipeRefreshLayout {
                    setColorSchemeResources(colorPrimary,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                    relativeLayout{
                        lparams (width = matchParent, height = wrapContent)

                        listEvent = recyclerView {
                            lparams (width = matchParent, height = wrapContent)
                            layoutManager = LinearLayoutManager(ctx)
                        }

                        progressBar = progressBar {
                        }.lparams{
                            centerHorizontally()
                        }
                    }
                }
            }

        }.view


        adapter = MainAdapter(ctx,events){
            //startActivity(intentFor<(DetailActivity)>("id" to it.idEvent))
        }

        listEvent.adapter = adapter

        val request = ApiService()
        val gson = Gson()
        val repository = TheSportDBApi
        presenter = MainPresenter(this,request,repository,gson)

        initData()
        refresh()
        return view
    }

    private fun refresh() {
        if (tag == "nextMatch"){
            refreshLayout.onRefresh { presenter.getNextMatch() }
        } else{
            refreshLayout.onRefresh { presenter.getLastMatch() }
        }
    }

    private fun initData() {
        if (tag == "nextMatch"){
            presenter.getNextMatch()
        } else {
            presenter.getLastMatch()
        }
    }

    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.invisible() {
        visibility = View.INVISIBLE
    }
}