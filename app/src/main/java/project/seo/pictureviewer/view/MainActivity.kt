package project.seo.pictureviewer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import project.seo.pictureviewer.presenter.MainPresenter
import project.seo.pictureviewer.contract.MainContract
import project.seo.pictureviewer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MainPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun showPictureFragment() {
        supportFragmentManager.commit {
            replace(binding.fragmentContainerView.id, PictureFragment())
        }
    }

    override fun showDetailFragment(pictureId: Int) {
        supportFragmentManager.commit {
            replace(binding.fragmentContainerView.id, DetailFragment(pictureId))
        }
    }
}