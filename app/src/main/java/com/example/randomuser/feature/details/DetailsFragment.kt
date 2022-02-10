package com.example.randomuser.feature.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.randomuser.R
import com.example.randomuser.core.base.BaseActivity
import com.example.randomuser.core.base.BaseFragment
import com.example.randomuser.core.base.BaseViewModel
import com.example.randomuser.core.extensions.fromJson
import com.example.randomuser.core.extensions.observe
import com.example.randomuser.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(),
    DetailsHandler
{

    private val viewModel: DetailsViewModel by viewModels()

    override val layoutRes: Int
        get() = R.layout.fragment_details

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreated(savedInstance: Bundle?) {

        checkArgs()
        initViews()
        initObservers()
    }

    private fun initViews() {
    }

    private fun checkArgs() {
        arguments?.let {
            if (it.containsKey(ARGS_USER_DETAIL)) {
                viewModel.setDetails(gson.fromJson(it.getString(ARGS_USER_DETAIL, "")))
            }
        }
    }

    private fun initObservers() {
        viewModel.apply {
            observe(details) {
                it?.let { detail ->
                    (activity as? BaseActivity<*>)?.setToolbar(
                        show = true,
                        showBackButton = true,
                        title = detail.name.first.toString()
                    )
                }
            }
        }

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.handler = this
    }

    companion object {
        const val ARGS_USER_DETAIL = "_user_detail"
    }

    override fun sendEmailTo(email: String?) {
        email?.let {
            val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email"))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "subject")
            startActivity(Intent.createChooser(emailIntent, "RandomUser Email"))
        }
    }

}