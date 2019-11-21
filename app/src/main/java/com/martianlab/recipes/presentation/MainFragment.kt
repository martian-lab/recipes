package com.martianlab.recipes.presentation


import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.gson.Gson

import com.martianlab.recipes.R
import com.martianlab.recipes.RecipesApp
import com.martianlab.recipes.presentation.viewmodel.RecipesViewModel
import com.martianlab.recipes.presentation.viewmodel.Resource
import kotlinx.android.synthetic.main.fragment_types.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter
import javax.inject.Inject


class MainFragment : Fragment() {

    @Inject
    lateinit var viewModel: RecipesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_types, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        (activity?.application as RecipesApp).component.inject(this)
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        viewModel.loadingState.observe(viewLifecycleOwner, Observer {
            loading_state_tv.text =
                when( it ){
                    is Resource.Loading -> "loading..."
                    is Resource.Success -> it.data
                    else -> "Я ничего не трогала, оно само..."
                }

        })
        viewModel.loadRecipes()

        viewModel.recipes.observe(viewLifecycleOwner, Observer {
            recipes ->
                if( recipes is Resource.Success)
                    println("RECIPES: json=" + recipes.data?.get(10)?.ingredients)
            //domystaff(Gson().toJson(recipes))
        })

    }

    private fun domystaff(json : String){

        try {
            val path = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS);
            val myFile = File(path, "mytextfile.json");
            val fOut = FileOutputStream(myFile,true);
            val myOutWriter = OutputStreamWriter(fOut);
            myOutWriter.append(json);
            myOutWriter.close();
            fOut.close();

            Toast.makeText(activity,"Text file Saved !",Toast.LENGTH_LONG).show();
        }

        catch ( e : IOException) {
            println("RECIPES: e=" + e)
            //do something if an IOException occurs.
            Toast.makeText(activity,"ERROR - Text could't be added",Toast.LENGTH_LONG).show();
        }
    }
}
