package project.seo.pictureviewer.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import project.seo.pictureviewer.R
import project.seo.pictureviewer.databinding.FragmentMainBinding
import project.seo.pictureviewer.ui.adapter.ListAdapter
import project.seo.pictureviewer.utils.RecyclerViewItemDecorator
import project.seo.pictureviewer.ui.detail.DetailFragment

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
            viewModel.start()

            recyclerView.adapter = ListAdapter { pictureId ->
                parentFragmentManager.commit {
                    replace<DetailFragment>(
                        R.id.container, args = Bundle().apply {
                            putInt(DetailFragment.PICTURE_KEY, pictureId)
                        }
                    )
                    addToBackStack(null)
                }
            }
            recyclerView.addItemDecoration(RecyclerViewItemDecorator())
        }


    }
}
