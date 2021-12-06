package com.shawky.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shawky.newsapp.R
import com.shawky.newsapp.constants.routs.GlideApp
import com.shawky.newsapp.databinding.MultimediaViewpagerLayoutBinding
import com.shawky.newsapp.models.MultiMedia

class NewsMultimediaVPAdapter(private val imagesDataList : ArrayList<MultiMedia>) : RecyclerView.Adapter<NewsMultimediaVPAdapter.ViewHolder>() {
    inner class ViewHolder(private val multimediaViewpagerLayoutBinding: MultimediaViewpagerLayoutBinding) :
        RecyclerView.ViewHolder(multimediaViewpagerLayoutBinding.root) {
        fun bind(image: MultiMedia) {

            GlideApp.with(itemView.context)
                .load(image.url)
                .placeholder(R.drawable.news_placeholder)
                .into(multimediaViewpagerLayoutBinding.newsImageItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MultimediaViewpagerLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imagesDataList[position])
    }

    override fun getItemCount(): Int = imagesDataList.size
}