package com.mzukic.superhero.ui.search

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mzukic.superhero.R
import com.mzukic.superhero.data.model.SuperHero
import com.mzukic.superhero.databinding.FragmentSearchBinding
import com.mzukic.superhero.ui.base.BaseFragment
import com.mzukic.superhero.util.hide
import com.mzukic.superhero.util.show


class SearchFragment : BaseFragment() {

    private lateinit var superHeroAdapter: SuperHeroAdapter
    private val viewModel: SearchViewModel by viewModels()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)

        superHeroAdapter = SuperHeroAdapter(requireContext()) {
            onSuperHeroItemClick(it)
        }

        binding.apply {
            recyclerView.apply {
                this.adapter = superHeroAdapter
                layoutManager = GridLayoutManager(context, 2)
            }

            buttonClear.setOnClickListener {
                showEmptyStateScreen()
                editTextSearch.clearFocus()
                editTextSearch.setText("")
                hideKeyboard()
            }
            buttonSubmit.setOnClickListener {
                performSearch()
            }
            editTextSearch.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch()
                    return@OnEditorActionListener true
                }
                false
            })
            buttonRetry.setOnClickListener {
                val text = editTextSearch.text.toString()
                if (text.isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.enter_superhero_name_label),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    viewModel.searchHeroes(text)
                }
            }
        }
        showEmptyStateScreen()

        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                showLoadingScreen()
            } else {
                hideLoadingScreen()
            }
        }

        viewModel.searchSuperHeroResult.observe(viewLifecycleOwner) {
            when (it) {
                SearchViewModel.SearchSuperHeroResult.EndpointFailedError,
                SearchViewModel.SearchSuperHeroResult.NoConnectionError -> {
                    showErrorScreen()
                }
                SearchViewModel.SearchSuperHeroResult.NoHeroesFound -> {
                    showNoResultScreen()
                }
                is SearchViewModel.SearchSuperHeroResult.Success -> {
                    showResultScreen()
                    superHeroAdapter.submitList(it.heroes)
                }
            }
        }
    }

    private fun FragmentSearchBinding.performSearch() {
        val query = editTextSearch.text.toString()
        if (query.isEmpty()) {
            Toast.makeText(
                requireContext(),
                getString(R.string.enter_superhero_name_label),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            binding.recyclerView.scrollToPosition(0)
            viewModel.searchHeroes(query)
            editTextSearch.clearFocus()
            hideKeyboard()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showErrorScreen() {
        binding.apply {
            containerEmpty.hide()
            containerError.show()
            containerNoResult.hide()
            recyclerView.hide()
            buttonClear.show()
        }
    }

    private fun showResultScreen() {
        binding.apply {
            containerEmpty.hide()
            containerError.hide()
            containerNoResult.hide()
            recyclerView.show()
            buttonClear.show()
        }
    }

    private fun showNoResultScreen() {
        binding.apply {
            containerEmpty.hide()
            containerError.hide()
            containerNoResult.show()
            recyclerView.hide()
            buttonClear.show()
        }
    }

    private fun showEmptyStateScreen() {
        binding.apply {
            containerEmpty.show()
            containerError.hide()
            containerNoResult.hide()
            recyclerView.hide()
            buttonClear.hide()
        }
    }

    private fun showLoadingScreen() {
        binding.apply {
            containerEmpty.hide()
            containerError.hide()
            containerNoResult.hide()
            recyclerView.hide()
            progressBar.show()
            buttonClear.hide()
        }
    }

    private fun hideLoadingScreen() {
        binding.progressBar.hide()
    }

    private fun onSuperHeroItemClick(superHero: SuperHero) {
        findNavController().navigate(
            SearchFragmentDirections.actionSearchFragmentToDetailFragment(
                superHero
            )
        )
    }

    /**
     * Hides the keyboard form the screen.
     */
    private fun hideKeyboard() {
        context?.let {
            val manager =
                it.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(view?.rootView?.windowToken, 0)
        }
    }
}
