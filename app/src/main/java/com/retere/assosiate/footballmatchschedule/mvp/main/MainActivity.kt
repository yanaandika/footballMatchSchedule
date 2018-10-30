package com.retere.assosiate.footballmatchschedule.mvp.main

import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.retere.assosiate.footballmatchschedule.MatchFragment
import com.retere.assosiate.footballmatchschedule.R
import kotlinx.android.synthetic.main.main_layout.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        supportActionBar?.title = "Football Match Schedule"
        showFragment("lastMatch")
        navigation.setOnNavigationItemSelectedListener(navigationSelected)
    }

    private fun showFragment(tag: String?) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.contentFragment, MatchFragment.newInstance(), tag)
            .commit()
    }

    private val navigationSelected = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.lastMatch -> {
                showFragment("lastMatch")
                return@OnNavigationItemSelectedListener true
            }
            R.id.nextMatch ->{
                showFragment("nextMatch")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}