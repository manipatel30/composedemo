package com.jetpackcomposedemo.ui.components

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.jetpackcomposedemo.R


class FragmentComposeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        //Approach - 1
        /*supportFragmentManager
            .beginTransaction()
            .add(R.id.content, HomeFragment())
            .commit()*/

        //Approach - 2
        supportFragmentManager
            .beginTransaction()
            .add(R.id.content, HomeFragment2())
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}