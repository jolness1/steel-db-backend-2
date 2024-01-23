package com.example.domain.exposed

import SteelTable
import com.example.domain.datamodel.ExternalLink

import com.example.domain.repository.ExternalLinksRepository
import com.example.domain.table.ExternalLinkRecord
import com.example.domain.table.toDomain
import java.util.UUID
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction

class ExposedExternalLinksRepository : ExternalLinksRepository {

    override fun createExternalLink(steelId: UUID, link: ExternalLink): ExternalLink {
        return transaction {
            val createdLinkRecord = ExternalLinkRecord.new {
                this.steelId = EntityID(steelId, SteelTable)
                linkValue = link.linkValue
                isActive = link.isActive
            }
            createdLinkRecord.toDomain()
        }
    }

    override fun updateExternalLink() {}

    override fun deleteExternalLinks() {}
}