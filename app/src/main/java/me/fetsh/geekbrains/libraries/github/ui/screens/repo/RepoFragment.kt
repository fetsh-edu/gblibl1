package me.fetsh.geekbrains.libraries.github.ui.screens.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import github.databinding.FragmentRepoBinding
import me.fetsh.geekbrains.libraries.github.App
import me.fetsh.geekbrains.libraries.github.models.GithubRepoUI
import me.fetsh.geekbrains.libraries.github.navigation.BackButtonListener
import me.fetsh.geekbrains.libraries.github.ui.activity.MainActivity
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoFragment : MvpAppCompatFragment(), RepoView, BackButtonListener {
    companion object {
        private const val ARGUMENTS_REPO = "repo"
        @JvmStatic
        fun newInstance(repo: GithubRepoUI) =
            RepoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARGUMENTS_REPO, repo)
                }
            }
    }

    private var vb: FragmentRepoBinding? = null

    private val presenter : RepoPresenter by moxyPresenter {
        val repo : GithubRepoUI? = arguments?.getParcelable(ARGUMENTS_REPO)

        App.instance.initRepoSubcomponent(repo!!)
            ?.presenter!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return FragmentRepoBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init(repo: GithubRepoUI) {
        vb?.repoName?.text = repo.name
        vb?.repoForksCount?.text = "Forks: ${repo?.forks}"
    }
}