package es.unizar.webeng.hello.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.ui.ExtendedModelMap
import org.springframework.ui.Model

class HelloControllerI18nTests {

    private lateinit var controller: HelloController
    private lateinit var model: Model

    @BeforeEach
    fun setup() {
        val messageSource = ResourceBundleMessageSource()
        messageSource.setBasename("messages")
        messageSource.setDefaultEncoding("UTF-8")
        controller = HelloController("Test Message", messageSource)
        model = ExtendedModelMap()
    }

    @Test
    fun `should return greeting in default English`() {
        val view = controller.welcome(model, "Alice", null)

        assertThat(view).isEqualTo("welcome")
        assertThat(model.getAttribute("message")).isEqualTo("Hello, Alice!")
        assertThat(model.getAttribute("name")).isEqualTo("Alice")
    }

    @Test
    fun `should return greeting in Spanish`() {
        val view = controller.welcome(model, "Ana", "es")

        assertThat(view).isEqualTo("welcome")
        assertThat(model.getAttribute("message")).isEqualTo("Hola, Ana!")
        assertThat(model.getAttribute("name")).isEqualTo("Ana")
    }

    @Test
    fun `should return greeting in French`() {
        val view = controller.welcome(model, "Jean", "fr")

        assertThat(view).isEqualTo("welcome")
        assertThat(model.getAttribute("message")).isEqualTo("Salut, Jean!")
        assertThat(model.getAttribute("name")).isEqualTo("Jean")
    }

    @Test
    fun `should fallback to English when language not supported`() {
        val view = controller.welcome(model, "Luca", "it")

        assertThat(view).isEqualTo("welcome")
        assertThat(model.getAttribute("message")).isEqualTo("Hello, Luca!")
        assertThat(model.getAttribute("name")).isEqualTo("Luca")
    }

}
