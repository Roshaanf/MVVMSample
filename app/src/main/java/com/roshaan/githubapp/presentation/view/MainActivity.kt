package com.roshaan.githubapp.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.roshaan.githubapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        checking savedInstanceState to avoid loading fragment again on configurationChange
        if (savedInstanceState == null)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, RepositoriesFragment())
                .commit()
    }
}