package com.codeboxlk.pexel

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.codeboxlk.pexel.adapter.ColorAdapter
import com.codeboxlk.pexel.adapter.ThemeAdapter
import com.codeboxlk.pexel.data.model.CategoryModel
import com.codeboxlk.pexel.databinding.FragmentPexelsHomeBinding
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class PexelsHomeFragment :
    BindingFragment<FragmentPexelsHomeBinding>(R.layout.fragment_pexels_home),
    ColorAdapter.OnItemClickListener, ThemeAdapter.OnItemClickListener {

    private val viewModel: PexelsHomeViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(view) { _view, insets ->
            val systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            _view.updateLayoutParams<FrameLayout.LayoutParams> {
                topMargin = systemBar.top
                bottomMargin = systemBar.bottom
            }

            insets
        }

        binding {
            colorAdapter = ColorAdapter().apply {
                setOnItemClickListener(this@PexelsHomeFragment)
            }

            themeAdapter = ThemeAdapter().apply {
                setOnItemClickListener(this@PexelsHomeFragment)
            }

            vm = viewModel

            searchBar.setOnClickListener {
                findNavController().navigate(
                    PexelsHomeFragmentDirections
                        .actionPexelsHomeFragmentToPexelsSearchFragment()
                )
            }
        }
    }

    override fun onItemClicked(categoryModel: CategoryModel) {
        findNavController().navigate(
            PexelsHomeFragmentDirections
                .actionPexelsHomeFragmentToPexelsResultsFragment(
                    categoryModel.category.name
                )
        )
    }
}