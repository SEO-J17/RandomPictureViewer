package project.seo.pictureviewer.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import project.seo.pictureviewer.databinding.FragmentDetailBinding
import project.seo.pictureviewer.utils.observeEvent

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            detailVm = viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        with(viewModel) {
            fetchDetail()
            error.observeEvent(viewLifecycleOwner) { message ->
                Toast.makeText(this@DetailFragment.context, message, Toast.LENGTH_SHORT).show()
            }

            webPage.observeEvent(viewLifecycleOwner) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(pictureDetail.value?.url)
                    )
                )
            }
        }
    }
}