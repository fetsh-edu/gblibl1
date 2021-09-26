package me.fetsh.geekbrains.libraries.github.ui.screens.user

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import github.databinding.ItemUserBinding
import me.fetsh.geekbrains.libraries.github.ui.contracts.RVContract

class ReposRVAdapter (
    private val presenter: RVContract.ReposListPresenter,
) : RecyclerView.Adapter<ReposRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }
    }

    override fun getItemCount(): Int = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    inner class ViewHolder(
        private val vb: ItemUserBinding
        ) :
        RecyclerView.ViewHolder(vb.root),
        RVContract.RepoItemView {

        override fun showName(login: String) {
            vb.tvLogin.text = login
            vb.repoIcon.visibility = View.VISIBLE
            vb.avatarImageView.visibility = View.GONE
        }

        override var pos: Int = -1
    }
}