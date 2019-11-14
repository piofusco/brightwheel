package io.piofusco.brightwheel

import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.mockk
import io.mockk.verify
import io.piofusco.brightwheel.core.EmailDTO
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(SpringExtension::class)
@WebMvcTest(EmailController::class)
class EmailControllerTest {
    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun mockEmailService() = mockk<EmailService>(relaxed = true)
    }

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var mockEmailService: EmailService

    @Autowired
    lateinit var subject: EmailController

    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders
            .standaloneSetup(subject)
            .build()
    }

    @Test
    fun `POST should send response body to email service if all fields are present`() {
        val emailToPost = EmailDTO(
            to = "some to",
            to_name = "some to_name",
            from = "some from",
            from_name = "some from_name",
            subject = "some subject",
            body = "some body"
        )

        mockMvc.perform(
            MockMvcRequestBuilders.post("/email")
                .content(objectMapper.writeValueAsString(emailToPost))
                .contentType(MediaType.APPLICATION_JSON)

        )
            .andExpect(MockMvcResultMatchers.status().isCreated)

        verify { mockEmailService.send(emailToPost) }
    }
}