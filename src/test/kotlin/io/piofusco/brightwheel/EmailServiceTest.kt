package io.piofusco.brightwheel

import com.fasterxml.jackson.databind.ObjectMapper
import com.sendgrid.Method
import com.sendgrid.Request
import com.sendgrid.SendGrid
import io.mockk.mockk
import io.mockk.verify
import io.piofusco.brightwheel.core.EmailDAO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import javax.validation.Validation
import javax.validation.Validator

class EmailServiceTest {
    lateinit var validator: Validator
    lateinit var mockSendGrid: SendGrid
    lateinit var subject: EmailService

    @BeforeEach
    fun setUp() {
        validator = Validation.buildDefaultValidatorFactory().validator
        mockSendGrid = mockk(relaxed = true)
        subject = SendGridService(
            validator = validator,
            sendGridClient = mockSendGrid
        )
    }

    @Test
    fun `when an email with a missing field is sent, should throw a validation error`() {
        val emailToPost = EmailDAO(
            to = null,
            to_name = "some to_name",
            from = "some from",
            from_name = "some from_name",
            subject = "some subject",
            body = "some body"
        )

        val emailValidationException = assertThrows<EmailValidationException> {
            subject.send(emailToPost)
        }

        assertThat(emailValidationException.result.size).isEqualTo(1)
    }

    @Test
    fun `if an email is valid, client should submit post with email info`() {
        val emailToPost = EmailDAO(
            to = "some to",
            to_name = "some to_name",
            from = "some from",
            from_name = "some from_name",
            subject = "some subject",
            body = "some body"
        )
        val expectedRequest = Request()
        expectedRequest.baseUri = "https://api.sendgrid.com/v3/mail/send"
        expectedRequest.addHeader("Authorization: Bearer", "")
        expectedRequest.addHeader("Content-Type", "application/json")
        expectedRequest.body = ObjectMapper().writeValueAsString(emailToPost)
        expectedRequest.method = Method.POST

        subject.send(emailToPost)

        verify { mockSendGrid.api(any()) }
    }
}
