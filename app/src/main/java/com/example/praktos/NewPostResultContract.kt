package com.example.praktos

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.viewmodel.compose.viewModel

class NewPostResultContract : ActivityResultContract<Unit, String?>() {
    override fun createIntent(context: Context, input: Unit?): Intent =
        Intent(context, NewPostActivity::class.java)

    override fun parseResult(resultCode: Int, intent: Intent?): String? =
        if (resultCode == Activity.RESULT_OK){
            intent?.getStringExtra(Intent.EXTRA_TEXT)
        }else{
            null
        }
    val NewPostLauncher = registerForActivityResult(NewPostResultContract()) { result ->
        result ?: return@registerForActivityResult
        viewModel.changeContent(result)
        viewModel.save()
    }
}