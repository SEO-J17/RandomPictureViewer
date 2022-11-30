package project.seo.pictureviewer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import project.seo.pictureviewer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFragment(PictureFragment())
    }

    fun setFragment(fragment: Fragment) {
        with(supportFragmentManager) {
            if (backStackEntryCount == 0) {
                beginTransaction().add(binding.fragmentContainerView.id, fragment)
                    .addToBackStack("first")
            } else {
                beginTransaction()
                    .replace(binding.fragmentContainerView.id, fragment)
            }
                .commit()
        }
    }
}