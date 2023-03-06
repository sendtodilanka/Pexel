package com.codeboxlk.pexel.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codeboxlk.pexel.R
import com.codeboxlk.pexel.data.model.CategoryModel
import com.codeboxlk.pexel.data.model.PhotoCategory
import com.skydoves.bindables.BindingViewModel
import kotlinx.coroutines.*

class PexelsHomeViewModel : BindingViewModel() {

    private val _themes = MutableLiveData<List<CategoryModel>>()
    val themes: LiveData<List<CategoryModel>> = _themes

    private val _colors = MutableLiveData<List<CategoryModel>>()
    val colors: LiveData<List<CategoryModel>> = _colors

    init {
        viewModelScope.launch {
            supervisorScope {
                val color = async {
                    listOf(
                        CategoryModel(PhotoCategory.COLOR_WHITE(), R.drawable.white),
                        CategoryModel(PhotoCategory.COLOR_BLACK(), R.drawable.black),
                        CategoryModel(PhotoCategory.COLOR_RED(), R.drawable.red),
                        CategoryModel(PhotoCategory.COLOR_GREEN(), R.drawable.green),
                        CategoryModel(PhotoCategory.COLOR_BLUE(), R.drawable.blue),
                        CategoryModel(PhotoCategory.COLOR_VIOLET(), R.drawable.violet),
                        CategoryModel(PhotoCategory.COLOR_YELLOW(), R.drawable.yellow)
                    )
                }

                val theme = async {
                    listOf(
                        CategoryModel(PhotoCategory.ABSTRACT(), R.drawable.abstracts),
                        CategoryModel(PhotoCategory.ANIMALS(), R.drawable.animal),
                        CategoryModel(PhotoCategory.ARCHITECTURE(), R.drawable.architecture),
                        CategoryModel(PhotoCategory.NATURE(), R.drawable.nature),
                        CategoryModel(PhotoCategory.NIGHT(), R.drawable.night),
                        CategoryModel(PhotoCategory.PORTRAITS(), R.drawable.portrait),
                        CategoryModel(PhotoCategory.SEA(), R.drawable.sea)
                    )
                }

                doAfterAllAwait(theme.await(), color.await()) { values ->
                    _themes.value = values[0]
                    _colors.value = values[1]
                }
            }
        }
    }

    private fun <T> doAfterAllAwait(vararg awaitValues: T, nextAction: (Array<out T>) -> Unit) {
        nextAction.invoke(awaitValues)
    }
}