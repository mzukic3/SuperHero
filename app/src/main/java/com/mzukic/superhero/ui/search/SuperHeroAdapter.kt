package com.mzukic.superhero.ui.search

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.mzukic.superhero.R
import com.mzukic.superhero.data.model.SuperHero
import com.mzukic.superhero.databinding.ItemSuperheroBinding
import kotlinx.android.synthetic.main.item_superhero.view.*

class SuperHeroAdapter(
    private var context: Context,
    val callback: (product: SuperHero) -> Unit
) : ListAdapter<SuperHero, SuperHeroAdapter.SuperHeroViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val binding =
            ItemSuperheroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SuperHeroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class SuperHeroViewHolder(private val binding: ItemSuperheroBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(superHero: SuperHero) {
            binding.apply {
                root.setOnClickListener {
                    callback(superHero)
                }
                detailsLabel.text = superHero.name
                superHero.imageUrl.let {
                    Glide.with(context)
                        .asBitmap()
                        .load(it)
                        .centerCrop()
                        .transition(BitmapTransitionOptions.withCrossFade())
                        .placeholder(R.drawable.logo_superhero)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .addListener(object : RequestListener<Bitmap> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<Bitmap>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                return false
                            }

                            override fun onResourceReady(
                                resource: Bitmap?,
                                model: Any?,
                                target: Target<Bitmap>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                resource?.let {
                                    Palette.from(resource).generate { palette ->
                                        val bgColor = palette?.getMutedColor(
                                            ContextCompat.getColor(
                                                context,
                                                android.R.color.black
                                            )
                                        )
                                        heroNameHolder.setBackgroundColor(bgColor ?: 0)
                                    }
                                }

                                return false
                            }
                        })
                        .into(imageView)
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SuperHero>() {
            override fun areItemsTheSame(oldItem: SuperHero, newItem: SuperHero): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SuperHero, newItem: SuperHero): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}
