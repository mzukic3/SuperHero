package com.mzukic.superhero.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.mzukic.superhero.R
import com.mzukic.superhero.databinding.FragmentDetailBinding
import com.mzukic.superhero.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : BaseFragment() {

    private val args: DetailFragmentArgs by navArgs()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        _binding = FragmentDetailBinding.bind(view)

        val superHero = args.superHero
        binding.apply {
            // setup toolbar
            toolbarLayout.title = superHero.name
            toolbar.title = superHero.name
            toolbar.setNavigationOnClickListener { view ->
                findNavController().navigateUp()
            }

            Glide.with(this@DetailFragment).load(superHero.imageUrl).into(detail_image)
            Glide.with(this@DetailFragment).load(superHero.imageUrl).into(imagePoster)

            val aliases = superHero.aliases
            for (index in aliases) {
                val chip = Chip(chipGroup.context)
                chip.text = index
                chipGroup.addView(chip)
            }
            alternativeNameTextView.text = superHero.alternativeName
            publisherNameTextView.text = superHero.publisher
            placeOfBirthTextView.text = superHero.placeOfBirth
            alignmentTextView.text = superHero.alignment
            firstAppearanceTextView.text = superHero.firstAppearance
            alterEgosTextView.text = superHero.alterEgos
        }
    }
}
