package com.example.shopsavvycommvp.widget

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

class NavData {
    var navHostFragment: NavHostFragment? = null
    var navController: NavController? = null
    var navHostFragments: ArrayList<NavHostFragment> = arrayListOf()
    var navControllers: ArrayList<NavController> = arrayListOf()
}