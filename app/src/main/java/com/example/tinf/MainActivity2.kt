package com.example.tinf


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.tinf.repository.Repository
import kotlinx.android.synthetic.main.activity_main2.*
class MainActivity2: AppCompatActivity() {

    var arrGif = ArrayList<String>()
    var arrText = ArrayList<String>()
    private var countButtonsClick = 0
    private var countNextClick = 1
    private lateinit var viewModel: MainViewModel
    private lateinit var cld: ConnectionLiveData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        checkNetworkConnection()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer { response ->
            Glide.with(this).load(response.gifURL).placeholder(R.drawable.progress_animation).error(R.drawable.try_later).into(imageViewR)
            textViewR.text = response.description
                arrGif.add(response.gifURL)
                arrText.add(response.description)

            Log.d("TESTARR1", "$arrGif \n $arrText")
        })



        btnNextR.setOnClickListener {

            btnPrevR.isEnabled = true
            countButtonsClick++
            if (countButtonsClick == countNextClick) {
                countNextClick++
                viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
                viewModel.getPost()
                viewModel.myResponse.observe(this, Observer { response ->
                    Glide.with(this).load(response.gifURL).placeholder(R.drawable.progress_animation).error(R.drawable.try_later).into(imageViewR)
                    textViewR.text = response.description

                    Log.d("TESTARR2", "$arrGif \n $arrText")
                })
            }else{
                    Glide.with(this).load(arrGif[countButtonsClick]).placeholder(R.drawable.progress_animation).error(R.drawable.try_later).into(imageViewR)
                    textViewR.text = arrText[countButtonsClick]
            }

        }

        btnPrevR.setOnClickListener {
            countButtonsClick--

                Glide.with(this).load(arrGif[countButtonsClick]).placeholder(R.drawable.progress_animation).error(R.drawable.try_later).into(imageViewR)
                textViewR.text = arrText[countButtonsClick]

            if (countButtonsClick == 0) {
                btnPrevR.isEnabled = false
            }
        }

    }


    private fun checkNetworkConnection() {
        cld = ConnectionLiveData(application)

        cld.observe(this) { isConnected ->

            if (isConnected) {
                linLayViews.visibility = View.VISIBLE
                linLayCon.visibility = View.GONE
            } else {
                linLayViews.visibility = View.GONE
                linLayCon.visibility = View.VISIBLE
            }
        }
    }
}


