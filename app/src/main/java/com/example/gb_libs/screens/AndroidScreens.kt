package com.example.gb_libs.screens

import com.example.gb_libs.view.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object AndroidScreens {
    fun UsersScreen() = FragmentScreen { UsersFragment() }
}