package com.example.gb_libs.presentation

import com.example.gb_libs.view.ItemView

interface ListPresenter<V : ItemView> {

    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int

}