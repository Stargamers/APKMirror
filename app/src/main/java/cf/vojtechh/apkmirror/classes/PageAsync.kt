package cf.vojtechh.apkmirror.classes

import android.graphics.Color
import android.os.AsyncTask

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

import java.io.IOException

import cf.vojtechh.apkmirror.interfaces.AsyncResponse

class PageAsync : AsyncTask<String, Int, Int>() {

    var response: AsyncResponse? = null

    override fun doInBackground(vararg url: String): Int? {
        try {
            val doc = Jsoup.connect(url[0]).get()
            val metaElements = doc.select("meta[name=theme-color]")
            return Color.parseColor(if (metaElements.size != 0) metaElements[0].attr("content") else "#FF8B14")
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

    }

    override fun onPostExecute(result: Int?) {
        if (result != null)
            response!!.onProcessFinish(result)
        else
            response!!.onProcessFinish(Color.parseColor("#FF8B14"))
    }
}

