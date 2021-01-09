package com.roshaan.githubapp.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.roshaan.githubapp.R
import com.roshaan.githubapp.databinding.FragmentRepositoriesBinding
import com.roshaan.githubapp.presentation.viewmodel.RepositoriesViewModel


class RepositoriesFragment : Fragment(R.layout.fragment_repositories) {
    private var binding: FragmentRepositoriesBinding? = null

    private val viewModel: RepositoriesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRepositoriesBinding.bind(view)

        initAdapter()
        observeViewModel()
        setupPullToRefresh()
        initClickListeners()
    }

    private fun initClickListeners() {
        binding?.errorView?.retry
            ?.setOnClickListener { viewModel.fetchData(true) }
    }

    private fun setupPullToRefresh() {
        binding?.swipeRefreshLayout?.setOnRefreshListener {
            viewModel.fetchData(true)
        }
    }

    private fun initAdapter() {
        val layoutManager = LinearLayoutManager(context)
        binding?.recyclerView?.adapter = viewModel.adapter
        binding?.recyclerView?.layoutManager = layoutManager
    }

    private fun observeViewModel() {
        viewModel.errorVisibility.observe(viewLifecycleOwner, Observer {
            binding?.errorView?.root?.run {
                visibility = if (it) VISIBLE else GONE
            }
        })
        viewModel.reyclerViewVisibility.observe(
            viewLifecycleOwner,
            Observer {
                binding?.recyclerView?.run {
                    visibility = if (it) VISIBLE else GONE
                }
            })

        viewModel.shimmerVisibility.observe(viewLifecycleOwner, Observer {
            binding?.shimmer?.root?.run {
                if (it) {
                    visibility = VISIBLE
                    binding?.shimmer?.shimmerFrameLayout?.startShimmer()
                } else {
                    visibility = GONE
                    binding?.shimmer?.shimmerFrameLayout?.stopShimmer()
                }
            }
        })

        viewModel.swipeLoaderVisibility.observe(viewLifecycleOwner, Observer {
            if (it.not()) {
                binding?.swipeRefreshLayout?.isRefreshing = false
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}