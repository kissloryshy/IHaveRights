package com.example.androidnav.ui.support

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.androidnav.R
import kotlinx.android.synthetic.main.fragment_support.*

class SupportFragment : Fragment() {

    companion object {
        fun newInstance() = SupportFragment()
    }

    private lateinit var viewModel: SupportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_support, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SupportViewModel::class.java)
    }

}
