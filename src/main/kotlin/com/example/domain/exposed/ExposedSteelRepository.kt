package com.example.domain.exposed

import SteelRecord
import com.example.domain.datamodel.Steel
import com.example.domain.repository.SteelRepository
import com.example.domain.table.ExternalLinkRecord
import java.util.UUID
import org.jetbrains.exposed.sql.transactions.transaction

class ExposedSteelRepository : SteelRepository {

    fun createSteel(steel: Steel): Steel {
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

            createdSteelRecord.toDomain() // Convert record back to domain object
        }
    }


    override fun updateSteel(): Steel {

    }

    override fun deleteSteel(): {

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