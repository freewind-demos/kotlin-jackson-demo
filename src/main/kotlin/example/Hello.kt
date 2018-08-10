package example

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.registerKotlinModule


private val site = Site("demo", listOf(
        Category("java-demos", listOf(
                Article("demo1", "content of demo1", System.currentTimeMillis(), State.Published),
                Article("demo2", null, System.currentTimeMillis(), State.Draft)
        ))
))

fun main(args: Array<String>) {
    val mapper = ObjectMapper().apply {
        registerKotlinModule()
        enable(SerializationFeature.INDENT_OUTPUT)
    }

    val json = mapper.writeValueAsString(site)
    println(json)

    val site = mapper.readValue(json, Site::class.java)
    println(site)
}


enum class State(@JsonValue val value: String) {
    Published("_published_"), Draft("_draft_")
}

data class Article(val title: String, val content: String?, val timestamp: Long, val state: State)
data class Category(val title: String, val articles: List<Article>)
data class Site(@JsonProperty("site_name") val name: String, val categories: List<Category>)
