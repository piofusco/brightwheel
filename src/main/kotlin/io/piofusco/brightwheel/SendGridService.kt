package io.piofusco.brightwheel

import com.fasterxml.jackson.databind.ObjectMapper
import com.sendgrid.Client
import com.sendgrid.Method
import com.sendgrid.Request
import com.sendgrid.SendGrid
import io.piofusco.brightwheel.core.Email
import io.piofusco.brightwheel.core.EmailDAO
import org.springframework.stereotype.Service
import javax.validation.Validator

interface EmailService {
    fun send(email: Email)
}

@Service
class SendGridService(
    val validator: Validator,
    val sendGridClient: SendGrid
) : EmailService {
    override fun send(email: Email) {
        val emailDAO = EmailDAO(
            to = email.to,
            to_name = email.to_name,
            from = email.from,
            from_name = email.from_name,
            subject = email.subject,
            body = email.body
        )

        validate(emailDAO)

        try {
            sendEmail(emailDAO)
        } catch (ex: Exception) {
            println(ex.message)
        }
    }

    private fun sendEmail(email: Email) {
        val expectedRequest = Request()
        expectedRequest.baseUri = "https://api.sendgrid.com/v3/mail/send"
        expectedRequest.addHeader("Authorization: Bearer", "")
        expectedRequest.addHeader("Content-Type", "application/json")
        expectedRequest.body = ObjectMapper().writeValueAsString(email)
        expectedRequest.method = Method.POST
        sendGridClient.api(expectedRequest)
    }

    private fun validate(emailDAO: EmailDAO) {
        val violations = validator.validate(emailDAO)
        if (violations.isEmpty()) {
            return
        } else {
            throw EmailValidationException(violations.map { "${it.propertyPath} ${it.message}" })
        }
    }
}

data class EmailValidationException(val result: List<String>) : RuntimeException()