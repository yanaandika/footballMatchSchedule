package com.retere.assosiate.footballmatchschedule.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.retere.assosiate.footballmatchschedule.R
import com.retere.assosiate.footballmatchschedule.model.EventsItem
import kotlinx.android.synthetic.main.list_match.view.*
import java.text.SimpleDateFormat

class MainAdapter(
    private val context: Context,
    private val events: List<EventsItem>,
    private val listener: (EventsItem) -> Unit
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MainAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_match, p0, false))
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindData(events[p1], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val dateMatch = view.tvDateMatch
        private val homeTeam = view.tvHomeTeam
        private val homeTeamScore = view.tvHomeScore
        private val awayTeam = view.tvAwayTeam
        private val awayTeamScore = view.tvAwayScore

        fun bindData(eventsItem: EventsItem, listener: (EventsItem) -> Unit) {
            var dateFormat = SimpleDateFormat("EEE, dd-MMMM-yyyy")
            dateMatch.text = dateFormat.parse(eventsItem.dateEvent).toString()
            homeTeam.text = eventsItem.strHomeTeam
            homeTeamScore.text = eventsItem.intHomeScore
            awayTeam.text = eventsItem.strAwayTeam
            awayTeamScore.text = eventsItem.intAwayScore

            itemView.setOnClickListener {
                listener(eventsItem)
            }
        }
    }
}