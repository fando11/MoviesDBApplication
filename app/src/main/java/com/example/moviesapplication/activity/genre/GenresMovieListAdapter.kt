package com.example.moviesapplication.activity.genre


import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.common.entity.genre.Genre
import com.example.moviesapplication.databinding.GenresMovieListItemBinding

class GenreMovieListAdapter(
    private val getSelectedGenres: () -> ArrayList<Genre>
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
            val selectedGenre = getSelectedGenres()
            if (data in selectedGenre) {
                selectedGenre.remove(data)
            } else {
                selectedGenre.add(data)
            }

            if (data in getSelectedGenres()) {
                holder.binding.cardView.setCardBackgroundColor(Color.BLUE)
                holder.binding.tvGenre.setTextColor(Color.WHITE)
            } else {
                holder.binding.cardView.setCardBackgroundColor(Color.WHITE)
                holder.binding.tvGenre.setTextColor(Color.BLACK)
            }
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