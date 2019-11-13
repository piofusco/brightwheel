package io.piofusco.brightwheel

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BrightwheelApplication

fun main(args: Array<String>) {
	runApplication<BrightwheelApplication>(*args)
}
