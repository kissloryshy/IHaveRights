package com.example.androidnav.ui.articles

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androidnav.R
import kotlinx.android.synthetic.main.fragment_articles.*

class ArticlesFragment : Fragment() {

    private lateinit var articlesViewModel: ArticlesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        articlesViewModel =
                ViewModelProviders.of(this).get(ArticlesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_articles, container, false)

//        val textView: TextView = root.findViewById(R.id.text_home)
//        articlesViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        Log.d("kiss", "hello from articles onCreateView")

        btnArticles.setOnClickListener {
            var toast: Toast = Toast.makeText(context, "Hello from articles", Toast.LENGTH_LONG)
            toast.show()
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}
