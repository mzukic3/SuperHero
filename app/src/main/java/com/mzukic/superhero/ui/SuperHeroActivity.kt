package com.mzukic.superhero.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mzukic.superhero.R
import dagger.hilt.android.AndroidEntryPoint

class SuperHeroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_superhero)
    }
}
