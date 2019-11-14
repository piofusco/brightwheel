package io.piofusco.brightwheel

import io.piofusco.brightwheel.core.Email
import io.piofusco.brightwheel.core.EmailDAO
import org.springframework.stereotype.Service
import javax.validation.Validator

@Service
class EmailService(val validator: Validator) {
    fun send(email: Email) {
        val emailDAO = EmailDAO(
            to = email.to,
            to_name = email.to_name,
            from = email.from,
            from_name = email.from_name,
            subject = email.subject,
            body = email.body
        )

        validate(emailDAO)

        return
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