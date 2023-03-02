package com.codeboxlk.pexel

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.codeboxlk.pexel.adapter.ResultsAdapter
import com.codeboxlk.pexel.data.model.PexelPhoto
import com.codeboxlk.pexel.databinding.FragmentSecondBinding
import com.codeboxlk.pexel.extension.firstCap
import com.codeboxlk.pexel.extension.screenHeightInPx
import com.codeboxlk.pexel.extension.screenWidthInPx
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SecondFragment : BindingFragment<FragmentSecondBinding>(R.layout.fragment_second),
    ResultsAdapter.OnItemClickListener {

    @Inject
    internal lateinit var viewModelFactory: SecondViewModel.AssistedFactory

    private val args: SecondFragmentArgs by navArgs()
    private val viewModel: SecondViewModel by viewModels {
        SecondViewModel.provideFactory(viewModelFactory, args.searchQuery)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            toolbar.title = args.searchQuery.firstCap
            toolbar.setNavigationOnClickListener { findNavController().navigateUp() }

            adapter = ResultsAdapter().apply {
                setOnItemClickListener(this@SecondFragment)
            }
            vm = viewModel
        }
    }

    override fun onItemClicked(pexelPhoto: PexelPhoto) {

    }
}