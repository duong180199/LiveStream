package com.only.kotlinapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

open class BaseFragment : Fragment() {
    fun navigate(actionId: Int) = navigateFragment(actionId, null)

    fun navigate(actionId: Int, bundle: Bundle?) = navigateFragment(actionId, bundle)

    fun backToSpecificFragment(fragmentId: Int): Unit {
        val navController: NavController = NavHostFragment.findNavController(this)
        navController.popBackStack(fragmentId, false)
    }

    fun backToPrevious() {
        val navController: NavController = NavHostFragment.findNavController(this)
        navController.popBackStack()
    }

    private fun navigateFragment(actionId: Int, bundle: Bundle?) {
        val navController: NavController = NavHostFragment.findNavController(this)
        navController.navigate(actionId, bundle)
    }
}