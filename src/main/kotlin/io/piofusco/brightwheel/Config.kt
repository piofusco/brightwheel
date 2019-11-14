package io.piofusco.brightwheel

import com.sendgrid.SendGrid
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Config {
    @Bean
    fun sendGridClient() = SendGrid(System.getenv("SENDGRID_API_KEY"))
}