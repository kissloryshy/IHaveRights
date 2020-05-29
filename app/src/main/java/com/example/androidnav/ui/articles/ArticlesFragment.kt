package com.example.androidnav.ui.articles

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.androidnav.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_articles.*


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

        var adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, titles)
        listView.adapter = adapter

//        For disable testing data comment this if
//        if (titles.isEmpty()) {
//            titles.add("Title one")
//            titles.add("Title two")
//            titles.add("Title three")
//            titles.add("Title four")
//            titles.add("Title five")
//            content.add("Content one Lorem Ipsum - это текст-\"рыба\", часто используемый в печати и вэб-дизайне. Lorem Ipsum является стандартной \"рыбой\" для текстов на латинице с начала XVI века. В то время некий безымянный печатник создал большую коллекцию размеров и форм шрифтов, используя Lorem Ipsum для распечатки образцов. Lorem Ipsum не только успешно пережил без заметных изменений пять веков, но и перешагнул в электронный дизайн. Его популяризации в новое время послужили публикация листов Letraset с образцами Lorem Ipsum в 60-х годах и, в более недавнее время, программы электронной вёрстки типа Aldus PageMaker, в шаблонах которых используется Lorem Ipsum.")
//            content.add("Content two Lorem Ipsum - это текст-\"рыба\", часто используемый в печати и вэб-дизайне. Lorem Ipsum является стандартной \"рыбой\" для текстов на латинице с начала XVI века. В то время некий безымянный печатник создал большую коллекцию размеров и форм шрифтов, используя Lorem Ipsum для распечатки образцов. Lorem Ipsum не только успешно пережил без заметных изменений пять веков, но и перешагнул в электронный дизайн. Его популяризации в новое время послужили публикация листов Letraset с образцами Lorem Ipsum в 60-х годах и, в более недавнее время, программы электронной вёрстки типа Aldus PageMaker, в шаблонах которых используется Lorem Ipsum.")
//            content.add("Content three Lorem Ipsum - это текст-\"рыба\", часто используемый в печати и вэб-дизайне. Lorem Ipsum является стандартной \"рыбой\" для текстов на латинице с начала XVI века. В то время некий безымянный печатник создал большую коллекцию размеров и форм шрифтов, используя Lorem Ipsum для распечатки образцов. Lorem Ipsum не только успешно пережил без заметных изменений пять веков, но и перешагнул в электронный дизайн. Его популяризации в новое время послужили публикация листов Letraset с образцами Lorem Ipsum в 60-х годах и, в более недавнее время, программы электронной вёрстки типа Aldus PageMaker, в шаблонах которых используется Lorem Ipsum.")
//            content.add("Content four Lorem Ipsum - это текст-\"рыба\", часто используемый в печати и вэб-дизайне. Lorem Ipsum является стандартной \"рыбой\" для текстов на латинице с начала XVI века. В то время некий безымянный печатник создал большую коллекцию размеров и форм шрифтов, используя Lorem Ipsum для распечатки образцов. Lorem Ipsum не только успешно пережил без заметных изменений пять веков, но и перешагнул в электронный дизайн. Его популяризации в новое время послужили публикация листов Letraset с образцами Lorem Ipsum в 60-х годах и, в более недавнее время, программы электронной вёрстки типа Aldus PageMaker, в шаблонах которых используется Lorem Ipsum.")
//            content.add("Content five Lorem Ipsum - это текст-\"рыба\", часто используемый в печати и вэб-дизайне. Lorem Ipsum является стандартной \"рыбой\" для текстов на латинице с начала XVI века. В то время некий безымянный печатник создал большую коллекцию размеров и форм шрифтов, используя Lorem Ipsum для распечатки образцов. Lorem Ipsum не только успешно пережил без заметных изменений пять веков, но и перешагнул в электронный дизайн. Его популяризации в новое время послужили публикация листов Letraset с образцами Lorem Ipsum в 60-х годах и, в более недавнее время, программы электронной вёрстки типа Aldus PageMaker, в шаблонах которых используется Lorem Ipsum.")
//
//            publicationDate.add("PublicationDate one")
//            publicationDate.add("PublicationDate two")
//            publicationDate.add("PublicationDate three")
//            publicationDate.add("PublicationDate four")
//            publicationDate.add("PublicationDate five")
//
//            source.add("Source one")
//            source.add("Source two")
//            source.add("Source three")
//            source.add("Source four")
//            source.add("Source five")
//
//            adapter.notifyDataSetChanged()
//        }

//        Button update
//        btnGetArticles.setOnClickListener {
            content.clear()
            publicationDate.clear()
            source.clear()
            titles.clear()

            val db = FirebaseFirestore.getInstance()

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
//        }

        listView.setOnItemClickListener { parent, view, position, id ->
            Log.d("position", position.toString())

            var args = Bundle()
            args.putString("position", position.toString())
            args.putStringArrayList("title", titles)
            args.putString("content", content[position])
            args.putString("source", source[position])
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
