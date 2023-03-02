package com.codeboxlk.pexel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codeboxlk.pexel.data.model.CategoryModel
import com.codeboxlk.pexel.data.model.PhotoCategory
import com.skydoves.bindables.BindingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

class FirstViewModel : BindingViewModel() {

    private val _categoryThemes: MutableLiveData<List<CategoryModel>> = MutableLiveData(
        listOf(
            CategoryModel(PhotoCategory.ABSTRACT(), R.drawable.abstracts),
            CategoryModel(PhotoCategory.ANIMALS(), R.drawable.animal),
            CategoryModel(PhotoCategory.ARCHITECTURE(), R.drawable.architecture),
            CategoryModel(PhotoCategory.NATURE(), R.drawable.nature),
            CategoryModel(PhotoCategory.NIGHT(), R.drawable.night),
            CategoryModel(PhotoCategory.PORTRAITS(), R.drawable.portrait),
            CategoryModel(PhotoCategory.SEA(), R.drawable.sea)
        )
    )
    val categoryThemes: LiveData<List<CategoryModel>> = _categoryThemes

    private val _categoryColors: MutableLiveData<List<CategoryModel>> = MutableLiveData(
        listOf(
            CategoryModel(PhotoCategory.COLOR_WHITE(), R.drawable.white),
            CategoryModel(PhotoCategory.COLOR_BLACK(), R.drawable.black),
            CategoryModel(PhotoCategory.COLOR_RED(), R.drawable.red),
            CategoryModel(PhotoCategory.COLOR_GREEN(), R.drawable.green),
            CategoryModel(PhotoCategory.COLOR_BLUE(), R.drawable.blue),
            CategoryModel(PhotoCategory.COLOR_VIOLET(), R.drawable.violet),
            CategoryModel(PhotoCategory.COLOR_YELLOW(), R.drawable.yellow)
        )
    )
    val categoryColors: LiveData<List<CategoryModel>> = _categoryColors
}