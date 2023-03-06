package com.codeboxlk.pexel

import androidx.annotation.MainThread
import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.codeboxlk.pexel.data.model.PexelPhoto
import com.codeboxlk.pexel.data.repository.Repository
import com.google.android.material.snackbar.Snackbar
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import timber.log.Timber

class PexelsResultsViewModel @AssistedInject constructor(
    private val repository: Repository,
    @Assisted private val searchQuery: String
) : BindingViewModel() {

    @get:Bindable
    var isLoading: Boolean by bindingProperty(false)
        private set

    @get:Bindable
    var toastMessage: String? by bindingProperty(null)
        private set

    private val pexelsFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)

    private val pexelsListFlow = pexelsFetchingIndex.flatMapLatest { page ->
        Timber.d("pexelsListFlow() -> $page")
        repository.searchPhotos(
            query = searchQuery,
            page = page,
            perPage = 50,
            onStart = { isLoading = true },
            onComplete = { isLoading = false },
            onError = { toastMessage = it }
        )
    }

    @get:Bindable
    val pexelsList: List<PexelPhoto> by pexelsListFlow.asBindingProperty(
        viewModelScope,
        emptyList()
    )

    @MainThread
    fun fetchNextPexelsList() {
        if (!isLoading) { pexelsFetchingIndex.value++ }
    }

    fun retry() {
        Timber.d("retry()")
        viewModelScope.launch {
            pexelsFetchingIndex.value--

            delay(500)

            pexelsFetchingIndex.value++
        }
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(searchQuery: String): PexelsResultsViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            searchQuery: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(searchQuery) as T
            }
        }
    }
}