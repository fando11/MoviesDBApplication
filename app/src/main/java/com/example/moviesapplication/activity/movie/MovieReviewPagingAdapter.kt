package com.example.moviesapplication.activity.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapplication.databinding.MovieReviewItemBinding
import com.example.common.entity.movie.movie_review.Result as MovieReview


class MovieReviewPagingAdapter(
): PagingDataAdapter<MovieReview, MovieReviewViewHolder>(differ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieReviewViewHolder {
        return MovieReviewViewHolder(
        MovieReviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieReviewViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.data = data
    }

    companion object{
        val differ = object: DiffUtil.ItemCallback<MovieReview>() {
            override fun areItemsTheSame(oldItem: MovieReview, newItem: MovieReview): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieReview, newItem: MovieReview): Boolean {
                return oldItem == newItem
            }
        }
    }




}

class MovieReviewViewHolder(
    val binding: MovieReviewItemBinding
):RecyclerView.ViewHolder(binding.root)