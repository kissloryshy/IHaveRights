package com.example.androidnav.ui.articles

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.androidnav.R
import kotlinx.android.synthetic.main.fragment_current_article.*

class CurrentArticleFragment : Fragment() {

    var bundle = Bundle()

    companion object {
        fun newInstance() = CurrentArticleFragment()
    }

    private lateinit var viewModel: CurrentArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bundle = this!!.requireArguments()

        return inflater.inflate(R.layout.fragment_current_article, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrentArticleViewModel::class.java)
        // TODO: Use the ViewModel

        tvTitle.text = bundle.getString("title")
        tvContent.text = bundle.getString("content")
        tvPublicationDate.text = bundle.getString("publicationDate")
        tvSource.text = bundle.getString("source")


        var toast: Toast = Toast.makeText(context, "Hello from CurrentArticle", Toast.LENGTH_LONG)
        toast.show()
    }

}
