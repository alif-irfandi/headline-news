package com.dicoding.headlinenews.home

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dicoding.core.data.Resource
import com.dicoding.core.domain.model.News
import org.koin.android.viewmodel.ext.android.viewModel
import com.dicoding.headlinenews.databinding.FragmentHomeBinding
import com.dicoding.headlinenews.R
import com.dicoding.headlinenews.detail.DetailActivity


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.headlineNews.observe(viewLifecycleOwner, {
            if (it != null)
                when (it) {
                    is Resource.Loading -> {
                        binding.progressTop.visibility = View.VISIBLE
                        binding.headlineNews.visibility = View.GONE
                        binding.imageNoInternet.visibility = View.GONE
                        binding.textNoInternet.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        binding.headlineNews.visibility = View.VISIBLE
                        binding.progressTop.visibility = View.GONE
                        binding.imageNoInternet.visibility = View.GONE
                        binding.textNoInternet.visibility = View.GONE
                        val adapter =
                            HomeAdapter(it.data!!, object : HomeAdapter.OnAdapterListener {
                                override fun onClick(news: News) {
                                    startActivity(
                                        Intent(requireActivity(), DetailActivity::class.java)
                                            .putExtra("detail", news)
                                    )
                                }
                            })
                        binding.headlineNews.adapter = adapter
                    }
                    else -> {
                        binding.imageNoInternet.visibility = View.VISIBLE
                        binding.textNoInternet.visibility = View.VISIBLE
                        binding.headlineNews.visibility = View.GONE
                        binding.progressTop.visibility = View.GONE
                    }
                }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_favorite, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_favorite -> {
            navigateToFavorite()
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToFavorite() {
        findNavController()
            .navigate(HomeFragmentDirections.actionHomeFragmentToFavoriteFragment())
    }
}