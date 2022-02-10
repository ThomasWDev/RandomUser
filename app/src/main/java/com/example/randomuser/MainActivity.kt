package com.example.randomuser

import android.os.Bundle
import com.example.randomuser.core.base.BaseActivity
import com.example.randomuser.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * The entry point of the app
 */
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun onCreated(instance: Bundle?) {
        setSupportActionBar(binding.toolbar)
    }
}