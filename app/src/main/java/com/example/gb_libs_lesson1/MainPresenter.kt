package com.example.gb_libs_lesson1

class MainPresenter(
    private val view: MainView,
    private val model: CountersModel
) {

    fun counterClick(id: Int) = view.setButtonText(id, model.next(id).toString())

}