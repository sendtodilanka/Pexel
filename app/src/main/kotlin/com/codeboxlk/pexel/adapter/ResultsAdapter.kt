package com.codeboxlk.pexel.adapter

import android.graphics.Color
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.codeboxlk.pexel.R
import com.codeboxlk.pexel.data.model.PexelPhoto
import com.codeboxlk.pexel.databinding.ItemResultBinding
import com.codeboxlk.pexel.extension.px
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding

class ResultsAdapter: BindingListAdapter<PexelPhoto, RecyclerView.ViewHolder>(DIFFUTILS) {

    private lateinit var itemClickLister: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClicked(pexelPhoto: PexelPhoto)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickLister = listener
    }

    inner class ItemViewHolder(val binding: ItemResultBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(parent.binding(R.layout.item_result))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { item ->
            (holder as ItemViewHolder).binding.apply {
                cardView.updateLayoutParams<ConstraintLayout.LayoutParams> {
                    dimensionRatio = if (position % 3 == 0) "4:3" else "3:4"
                }

                cardView.setCardBackgroundColor(
                    Color.parseColor(item.avgColor)
                )

                Glide.with(root.context)
                    .load(item.src.medium)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(image)

                root.setOnClickListener { itemClickLister.onItemClicked(item) }
            }
        }
    }

    companion object {
        private val DIFFUTILS = object : DiffUtil.ItemCallback<PexelPhoto>() {
            override fun areItemsTheSame(
                oldItem: PexelPhoto,
                newItem: PexelPhoto
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: PexelPhoto,
                newItem: PexelPhoto
            ): Boolean = oldItem.src.small == newItem.src.small
        }
    }
}