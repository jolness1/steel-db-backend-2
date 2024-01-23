package com.example.domain.repository

import com.example.domain.datamodel.ExternalLink
import java.util.UUID

interface ExternalLinksRepository {

    fun createExternalLink(steelId: UUID, link: ExternalLink): ExternalLink

    fun updateExternalLink() {}

    fun deleteExternalLinks() {}
}