package com.example.domain.exposed

import SteelRecord
import com.example.domain.datamodel.Steel
import com.example.domain.repository.SteelRepository
import com.example.domain.table.ExternalLinkRecord
import com.example.domain.table.ExternalLinkTable
import com.example.exceptions.SteelException
import java.util.UUID
import org.jetbrains.exposed.sql.transactions.transaction

class ExposedSteelRepository : SteelRepository {

    override fun createSteel(steel: Steel): Steel {
        return transaction {
            val createdSteelRecord = SteelRecord.new {
                name = steel.name
                manufacturer = steel.manufacturer
                dataSheet = steel.dataSheet
                history = steel.history
                description = steel.description
                edgeRetention = steel.edgeRetention
                stainless = steel.stainless
                toughness = steel.toughness
                sharpening = steel.sharpening
                particleMetallurgy = steel.particleMetallurgy
            }

            steel.externalLinks.forEach { externalLink ->
                ExternalLinkRecord.new {
                    steelId = createdSteelRecord.id
                    linkValue = externalLink.linkValue
                    isActive = externalLink.isActive
                }
            }

            createdSteelRecord.toDomain()
        }
    }


    override fun updateSteel(): Steel {

    }

    override fun deleteSteel(id: UUID): Steel{
        return transaction {
            val steelRecord = SteelRecord.findById(id) ?: throw SteelException.NotFound(id)
            ExternalLinkRecord.find { ExternalLinkTable.steelId eq id }.forEach { it.delete() }
            steelRecord.delete()
            steelRecord.toDomain()
        }
    }

    override fun fetchSteelWithExternalLinks(id: UUID): Steel? {
        return SteelRecord.find { SteelTable.id eq id }
            .firstOrNull()?.toDomain()
    }

    override fun fetchSteelByName(nameQuery: String): List<Steel> {
        val nameLike = "%$nameQuery%"
        return SteelRecord.find { SteelTable.name like nameLike }
            .map { it.toDomain() }
    }
}