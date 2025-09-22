package es.unizar.webeng.hello.controller

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class RateLimitFilterTests(
    @Autowired val mockMvc: MockMvc
) {

    @Test
    fun `should return 429 after exceeding rate limit`() {
        // lanza 10 peticiones permitidas
        repeat(10) {
            mockMvc.perform(get("/api/hello"))
                .andExpect(status().isOk)
        }
        // la 11ª debe ser rechazada
        val result = mockMvc.perform(get("/api/hello"))
            .andExpect(status().isTooManyRequests)
            .andReturn()

        // opcional: verificar contenido
        assertEquals("Too Many Requests", result.response.contentAsString)
    }
}