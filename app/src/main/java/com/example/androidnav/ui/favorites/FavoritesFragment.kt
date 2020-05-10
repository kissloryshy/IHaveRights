package com.example.androidnav.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.androidnav.R
import com.example.androidnav.data.UserInfo
import com.example.androidnav.ui.articles.CurrentArticleFragment
import kotlinx.android.synthetic.main.fragment_articles.*
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment() {

    private lateinit var galleryViewModel: FavoritesViewModel

    var navController: NavController? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
                ViewModelProviders.of(this).get(FavoritesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_favorites, container, false)
//        val textView: TextView = root.findViewById(R.id.text_gallery)
//        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var userInfo = UserInfo(context, "UserInfo", null, 1)
        userInfo.getArticles()

        var titles = ArrayList<String>()
        titles.add("title one")
        var adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, titles)
        listViewFav.adapter = adapter

        listViewFav.setOnItemClickListener { parent, view, position, id ->
            var args = Bundle()
            args.putString("title", "lorem ipsum")
            args.putString("content", "lorem ipsum")
            args.putString("publicationDate", "lorem ipsum")
            args.putString("source", "lorem ipsum")
//            var fragment = CurrentFavoritesFragment()
//            fragment.arguments = args
//            var manager = parentFragmentManager
//            var transaction = manager.beginTransaction()
//            transaction.replace(R.id.nav_host_fragment, fragment)
//            transaction.addToBackStack(null)
//            transaction.commit()

            navController!!.navigate(R.id.action_nav_favorites_to_currentFavoritesFragment, args)

        }
    }


}
