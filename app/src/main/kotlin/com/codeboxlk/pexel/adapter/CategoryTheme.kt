package com.codeboxlk.pexel.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.codeboxlk.pexel.R
import com.codeboxlk.pexel.data.model.CategoryModel
import com.codeboxlk.pexel.databinding.ItemCategoryThemeBinding
import com.codeboxlk.pexel.extension.dp
import com.codeboxlk.pexel.extension.firstCap
import com.codeboxlk.pexel.extension.px
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding

class CategoryTheme : BindingListAdapter<CategoryModel, RecyclerView.ViewHolder>(DIFFUTILS) {

    private lateinit var itemClickLister: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClicked(categoryModel: CategoryModel)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickLister = listener
    }

    inner class ItemViewHolder(val binding: ItemCategoryThemeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(parent.binding(R.layout.item_category_theme))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { item ->
            (holder as ItemViewHolder).binding.apply {
                Glide.with(root.context)
                    .load(item.categoryPhotoUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .transform(CenterCrop(), RoundedCorners(12.px))
                    .into(image)

                title.text = item.category.name.firstCap

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