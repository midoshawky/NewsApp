package com.shawky.newsapp.ui.fragments
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.annotation.RequiresApi
import com.shawky.newsapp.R
import com.shawky.newsapp.databinding.FragmentItemDetailBinding
import com.shawky.newsapp.models.NewsModel
import com.shawky.newsapp.ui.adapter.NewsMultimediaVPAdapter
import com.shawky.newsapp.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemDetailFragment : Fragment(R.layout.fragment_item_detail) {

    private var binding: FragmentItemDetailBinding? = null


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentItemDetailBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        updateContent()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateContent() {


        val bundle = requireArguments()

        if(bundle.get("News") != null){
            val news = bundle.get("News") as NewsModel
            val imagesAdapter = NewsMultimediaVPAdapter(news.multimedia)
            binding?.apply {
                pictureCopyright.text = ("© ${news.multimedia.first().copyright}")
                detialTitleText.text = news.title
                detailPublisher.text = news.publishedBy
                itemDetail.text = news.abstract
                detailDateText.text = Utils.dateFormat(news.publishDate)
                imagesVp.apply {
                    adapter = imagesAdapter
                }
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}