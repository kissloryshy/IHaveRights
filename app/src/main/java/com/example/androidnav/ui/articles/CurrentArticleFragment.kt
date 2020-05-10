package com.example.androidnav.ui.articles

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi

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

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrentArticleViewModel::class.java)

        var titles = bundle.getStringArrayList("title")
        var position = bundle.getString("position")
        var currentTitle = titles?.get(position?.toInt()!!)

        tvTitle.text = currentTitle
        tvContent.text = bundle.getString("content")

        var publicationDate = bundle.getString("publicationDate")
        tvPublicationDate.text = publicationDate

        tvSource.text = bundle.getString("source")

        btnSaveArticle.setOnClickListener {
            var toast = Toast.makeText(context, "Сохранение", Toast.LENGTH_SHORT)
            toast.show()
        }

    }

}
