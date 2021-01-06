package com.example.apinterative.ui.films

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apinterative.R
import com.example.apinterative.adapter.MoviesAdapter
import com.example.apinterative.api.ServiceBuilder
import com.example.apinterative.model.PopularMovies
import com.example.apinterative.model.ResultFilm
import com.example.apinterative.ui.details.DetailsFilmFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class FilmFragment : Fragment(), CellClickListener{

    private lateinit var filmViewModel: FilmViewModel
    lateinit var loadingView: ProgressBar
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        filmViewModel =
            ViewModelProvider(this).get(FilmViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_films, container, false)
        val textView: TextView = root.findViewById(R.id.text_films)
        filmViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        loadingView = root.findViewById(R.id.progressFilms)

        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            ServiceBuilder.buildService().getMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response ->
                    onResponse(response)
                }, { t -> onFailure(t) })
        )

        recyclerView = root.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)

        return root
    }

    private fun onFailure(t: Throwable) {
        loadingView.visibility = View.GONE
        Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
    }

    private fun onResponse(response: PopularMovies) {
        loadingView.visibility = View.GONE
        recyclerView.adapter = MoviesAdapter(
            response.results,
            this
        )
    }

    override fun onCellClickListener(data: ResultFilm) {
     //   Toast.makeText(DetailsFilmFragment::class.java, "", Toast.LENGTH_SHORT).show()
        Log.d("TAG " , data.title)
        sendHomeFragment(data)

    }

    fun sendHomeFragment(data : ResultFilm){
        val bundle = Bundle()
        bundle.putParcelable("filmSelected", data)

        val transaction = getFragmentManager()?.beginTransaction()
        val frag2 = DetailsFilmFragment()
        frag2.arguments = bundle

        if (transaction != null) {
            transaction.replace(R.id.nav_host_fragment, frag2,"DetailsFilms")
            transaction.addToBackStack(null)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.commit()

        }

    }

    override fun onResume() {
        super.onResume()
    }


}



