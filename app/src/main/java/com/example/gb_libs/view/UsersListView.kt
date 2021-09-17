package com.example.gb_libs.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UsersListView: MvpView {
    fun init()
    fun updateList()
}