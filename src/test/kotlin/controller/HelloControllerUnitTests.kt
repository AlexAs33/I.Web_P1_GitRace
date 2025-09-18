package es.unizar.webeng.hello.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.context.MessageSource
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.ui.Model
import org.springframework.ui.ExtendedModelMap

class HelloControllerUnitTests {
    private lateinit var controller: HelloController
    private lateinit var model: Model
    private lateinit var messageSource: MessageSource
    
    @BeforeEach
    fun setup() {
        val ms = ResourceBundleMessageSource()
        ms.setBasename("messages")
        ms.setDefaultEncoding("UTF-8")
        messageSource = ms
        controller = HelloController("Test Message", messageSource)
        model = ExtendedModelMap()
    }
    
    @Test
    fun `should return welcome view with default message`() {
        val view = controller.welcome(model, "", null)
        
        assertThat(view).isEqualTo("welcome")
        assertThat(model.getAttribute("message")).isEqualTo("Test Message")
        assertThat(model.getAttribute("name")).isEqualTo("")
    }
    
    @Test
    fun `should return welcome view with personalized message`() {
        val view = controller.welcome(model, "Developer", null)
        
        assertThat(view).isEqualTo("welcome")
        assertThat(model.getAttribute("message")).isEqualTo("Hello, Developer!")
        assertThat(model.getAttribute("name")).isEqualTo("Developer")
    }
    
    @Test
    fun `should return API response with timestamp`() {
        val apiController = HelloApiController(messageSource)
        val response = apiController.helloApi("Test", null)
        
        assertThat(response).containsKey("message")
        assertThat(response).containsKey("timestamp")
        assertThat(response["message"]).isEqualTo("Hello, Test!")
        assertThat(response["timestamp"]).isNotNull()
    }
}
