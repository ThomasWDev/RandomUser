package com.example.randomuser.feature.dashboard

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.randomuser.R
import com.example.randomuser.core.base.BaseActivity
import com.example.randomuser.core.base.BaseFragment
import com.example.randomuser.core.base.BaseViewModel
import com.example.randomuser.core.extensions.observe
import com.example.randomuser.databinding.FragmentDashboardBinding
import com.example.randomuser.feature.details.DetailsFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {

    @Inject
    lateinit var adapter: UsersAdapter

    private val viewModel: DashboardViewModel by viewModels()

    override val layoutRes: Int
        get() = R.layout.fragment_dashboard

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreated(savedInstance: Bundle?) {

        initViews()
        initObservers()
    }

    override fun onResume() {
        super.onResume()

        (activity as? BaseActivity<*>)?.setToolbar(
            show = true,
            showBackButton = false,
            title = getString(R.string.app_name)
        )
    }

    private fun initViews() {
        binding.apply {
            usersRvPosts.adapter = adapter
            adapter.apply {
                onItemClick = {
                    findNavController().navigate(
                        R.id.nav_to_detailsFragment,
                        bundleOf(DetailsFragment.ARGS_USER_DETAIL to gson.toJson(it))
                    )
                }
            }
        }
    }

    private fun initObservers() {
        viewModel.apply {
            observe(users) {
                it?.let { adapter.collection = it.results }
            }
        }

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }



}