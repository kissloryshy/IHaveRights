package com.example.androidnav.ui.articles

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.androidnav.R
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_articles.*
import org.w3c.dom.Node


class ArticlesFragment : Fragment() {

    private lateinit var articlesViewModel: ArticlesViewModel

    var content: ArrayList<String> = ArrayList()
    var publicationDate: ArrayList<String> = ArrayList()
    var source: ArrayList<String> = ArrayList()
    var titles: ArrayList<String> = ArrayList()

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

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnArticles.setOnClickListener {
            val db = FirebaseFirestore.getInstance()

            var adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, titles)
            listView.adapter = adapter

            db.collection("Articles")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result!!) {
//                            Log.d("kiss", document.id + " => " + document.data)
                            content.add(document.get("content").toString())
                            publicationDate.add(document.get("publicationDate").toString())
                            source.add(document.get("source").toString())
                            titles.add(document.get("title").toString())
                            adapter.notifyDataSetChanged()
                        }
                    } else {
                        Log.w("kiss", "Error getting documents.", task.exception)
                    }
                }
        }

        //тест переход на новый фрагмент с параметром
        btnNextFragment.setOnClickListener {
            var args = Bundle()
            args.putString("data", "hello from prev fragment")

            var fragment = CurrentArticleFragment()
            fragment.arguments = args

            var manager = parentFragmentManager
            var transaction = manager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        listView.setOnItemClickListener { parent, view, position, id ->
            Log.d("position", position.toString())

            var args = Bundle()
            args.putString("content", content[position])
            args.putString("source", source[position])
            args.putString("title", titles[position])
            args.putString("publicationDate", publicationDate[position])

            var fragment = CurrentArticleFragment()
            fragment.arguments = args

            var manager = parentFragmentManager
            var transaction = manager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}
