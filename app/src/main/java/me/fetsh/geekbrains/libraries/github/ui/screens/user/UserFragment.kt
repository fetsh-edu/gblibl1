package me.fetsh.geekbrains.libraries.github.ui.screens.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import github.databinding.FragmentUserBinding
import me.fetsh.geekbrains.libraries.github.App
import me.fetsh.geekbrains.libraries.github.db.Database
import me.fetsh.geekbrains.libraries.github.models.GithubRepoRemote
import me.fetsh.geekbrains.libraries.github.models.GithubUserRemote
import me.fetsh.geekbrains.libraries.github.models.GithubUserUI
import me.fetsh.geekbrains.libraries.github.navigation.BackButtonListener
import me.fetsh.geekbrains.libraries.github.ui.activity.MainActivity
import me.fetsh.geekbrains.libraries.github.ui.images.GlideImageLoader
import me.fetsh.geekbrains.libraries.github.utils.AndroidNetworkStatus
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment() : MvpAppCompatFragment(), UserView, BackButtonListener {
    companion object {
        private const val ARGUMENTS_USER = "user"
        @JvmStatic
        fun newInstance(user: GithubUserUI) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARGUMENTS_USER, user)
                }
            }
    }

    private var vb: FragmentUserBinding? = null

    private val presenter by moxyPresenter {
        val user : GithubUserUI? = arguments?.getParcelable(ARGUMENTS_USER)
        UserPresenter(
            reposRepo = GithubRepoRemote.Repo(),
            usersRepo = GithubUserRemote.Repo(),
            router = App.instance.router,
            user = user!!,
            db = Database.getInstance(),
            networkStatus = AndroidNetworkStatus(requireContext()),
        )
    }

    private val imageLoader by lazy {
        GlideImageLoader()
    }

    private val adapter by lazy {
        ReposRVAdapter(
            presenter.reposListPresenter,
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return FragmentUserBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    }

    override fun init(user: GithubUserUI?) {
        vb?.repositories?.isNestedScrollingEnabled = false
        vb?.repositories?.adapter = adapter
        vb?.userLogin?.text = "@${user?.login}"
        user?.avatarUrl?.let{ url ->
            vb?.avatarImageView?.let {
                imageLoader.loadTo(url, it)
            }
        }
    }

    override fun showFullUserData(user: GithubUserUI) {
        vb?.userName?.visibility = View.VISIBLE
        vb?.userName?.text = user.name
        vb?.followersCount?.text = user.followers.toString()
        vb?.followingCount?.text = user.following.toString()
    }

    override fun showLoading() {
        vb?.progressBar?.visibility = View.VISIBLE
        vb?.repoHeader?.visibility = View.INVISIBLE
        vb?.repoHeaderHR?.visibility = View.INVISIBLE
        vb?.repositories?.visibility = View.INVISIBLE
    }

    override fun updateReposList() {
        vb?.progressBar?.visibility = View.GONE
        vb?.repoHeader?.visibility = View.VISIBLE
        vb?.repoHeaderHR?.visibility = View.VISIBLE
        vb?.repositories?.visibility = View.VISIBLE
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