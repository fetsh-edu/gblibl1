package me.fetsh.geekbrains.libraries.github.ui.screens.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import me.fetsh.geekbrains.libraries.github.App
import github.databinding.FragmentUsersBinding
import me.fetsh.geekbrains.libraries.github.db.Database
import me.fetsh.geekbrains.libraries.github.models.GithubUserRemote
import me.fetsh.geekbrains.libraries.github.navigation.BackButtonListener
import me.fetsh.geekbrains.libraries.github.ui.activity.MainActivity
import me.fetsh.geekbrains.libraries.github.ui.images.GlideImageLoader
import me.fetsh.geekbrains.libraries.github.utils.AndroidNetworkStatus
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var vb: FragmentUsersBinding? = null

    private val presenter by moxyPresenter {
        UsersPresenter(
            networkStatus = AndroidNetworkStatus(requireContext()),
            usersRepo = GithubUserRemote.Repo(),
            router = App.instance.router,
            db = Database.getInstance()
        )
    }

    private val adapter by lazy {
        UsersRVAdapter(
            presenter.usersListPresenter,
            GlideImageLoader()
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        return FragmentUsersBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(requireContext())
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }
}