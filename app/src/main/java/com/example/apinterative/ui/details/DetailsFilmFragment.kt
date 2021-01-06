package com.example.apinterative.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.apinterative.R
import com.example.apinterative.model.ResultFilm
import kotlinx.android.synthetic.main.fragment_details_films.view.*


class DetailsFilmFragment : Fragment() {

    private lateinit var homeViewModel: DetailsFilmViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        homeViewModel = ViewModelProvider(this).get(DetailsFilmViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_details_films, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val result: ResultFilm? = arguments?.getParcelable("filmSelected") as? ResultFilm
        if (result != null) {
            val photo: ImageView = root.findViewById(R.id.film_photo)
            val date: TextView = root.findViewById(R.id.film_date)
            val producer: TextView = root.findViewById(R.id.film_producer)
            val episode: TextView = root.findViewById(R.id.film_episode)
            val director: TextView = root.findViewById(R.id.film_director)
            val opening: TextView = root.findViewById(R.id.fil_opening)
            val title: TextView = root.findViewById(R.id.movie_title)

            title.text = "Titulo : " + result.title
            date.text = "Data : " + result.release_date
            producer.text = "Produtor : " + result.producer
            episode.text = "Episodio : " + result.episodeId
            director.text = "Diretor : " + result.director
            opening.text = "Descricao : " + result.opening_crawl
            activity?.let {
                Glide.with(this).load("https://img.ibxk.com.br/2011/11/programas/8007925111742.jpg?w=328&h=218&mode=crop&scale=both&quality=80").into(photo)
            }
        } else {
            Toast.makeText(
                activity,
                "Houve problema no processamento da informação",
                Toast.LENGTH_LONG
            ).show()
        }

        return root
    }

}