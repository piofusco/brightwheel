package io.piofusco.brightwheel.core

data class EmailDTO(
    override val to: String?,
    override val to_name: String?,
    override val from: String?,
    override val from_name: String?,
    override val subject: String?,
    override val body: String?
) : Email