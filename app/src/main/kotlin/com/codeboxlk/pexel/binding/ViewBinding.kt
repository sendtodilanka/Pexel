package com.codeboxlk.pexel.binding

import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.databinding.BindingAdapter
import com.codeboxlk.pexel.ui.results.PexelsResultsViewModel
import com.google.android.material.snackbar.Snackbar
import com.skydoves.whatif.whatIfNotNullOrEmpty
import timber.log.Timber

object ViewBinding {

    @JvmStatic
    @BindingAdapter("toast")
    fun bindToast(view: View, text: String?) {
        text.whatIfNotNullOrEmpty {
            Toast.makeText(view.context, it, Toast.LENGTH_SHORT).show()
        }
    }

    @JvmStatic
    @BindingAdapter("snack", "snackAction")
    fun bindSnack(view: View, text: String?, vm: PexelsResultsViewModel) {
        text.whatIfNotNullOrEmpty {
            Snackbar
                .make(view, "Request timeout", Snackbar.LENGTH_LONG)
                .setAction("Retry") {
                    Timber.d("Snackbar action clicked")
                    vm.retry()
                }
                .show()
        }
    }

    @JvmStatic
    @BindingAdapter("gone")
    fun bindGone(view: View, shouldBeGone: Boolean) {
        view.visibility = if (shouldBeGone) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    @JvmStatic
    @BindingAdapter("onBackPressed")
    fun bindOnBackPressed(view: View, onBackPress: Boolean) {
        val context = view.context
        if (onBackPress && context is OnBackPressedDispatcherOwner) {
            view.setOnClickListener {
                context.onBackPressedDispatcher.onBackPressed()
            }
        }
    }
}
