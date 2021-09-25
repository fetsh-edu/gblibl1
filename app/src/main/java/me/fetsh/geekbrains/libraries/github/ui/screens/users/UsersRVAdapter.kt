package me.fetsh.geekbrains.libraries.github.ui.screens.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import github.databinding.ItemUserBinding
import me.fetsh.geekbrains.libraries.github.ui.contracts.RVContract
import me.fetsh.geekbrains.libraries.github.ui.images.GlideImageLoader

class UsersRVAdapter(
    private val presenter: RVContract.UserListPresenter,
    private val imageLoader: GlideImageLoader
) : RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

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

    inner class ViewHolder(private val vb: ItemUserBinding) : RecyclerView.ViewHolder(vb.root),
        RVContract.UserItemView {

        override var pos: Int = -1

        override fun showLogin(login: String) {
            vb.tvLogin.text = login
        }

        override fun loadAvatar(url: String) {
            imageLoader.loadTo(url, vb.avatarImageView)
        }
    }
}