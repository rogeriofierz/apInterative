package com.example.apinterative.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apinterative.R
import com.example.apinterative.api.Film
import com.example.apinterative.model.ResultFilm
import com.example.apinterative.ui.films.CellClickListener

class MoviesAdapter(val results: List<ResultFilm>, private val cellClickListener: CellClickListener ):
    RecyclerView.Adapter<MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false)

        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val data = results.get(position)
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(data)
        }

        return holder.bind(results[position])
    }
}

class MoviesViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){

    fun bindView(film: Film, listener: (Film) -> Unit) {
        itemView.setOnClickListener { listener(film) }
    }

    private val photo:ImageView = itemView.findViewById(R.id.movie_photo)
    private val title:TextView = itemView.findViewById(R.id.movie_title)
    private val overview:TextView = itemView.findViewById(R.id.movie_overview)
    private val rating:TextView = itemView.findViewById(R.id.movie_rating)

    fun bind(result: ResultFilm) {
    //    Glide.with(itemView.context).load("http://image.tmdb.org/t/p/w500${movie.poster_path}").into(photo)
        title.text = "Filme : "+result.title
   //     overview.text = movie.overview
        rating.text = "Rating : "+result.episodeId.toString()
    }

}