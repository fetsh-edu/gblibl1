package com.example.gb_libs.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gb_libs.App
import com.example.gb_libs.databinding.FragmentUserBinding
import com.example.gb_libs.model.GithubUser

import com.example.gb_libs.model.GithubUsersRepo
import com.example.gb_libs.presentation.UserPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment() : MvpAppCompatFragment(), UserView, BackButtonListener {

    private var vb: FragmentUserBinding? = null

    var userId : String = ""

    private val presenter by moxyPresenter {
        UserPresenter(
            GithubUsersRepo(),
            App.instance.router,
            userId
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentUserBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    }

    override fun init(user: GithubUser?) {
        vb?.userLogin?.text = user?.login
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }
}