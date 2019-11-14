package io.piofusco.brightwheel.core

import javax.validation.constraints.NotBlank

data class EmailDAO(
    @field:NotBlank
    override val to: String?,

    @field:NotBlank
    override val to_name: String?,

    @field:NotBlank
    override val from: String?,

    @field:NotBlank
    override val from_name: String?,

    @field:NotBlank
    override val subject: String?,

    @field:NotBlank
    override val body: String?
) : Email