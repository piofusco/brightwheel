package io.piofusco.brightwheel

import io.piofusco.brightwheel.core.EmailDAO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import javax.validation.Validation
import javax.validation.Validator

class EmailServiceTest {
    lateinit var validator: Validator
    lateinit var subject: EmailService

    @BeforeEach
    fun setUp() {
        validator = Validation.buildDefaultValidatorFactory().validator
        subject = EmailService(validator = validator)
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
}
