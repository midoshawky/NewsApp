package com.shawky.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shawky.newsapp.R
import com.shawky.newsapp.data.services.State
import com.shawky.newsapp.databinding.FragmentItemListBinding
import com.shawky.newsapp.models.NewsModel
import com.shawky.newsapp.ui.adapter.NewsRvAdapter
import com.shawky.newsapp.ui.viewModels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemListFragment : Fragment() {


    private var newsList: ArrayList<NewsModel> = ArrayList()

    private val newsViewModel: NewsViewModel by viewModels()

    private var _binding: FragmentItemListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root

    }

    @Suppress("UNCHECKED_CAST")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemDetailFragmentContainer: View? = view.findViewById(R.id.item_detail_nav_container)

        binding.newsRv.layoutManager = LinearLayoutManager(requireContext())
        binding.newsRv.adapter = NewsRvAdapter(newsList) { news ->
            Log.i("News", "News : ${news.title}")

            val bundle = Bundle()
            bundle.putSerializable("News", news)
            if (itemDetailFragmentContainer != null) {
                itemDetailFragmentContainer.findNavController()
                    .navigate(R.id.fragment_item_detail, bundle)
            } else {
                view.findNavController().navigate(R.id.show_item_detail, bundle)
            }

        }

        newsViewModel.newsStateLiveData.observe(viewLifecycleOwner) { newsDataState ->
            Log.i("News", "News Here ${newsDataState.state} : ")
            when (newsDataState.state) {
                State.LOADING -> binding.progressBar.visibility = View.VISIBLE
                State.LOADED -> {
                    binding.progressBar.visibility = View.GONE
                    if (newsDataState.data is ArrayList<*>) {
                        newsList.clear()
                        (newsDataState.data as ArrayList<NewsModel>)
                            .filter { obj -> obj.title.isNotEmpty() && obj.abstract.isNotEmpty() }
                            .toCollection(newsList)
                        binding.progressBar.visibility = View.GONE
                        binding.newsRv.adapter!!.notifyItemRangeInserted(0, newsList.size)
                    }
                }
                State.ERROR -> {
                    Log.i("News", "Err : ${newsDataState.data}")
                    binding.apply {
                        progressBar.visibility = View.GONE
                        errorText.visibility = View.VISIBLE
                        errorText.text = newsDataState.data.toString()
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}