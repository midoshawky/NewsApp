package com.shawky.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shawky.newsapp.R
import com.shawky.newsapp.models.NewsModel
import com.shawky.newsapp.ui.adapter.NewsRvAdapter
import com.shawky.newsapp.databinding.FragmentItemListBinding
import com.shawky.newsapp.ui.viewModels.NewsViewModel
import kotlinx.serialization.Serializer
import java.io.Serializable

class ItemListFragment : Fragment(R.layout.fragment_item_list) {

    private var binding: FragmentItemListBinding? = null

    var newsList : ArrayList<NewsModel> = ArrayList()

    private val newsViewModel : NewsViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentItemListBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)


        val itemDetailFragmentContainer: View? = view.findViewById(R.id.item_detail_container)

        binding!!.newsRv.layoutManager = LinearLayoutManager(requireContext())
        binding!!.newsRv.adapter = NewsRvAdapter(newsList) { news ->
            Log.i("News", "News : ${news.title}")

            val bundle = Bundle()
            bundle.putSerializable("News",news)
            if (itemDetailFragmentContainer != null) {
                itemDetailFragmentContainer.findNavController()
                    .navigate(R.id.fragment_item_detail, bundle)
            } else {
                view.findNavController().navigate(R.id.show_item_detail, bundle)
            }

        }

        newsViewModel.newsLiveData.observe(viewLifecycleOwner){ newsData ->
            Log.i("News","News Here ${newsData.size} : $newsData ")
            newsList.clear()
            newsList.addAll(newsData)
            binding!!.progressBar.visibility = View.GONE
            binding!!.newsRv.adapter!!.notifyItemRangeInserted(0,newsList.size)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}