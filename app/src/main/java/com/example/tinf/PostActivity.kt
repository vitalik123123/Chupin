package com.example.tinf

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.tinf.di.Injector
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_post.*
import kotlin.LazyThreadSafetyMode.NONE


class PostActivity : AppCompatActivity(R.layout.activity_post) {

    private val viewModel by lazy(NONE) {
        Injector.appComponent.mainViewModel
    }

    private val connectivityLiveData by lazy(NONE) {
        Injector.appComponent.connectivityLiveData
    }

    private lateinit var networkSnackbar: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeNetworkConnection()
        initUI()
        observePost()

        if (savedInstanceState == null) {
            viewModel.getPost()
        }
    }

    private fun observePost() {
        viewModel.post.observe(this) { post ->
            post?.let {
                previousPost.isEnabled = true
                postImage.loadGif(post.gifURL)
                postDesc.text = post.description
            } ?: run {
                previousPost.isEnabled = false
            }
        }
    }

    private fun initUI() {
        nextPost.setOnClickListener { viewModel.getPost() }
        previousPost.setOnClickListener { viewModel.getPreviousPost() }
        networkSnackbar = Snackbar.make(root, R.string.network_error, LENGTH_INDEFINITE)
    }

    private fun observeNetworkConnection() {
        connectivityLiveData.observe(this) { isConnected ->
            if (isConnected) {
                content.visibility = View.VISIBLE
                networkError.visibility= View.GONE
                networkSnackbar.dismiss()
            } else {
                content.visibility = View.GONE
                networkError.visibility= View.VISIBLE
                networkSnackbar.show()
            }
        }
    }

    private fun ImageView.loadGif(url: String) {
        Glide.with(context).load(url)
            .placeholder(R.drawable.progress_animation)
            .error(R.drawable.try_later)
            .into(this)
    }
}
