package me.fetsh.geekbrains.libraries.github.ui.screens.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import github.databinding.FragmentRepoBinding
import github.databinding.FragmentUserBinding
import me.fetsh.geekbrains.libraries.github.App
import me.fetsh.geekbrains.libraries.github.models.GithubRepo
import me.fetsh.geekbrains.libraries.github.navigation.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoFragment : MvpAppCompatFragment(), RepoView, BackButtonListener {
    companion object {
        private const val ARGUMENTS_REPO = "repo"
        @JvmStatic
        fun newInstance(repo: GithubRepo) =
            RepoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARGUMENTS_REPO, repo)
                }
            }
    }

    private var vb: FragmentRepoBinding? = null

    private val presenter by moxyPresenter {
        val repo : GithubRepo? = arguments?.getParcelable(ARGUMENTS_REPO)
        RepoPresenter(
            App.instance.router,
            repo!!
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
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

    override fun init(repo: GithubRepo) {
        vb?.repoName?.text = repo.name
        vb?.repoForksCount?.text = "Forks: ${repo?.forks}"
    }
}