package com.example.androidnav.ui.articles

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.androidnav.R

class CurrentArticleFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentArticleFragment()
    }

    private lateinit var viewModel: CurrentArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current_article, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrentArticleViewModel::class.java)
        // TODO: Use the ViewModel

        var toast: Toast = Toast.makeText(context, "Hello from CurrentArticle", Toast.LENGTH_LONG)
        toast.show()
    }

}
