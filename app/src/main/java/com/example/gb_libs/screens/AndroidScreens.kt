package com.example.gb_libs.screens

import com.example.gb_libs.view.UserFragment
import com.example.gb_libs.view.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object AndroidScreens {
    fun UsersScreen() = FragmentScreen { UsersFragment() }
//    fun UserScreen() = FragmentScreen { UserFragment() }
    fun UserScreen(userId: String) = FragmentScreen("Profile_$userId") { UserFragment().apply { this.userId = userId } }
}