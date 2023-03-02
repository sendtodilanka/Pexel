package com.codeboxlk.pexel

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.codeboxlk.pexel.adapter.CategoryColor
import com.codeboxlk.pexel.adapter.CategoryTheme
import com.codeboxlk.pexel.data.model.CategoryModel
import com.codeboxlk.pexel.databinding.FragmentFirstBinding
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment : BindingFragment<FragmentFirstBinding>(R.layout.fragment_first),
    CategoryColor.OnItemClickListener, CategoryTheme.OnItemClickListener {

    private val vm: FirstViewModel by viewModels()
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

        val colorAdapter = CategoryColor().apply {
            setOnItemClickListener(this@FirstFragment)
        }

        val themeAdapter = CategoryTheme().apply {
            setOnItemClickListener(this@FirstFragment)
        }

        binding.rvColor.adapter = colorAdapter
        binding.rvTheme.adapter = themeAdapter

        lifecycleScope.launchWhenResumed {
            vm.categoryThemes.observe(viewLifecycleOwner) {
                themeAdapter.submitList(it)
            }

            vm.categoryColors.observe(viewLifecycleOwner) {
                colorAdapter.submitList(it)
            }
        }
    }

    override fun onItemClicked(categoryModel: CategoryModel) {
        findNavController().navigate(
            FirstFragmentDirections.actionFirstFragmentToSecondFragment(
                categoryModel.category.name
            )
        )
    }
}