package project.seo.pictureviewer.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import project.seo.pictureviewer.databinding.FragmentMainBinding
import project.seo.pictureviewer.navigator.AppNavigator
import project.seo.pictureviewer.navigator.Screens
import project.seo.pictureviewer.utils.RecyclerViewItemDecorator
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var navigator: AppNavigator

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
            recyclerView.adapter = MainListAdapter { pictureId ->
                navigator.navigateTo(pictureId, Screens.DETAIL)
            }
            recyclerView.addItemDecoration(RecyclerViewItemDecorator())
        }
    }
}
