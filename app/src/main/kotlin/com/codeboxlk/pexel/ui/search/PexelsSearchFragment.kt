package com.codeboxlk.pexel.ui.search

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.RecognizerIntent.EXTRA_RESULTS
import android.view.View
import android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.codeboxlk.pexel.PexelsSearchFragmentDirections
import com.codeboxlk.pexel.R
import com.codeboxlk.pexel.databinding.FragmentPexelsSearchBinding
import com.codeboxlk.pexel.extension.firstCap
import com.codeboxlk.pexel.extension.showSoftKeyboard
import com.skydoves.bindables.BindingFragment
import com.skydoves.whatif.whatIfNotNull
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class PexelsSearchFragment :
    BindingFragment<FragmentPexelsSearchBinding>(R.layout.fragment_pexels_search) {

    companion object {
        fun newInstance() = PexelsSearchFragment()
    }

    private val viewModel = viewModels<PexelsSearchViewModel>()

    private lateinit var voiceDetection: ActivityResultLauncher<Intent>

    override fun onAttach(context: Context) {
        super.onAttach(context)

        voiceDetection = registerForActivityResult(StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {

                result.data?.extras?.getStringArrayList(EXTRA_RESULTS)?.get(0)
                    .whatIfNotNull { string ->
                        binding.searchText.apply {
                            setText(string.firstCap)
                            setSelection(string.length)
                            showSoftKeyboard()
                        }
                    }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListener()
    }

    private fun setupListener() {
        lifecycleScope.launchWhenResumed {
            binding {
                toolbar.setNavigationOnClickListener {
                    findNavController().navigateUp()
                }

                searchText.apply {
                    requestFocus()
                    showSoftKeyboard()
                    doAfterTextChanged {
                        searchActionBtn.setImageResource(
                            if (it.isNullOrBlank()) {
                                R.drawable.round_mic_24
                            } else {
                                R.drawable.round_close_24
                            }
                        )
                    }

                    setOnEditorActionListener { _, actionId, _ ->
                        if (actionId == IME_ACTION_SEARCH) {
                            performSearch()
                        }
                        true
                    }
                }

                searchActionBtn.setOnClickListener {
                    if (searchText.text.isNullOrBlank()) {
                        launchVoiceRecognize()
                    } else {
                        searchText.text.clear()
                    }
                }
            }
        }
    }

    private fun performSearch() {
        binding.searchText.apply {
            if (!text.isNullOrBlank()) {
                findNavController().navigate(
                    PexelsSearchFragmentDirections.actionPexelsSearchFragmentToPexelsResultsFragment(
                        text.toString().trim()
                    )
                )
            }
        }
    }

    private fun launchVoiceRecognize() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault().language)
            putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.voice_prompt_text))
            putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, 20000000)
        }

        voiceDetection.launch(intent)
    }
}