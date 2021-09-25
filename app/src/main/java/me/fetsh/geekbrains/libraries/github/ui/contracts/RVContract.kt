package me.fetsh.geekbrains.libraries.github.ui.contracts

interface RVContract {
    interface ItemView {
        var pos: Int
    }

    interface UserItemView : ItemView {
        fun showLogin(login: String)
        fun loadAvatar(url: String)
    }

    interface ListPresenter<V : ItemView> {
        var itemClickListener: ((V) -> Unit)?
        fun bindView(view: V)
        fun getCount(): Int
    }

    interface UserListPresenter : ListPresenter<UserItemView>

}