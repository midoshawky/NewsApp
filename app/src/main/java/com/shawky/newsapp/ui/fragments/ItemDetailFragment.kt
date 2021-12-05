package com.shawky.newsapp.ui.fragments
import android.os.Build
import android.os.Bundle
import android.view.DragEvent
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.shawky.newsapp.R
import com.shawky.newsapp.constants.routs.GlideAppModule
import com.shawky.newsapp.databinding.FragmentItemDetailBinding
import com.shawky.newsapp.models.NewsModel
import com.shawky.newsapp.utils.Utils

class ItemDetailFragment : Fragment(R.layout.fragment_item_detail) {

    private var toolbarLayout: CollapsingToolbarLayout? = null

    private var binding: FragmentItemDetailBinding? = null

    @RequiresApi(Build.VERSION_CODES.O)
    private val dragListener = View.OnDragListener { _ , event ->
        if (event.action == DragEvent.ACTION_DROP) {
            updateContent()
        }
        true
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentItemDetailBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        updateContent()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateContent() {

        val bundle = requireArguments()
        val news = bundle.get("News") as NewsModel

        GlideAppModule.loadImage(binding!!.newsImage,news.multimedia.first().url,requireContext())

        binding?.apply {
            pictureCopyright.text = ("© ${news.multimedia.first().copyright}")
            detialTitleText.text = news.title
            detailPublisher.text = news.publishedBy
            itemDetail.text = news.abstract
            detailDateText.text = Utils.dateFormat(news.publishDate)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}