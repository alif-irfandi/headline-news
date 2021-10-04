package com.dicoding.headlinenews.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat
import com.dicoding.core.domain.model.News
import com.dicoding.headlinenews.R
import com.dicoding.headlinenews.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModel()
    private val detail by lazy { intent.getSerializableExtra("detail") as News }
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title = "Detail News"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.getOneNews(detail.publishedAt).observe(this, { data ->
            data?.let {
                val web = binding.webView
                it.url?.let { news -> web.loadUrl(news) }
                web.webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        binding.progressTop.visibility = View.GONE
                    }
                }
                val settings = binding.webView.settings
                settings.javaScriptCanOpenWindowsAutomatically = false

                var statusFavorite = it.isFavorite
                changeFabIcon(statusFavorite)
                binding.favoriteFab.setOnClickListener {
                    statusFavorite = !statusFavorite
                    changeFabIcon(statusFavorite)
                    viewModel.setFavoriteNews(data, statusFavorite)
                }
            }
        })
    }

    private fun changeFabIcon(statusFavorite: Boolean) {
        if (statusFavorite) binding.favoriteFab.setImageDrawable(
            ContextCompat.getDrawable(applicationContext, R.drawable.ic_favorite)
        )
        else binding.favoriteFab.setImageDrawable(
            ContextCompat.getDrawable(applicationContext, R.drawable.ic_unfavorite)
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}