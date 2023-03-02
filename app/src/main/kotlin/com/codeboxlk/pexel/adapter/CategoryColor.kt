package com.codeboxlk.pexel.adapter

import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.codeboxlk.pexel.R
import com.codeboxlk.pexel.data.model.CategoryModel
import com.codeboxlk.pexel.databinding.ItemCategoryColorBinding
import com.codeboxlk.pexel.extension.px
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding
import com.skydoves.whatif.whatIfNotNull
import timber.log.Timber

class CategoryColor : BindingListAdapter<CategoryModel, RecyclerView.ViewHolder>(DIFFUTILS) {

    private lateinit var itemClickLister: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClicked(categoryModel: CategoryModel)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickLister = listener
    }

    inner class ItemViewHolder(val binding: ItemCategoryColorBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(parent.binding(R.layout.item_category_color))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { item ->
            (holder as ItemViewHolder).binding.apply {
                Glide.with(root.context)
                    .load(item.categoryPhotoUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .transform(CenterCrop(), RoundedCorners(12.px))
                    .into(image)

                item.category.color.whatIfNotNull(
                    whatIf = {
                        color.setColorFilter(it)
                    },
                    whatIfNot = {
                        color.setColorFilter(Color.TRANSPARENT)
                    }
                )

                root.setOnClickListener {
                    itemClickLister.onItemClicked(item)
                }
            }
        }
    }

    companion object {
        private val DIFFUTILS = object : DiffUtil.ItemCallback<CategoryModel>() {
            override fun areItemsTheSame(
                oldItem: CategoryModel,
                newItem: CategoryModel
            ): Boolean = oldItem.category == newItem.category

            override fun areContentsTheSame(
                oldItem: CategoryModel,
                newItem: CategoryModel
            ): Boolean = oldItem == newItem
        }
    }
}