package es.unizar.webeng.hello.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.context.support.ResourceBundleMessageSource

class HelloApiControllerI18nTests {

    private lateinit var controller: HelloApiController
    private lateinit var messageSource: ResourceBundleMessageSource

    @BeforeEach
    fun setup() {
        messageSource = ResourceBundleMessageSource()
        messageSource.setBasename("messages")
        messageSource.setDefaultEncoding("UTF-8")
        controller = HelloApiController(messageSource)
    }

    @Test
    fun `should return greeting in default English`() {
        val response = controller.helloApi("Alice", null)

        assertThat(response["message"]).isEqualTo("Hello, Alice!")
        assertThat(response).containsKey("timestamp")
    }

    @Test
    fun `should return greeting in Spanish`() {
        val response = controller.helloApi("Ana", "es")

        assertThat(response["message"]).isEqualTo("¡Hola, Ana!")
        assertThat(response).containsKey("timestamp")
    }

    @Test
    fun `should return greeting in French`() {
        val response = controller.helloApi("Jean", "fr")

        assertThat(response["message"]).isEqualTo("Salut, Jean!")
        assertThat(response).containsKey("timestamp")
    }

    @Test
    fun `should fallback to English when language not supported`() {
        val response = controller.helloApi("Luca", "it")

        assertThat(response["message"]).isEqualTo("Hello, Luca!")
        assertThat(response).containsKey("timestamp")
    }

}
