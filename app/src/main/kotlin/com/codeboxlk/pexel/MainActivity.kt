package com.codeboxlk.pexel

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.codeboxlk.pexel.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.skydoves.bindables.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        setDecorFitsSystemWindows()
        super.onCreate(savedInstanceState)

    }

    private fun setDecorFitsSystemWindows() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}