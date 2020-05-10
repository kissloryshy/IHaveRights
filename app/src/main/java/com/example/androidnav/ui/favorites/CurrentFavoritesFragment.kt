package com.example.androidnav.ui.favorites

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidnav.R
import kotlinx.android.synthetic.main.fragment_favorites_current.*


class CurrentFavoritesFragment : Fragment() {

    var bundle = Bundle()

    companion object {
        fun newInstance() =
            CurrentFavoritesFragment()
    }

    private lateinit var viewModel: CurrentFavoritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bundle = this!!.requireArguments()

        return inflater.inflate(R.layout.fragment_favorites_current, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrentFavoritesViewModel::class.java)

        tvFavContent.text = bundle.getString("content")
        tvFavTitle.text = bundle.getString("title")
        tvFavPublicationDate.text = bundle.getString("publicationDate")
        tvFavSource.text = bundle.getString("source")

        btnBackTest.setOnClickListener {
            var manager = childFragmentManager
            manager.popBackStackImmediate()
        }

    }

}
