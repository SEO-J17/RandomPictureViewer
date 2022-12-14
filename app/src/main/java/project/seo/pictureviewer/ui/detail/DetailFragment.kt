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
import project.seo.pictureviewer.databinding.FragmentDetailBinding

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
        viewModel.updateId(arguments?.getInt(PICTURE_KEY) ?: 0)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            detailVm = viewModel
            detailFragment = this@DetailFragment
            lifecycleOwner = viewLifecycleOwner
        }

        viewModel.fetchDetail()
        viewModel.error.observe(this.viewLifecycleOwner, EventObserver { message ->
            Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
        })
    }

    fun showWebPage(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    companion object {
        const val PICTURE_KEY = "pictureId"
    }
}