package com.codeboxlk.pexel

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.codeboxlk.pexel.adapter.ResultsAdapter
import com.codeboxlk.pexel.data.model.PexelPhoto
import com.codeboxlk.pexel.databinding.FragmentPexelsResultsBinding
import com.codeboxlk.pexel.extension.firstCap
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PexelsResultsFragment : BindingFragment<FragmentPexelsResultsBinding>(R.layout.fragment_pexels_results),
    ResultsAdapter.OnItemClickListener {

    @Inject
    internal lateinit var viewModelFactory: PexelsResultsViewModel.AssistedFactory

    private val args: PexelsResultsFragmentArgs by navArgs()
    private val viewModel: PexelsResultsViewModel by viewModels {
        PexelsResultsViewModel.provideFactory(viewModelFactory, args.searchQuery)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            toolbar.title = args.searchQuery.firstCap
            toolbar.setNavigationOnClickListener { findNavController().navigateUp() }

            adapter = ResultsAdapter().apply {
                setOnItemClickListener(this@PexelsResultsFragment)
            }
            vm = viewModel
        }
    }

    override fun onItemClicked(pexelPhoto: PexelPhoto) {

    }
}