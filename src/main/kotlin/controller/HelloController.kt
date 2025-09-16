package es.unizar.webeng.hello.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.MessageSource
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@Controller
class HelloController(
    @param:Value("\${app.message:Hello World}")
    private val message: String,
    private val messageSource: MessageSource
) {
    /**
     * If the user indicates ?lang=es|es|fr it will use messages*.properties
     * If not, it maintains the original behaviour.
     */
    @GetMapping("/")
    fun welcome(
        model: Model,
        @RequestParam(defaultValue = "") name: String,
        @RequestParam(defaultValue = "") lang: String?
    ): String {
        val greeting = try{
            if (!lang.isNullOrBlank() && (lang == "en" || lang == "es" || lang == "fr")){
                val effectiveName = if (name.isNotBlank()) name else "World"
                val locale = Locale.forLanguageTag(lang)
                messageSource.getMessage("greeting", arrayOf(effectiveName),locale)

            } else {
                if (name.isNotBlank()) "Hello, $name!" else message
            }
        } catch (e: Exception){
            if (name.isNotBlank()) "Hello, $name!" else message
        }
        model.addAttribute("message", greeting)
        model.addAttribute("name", name)
        return "welcome"
    }
}

@RestController
class HelloApiController (private val messageSource: MessageSource) {
    /**
     * If the user indicates ?lang=es|es|fr it will use messages*.properties
     * If not, it maintains the original behaviour.
     */
    @GetMapping("/api/hello", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun helloApi(
        @RequestParam(defaultValue = "World") name: String,
        @RequestParam(required = false)lang: String?
    ): Map<String, String> {
        val greeting = try {
            if (!lang.isNullOrBlank() && (lang == "en" || lang == "es" || lang == "fr")){
                val locale = Locale.forLanguageTag(lang)
                messageSource.getMessage("greeting", arrayOf(name), locale)
            } else {
                "Hello, $name!"
            }
        } catch (e: Exception){
            "Hello, $name!"
        }
        return mapOf(
            "message" to greeting,
            "timestamp" to java.time.Instant.now().toString()
        )
    }
}
