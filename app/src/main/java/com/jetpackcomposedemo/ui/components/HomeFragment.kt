package com.jetpackcomposedemo.ui.components

import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.jetpackcomposedemo.R

class HomeFragment : Fragment(R.layout.home_fragment){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ComposeView>(R.id.compose_view).setContent {
            UserCard()
        }

    }
}