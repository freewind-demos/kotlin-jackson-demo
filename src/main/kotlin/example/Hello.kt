package example

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName


private val site = Site("demo", listOf(
        Category("java-demos", listOf(
                Article("demo1", "content of demo1", System.currentTimeMillis(), State.Published),
                Article("demo2", null, System.currentTimeMillis(), State.Draft)
        ))
))

fun main(args: Array<String>) {
    val gson = GsonBuilder().setPrettyPrinting().create()
    val json = gson.toJson(site)
    println(json)

    val site = gson.fromJson(json, Site::class.java)
    println(site)
}


enum class State(val value: String) {
    @SerializedName("_published_")
    Published("_published_"),

    @SerializedName("_draft_")
    Draft("_draft_")
}

data class Article(val title: String, val content: String?, val timestamp: Long, val state: State)
data class Category(val title: String, val articles: List<Article>)
data class Site(val name: String, val categories: List<Category>)
