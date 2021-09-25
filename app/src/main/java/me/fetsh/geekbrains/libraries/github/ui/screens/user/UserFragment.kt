package me.fetsh.geekbrains.libraries.github.ui.screens.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.fetsh.geekbrains.libraries.github.App
import github.databinding.FragmentUserBinding
import me.fetsh.geekbrains.libraries.github.models.GithubUser
import me.fetsh.geekbrains.libraries.github.navigation.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment() : MvpAppCompatFragment(), UserView, BackButtonListener {
    companion object {
        private const val ARGUMENTS_USER = "user"
        @JvmStatic
        fun newInstance(user: GithubUser) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARGUMENTS_USER, user)
                }
            }
    }

    private var vb: FragmentUserBinding? = null

    private val presenter by moxyPresenter {
        val user : GithubUser? = arguments?.getParcelable(ARGUMENTS_USER)
        UserPresenter(
            GithubUser.Repo(),
            App.instance.router,
            user!!
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