package project.seo.pictureviewer.presentation.utils

import android.view.View
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.findViewTreeLifecycleOwner

val View.coroutineScope: LifecycleCoroutineScope?
    get() = findViewTreeLifecycleOwner()?.lifecycle?.coroutineScope