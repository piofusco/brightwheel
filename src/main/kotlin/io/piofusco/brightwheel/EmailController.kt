package io.piofusco.brightwheel

import io.piofusco.brightwheel.core.EmailDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/email")
class EmailController(
    val emailService: EmailService
) {
    @PostMapping
    fun sendEmail(@RequestBody email: EmailDTO): ResponseEntity<EmailDTO> {
        emailService.send(email)
        return ResponseEntity(HttpStatus.CREATED)
    }
}
