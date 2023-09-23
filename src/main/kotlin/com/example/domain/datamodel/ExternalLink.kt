package com.example.domain.datamodel

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import java.util.UUID

data class ExternalLink(
    val linkedId: UUID,
    val createdAt: Instant = Clock.System.now(),
    val steelId: UUID,
    val linkValue: String,
    val isActive: Boolean = true
)
