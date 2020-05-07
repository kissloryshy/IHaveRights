package com.example.androidnav.ui.articles

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.example.androidnav.MainActivity
import com.example.androidnav.R
import com.google.firebase.firestore.FirebaseFirestore
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

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d("kiss", "hello from articles onCreateView")

        var toast: Toast = Toast.makeText(context, "hello from articles onCreateView", Toast.LENGTH_LONG)
        toast.show()

        btnArticles.setOnClickListener {
            var toast: Toast = Toast.makeText(context, "Hello from articles", Toast.LENGTH_LONG)
            toast.show()

            val db = FirebaseFirestore.getInstance()

            var list: ArrayList<String> = ArrayList()
            var adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, list)
            listView.adapter = adapter

            db.collection("Articles")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result!!) {
                            Log.d("kiss", document.id + " => " + document.data)
                            list.add(document.get("title").toString())
                            list.add(document.get("content").toString())
                            list.add(document.get("publicationDate").toString())
                            list.add(document.get("source").toString())
                            adapter.notifyDataSetChanged()
                        }
                    } else {
                        Log.w("kiss", "Error getting documents.", task.exception)
                    }
                }
        }

        btnNextFragment.setOnClickListener {
            var fragment = CurrentArticleFragment()
            var manager = parentFragmentManager
            var transaction = manager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}
