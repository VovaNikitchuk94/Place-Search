package com.vnykyt.placesearch.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.vnykyt.placesearch.presentation.R

class AppActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navController = findNavController(R.id.nav_host_fragment)
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

}