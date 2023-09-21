package com.example.repository.data

import java.util.UUID
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class ExternalLink(
    val linkedId: UUID,
    val createdAt: Instant = Clock.System.now(),
    val steelId: UUID,
    val linkValue: String,
    val isActive: Boolean = true
)