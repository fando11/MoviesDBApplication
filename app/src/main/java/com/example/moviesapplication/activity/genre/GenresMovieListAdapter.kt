package com.example.moviesapplication.activity.genre

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.common.entity.genre.Genre
import com.example.moviesapplication.activity.discover_movie.DiscoverMoviesActivity
import com.example.moviesapplication.databinding.GenresMovieListItemBinding

class GenreMovieListAdapter(
    private val context: Context
): RecyclerView.Adapter<GenreViewHolder>()  {
    val dataDiffer = AsyncListDiffer<Genre>(this, differGenre)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(GenresMovieListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val data = dataDiffer.currentList[position]
        holder.binding.data = data
        holder.binding.root.setOnClickListener {
            val intent = Intent(context, DiscoverMoviesActivity::class.java)
            intent.putExtra("EXTRA_DATA_GENRE_ID", data?.id)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = dataDiffer.currentList.size

    fun submitData(list: List<Genre>){
        dataDiffer.submitList(list)
    }

    companion object{
        val differGenre = object: DiffUtil.ItemCallback<Genre>(){
            override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
                return true
            }

        }
    }
}

class GenreViewHolder(
    val binding: GenresMovieListItemBinding
): RecyclerView.ViewHolder(binding.root)