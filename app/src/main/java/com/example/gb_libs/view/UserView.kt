package com.example.gb_libs.view

import com.example.gb_libs.model.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UserView : MvpView {
    fun init(user: GithubUser?)
}