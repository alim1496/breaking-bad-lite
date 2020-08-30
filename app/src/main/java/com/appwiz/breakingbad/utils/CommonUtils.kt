package com.appwiz.breakingbad.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.appwiz.breakingbad.model.Quote
import java.lang.Exception

class CommonUtils {
    fun copyQuote(context: Context, quote: Quote) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
        val content = "\"" + quote.quote + "\"\n" + quote.author + ", " + quote.series
        val clip = ClipData.newPlainText("label", content)
        clipboard!!.setPrimaryClip(clip)
        Toast.makeText(context, "copied to clipboard", Toast.LENGTH_SHORT).show()
    }

    fun shareSocial(context: Context, packageName:String, quote: Quote) {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.type = "text/plain"
        intent.setPackage(packageName)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        val content = "\"" + quote.quote + "\"\n" + quote.author + ", " + quote.series
        intent.putExtra(Intent.EXTRA_TEXT, content)
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            val url = "https://play.google.com/store/apps/details?id=${packageName}"
            val _intent = Intent(Intent.ACTION_VIEW)
            _intent.data = Uri.parse(url)
            context.startActivity(_intent)
        }
    }
}