package io.piofusco.brightwheel.core

interface Email {
    val to: String?
    val to_name: String?
    val from: String?
    val from_name: String?
    val subject: String?
    val body: String?
}