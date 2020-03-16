package com.example.viewpageradapter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewpageradapter.databinding.FragmentOneBinding
import com.example.viewpageradapter.model.Movie
import com.example.viewpageradapter.network.MoviesApi
import com.example.viewpageradapter.repository.MoviesRepository
import kotlinx.android.synthetic.main.fragment_one.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class FragmentOne : Fragment() {

    private lateinit var fragBinding: FragmentOneBinding

    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapterMyMovie: MyMovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
        {
            fragBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_one, container, false)

          val view = fragBinding.root

            val repository = MoviesRepository(MoviesApi())


            GlobalScope.launch(Dispatchers.Main) {
                val movies = repository.getMovies()
                // Toast.makeText(this@MainActivity,movies.toString(),Toast.LENGTH_LONG).show()
                Log.d("Usman", movies.toString())

                layoutManager = LinearLayoutManager(view.context)
                fragBinding.recyclerMovieList.layoutManager = layoutManager
                fragBinding.recyclerMovieList.setHasFixedSize(true)

                adapterMyMovie = MyMovieAdapter(view.context, movies as MutableList<Movie>)

//            adapter.notifyDataSetChanged()
                fragBinding.recyclerMovieList.adapter = adapterMyMovie
            }

            return view
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //binding.cameraPreview
    }



/*
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //tv_fragment_name.text = "Fragment One"
    }
*/

}
