package com.dicoding.favorite.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.core.domain.model.News
import com.dicoding.favorite.databinding.FragmentFavoriteBinding
import com.dicoding.headlinenews.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater)

        loadKoinModules(favoriteModule)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.favoriteNews.observe(viewLifecycleOwner, {
            binding.textEmpty.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            binding.imageEmpty.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE

            val adapter =
                FavoriteAdapter(it, object : FavoriteAdapter.OnAdapterListener {
                    override fun onClick(news: News) {
                        startActivity(
                            Intent(requireActivity(), DetailActivity::class.java)
                                .putExtra("detail", news)
                        )
                    }
                })
            binding.listFavorite.adapter = adapter
            binding.listFavorite.setHasFixedSize(true)
        })
    }
}