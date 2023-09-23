package com.example.domain.datamodel

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import java.util.UUID

data class Steel(
    val id: UUID,
    val createdAt: Instant = Clock.System.now(),
    val name: String,
    val manufacturer: String,
    val dataSheet: String?,
    val history: String?,
    val description: String?,
    val edgeRetention: Int?,
    val stainless: Int?,
    val toughness: Int?,
    val sharpening: Int?,
    val particleMetallurgy: Boolean?
)
