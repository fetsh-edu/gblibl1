package com.example.gb_libs.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_libs.databinding.ItemUserBinding
import com.example.gb_libs.presentation.UserListPresenter


class UsersRVAdapter(
    private val presenter: UserListPresenter
) : RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    class ViewHolder(
        private val vb: ItemUserBinding
    ) : RecyclerView.ViewHolder(vb.root), UserItemView {
        override fun setLogin(text: String) {
            vb.tvLogin.text = text
        }

        override var pos: Int = -1

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount(): Int {
        return presenter.getCount()
    }
}