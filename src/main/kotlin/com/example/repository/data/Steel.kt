package com.example.repository.data

import java.util.UUID
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

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