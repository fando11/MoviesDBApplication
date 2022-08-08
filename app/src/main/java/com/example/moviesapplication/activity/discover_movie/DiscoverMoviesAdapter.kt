package com.example.moviesapplication.activity.discover_movie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapplication.activity.movie.MovieDetailActivity
import com.example.moviesapplication.databinding.DiscoverMoviesItemBinding
import com.example.common.entity.discover_movie.Result as MoviesResult

class DiscoverMoviesAdapter(private val context: Context
): PagingDataAdapter<MoviesResult, DiscoverMovieViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverMovieViewHolder {
        return DiscoverMovieViewHolder(
            DiscoverMoviesItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: DiscoverMovieViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.data = data
        holder.binding.root.setOnClickListener {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("EXTRA_DATA_MOVIE_ID", data?.id)
            context.startActivity(intent)
        }
    }


    companion object{
        val diffCallback = object: DiffUtil.ItemCallback<MoviesResult>(){
            override fun areItemsTheSame(oldItem: MoviesResult, newItem: MoviesResult): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MoviesResult, newItem: MoviesResult): Boolean {
                return true
            }

        }
    }
}

class DiscoverMovieViewHolder(
    val binding: DiscoverMoviesItemBinding
): RecyclerView.ViewHolder(binding.root)