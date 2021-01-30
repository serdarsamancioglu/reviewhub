package com.serdar.reviewhub.review

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.serdar.reviewhub.databinding.ItemReviewBinding
import com.serdar.reviewhub.entity.ReviewItem
import com.serdar.reviewhub.entity.ReviewItems
import java.lang.StringBuilder

class ReviewAdapter(private val reviewItems: List<ReviewItem>, private val listener: ItemClickListener)
    : RecyclerView.Adapter<ReviewAdapter.ReviewVH>() {

    override fun getItemCount(): Int = reviewItems.size

    override fun onBindViewHolder(holder: ReviewVH, position: Int) {
        holder.bind(reviewItems[position], listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewVH {
        return ReviewVH.from(parent)
    }

    class ReviewVH(val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ReviewItem, listener: ItemClickListener) {
            binding.reviewItem = item
            var labels = StringBuilder()
            item.labels?.run {
                for (s in this) {
                    labels.append(s)
                    labels.append(", ")
                }
                if (labels.isNotEmpty()) {
                    labels.delete(labels.length - 2, labels.length)
                }
                binding.tvLabels.text = labels.toString()
            }
            binding.marker.setOnClickListener {
                item.geo?.lat?.let { it1 -> item.geo?.lon?.let { it2 ->
                    listener.onItemClicked(it1,
                        it2
                    )
                } }
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ReviewVH {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemReviewBinding.inflate(inflater, parent, false)
                return ReviewVH(binding)
            }
        }
    }
}