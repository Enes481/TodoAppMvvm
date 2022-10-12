package com.enestigli.todoapp.presentation.home.main


import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.enestigli.todoapp.R
import com.enestigli.todoapp.base.BaseBindingActivity
import com.enestigli.todoapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseBindingActivity<ActivityMainBinding,MainViewModel>() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override val layoutId: Int = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    override fun onReady(savedInstanceState: Bundle?) {
        super.onReady(savedInstanceState)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}