package com.example.gb_libs_lesson1

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.gb_libs_lesson1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private val presenter = MainPresenter(this, CountersModel())

    private var vb: ActivityMainBinding? = null

    private val buttons : MutableList<Button> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        vb?.let{ vb ->
            buttons.add(vb.btnCounter1)
            buttons.add(vb.btnCounter2)
            buttons.add(vb.btnCounter3)
        }

        for((index, button) in buttons.withIndex()) {
            button.setOnClickListener{
                presenter.counterClick(index)
            }
        }
    }

    override fun setButtonText(index: Int, text: String) {
        buttons[index].text = text
    }
}