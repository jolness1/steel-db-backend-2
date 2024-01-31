package com.example.domain.exposed

import SteelRecord
import com.example.domain.datamodel.Steel
import com.example.domain.repository.SteelRepository
import com.example.domain.table.ExternalLinkRecord
import com.example.domain.table.ExternalLinkTable
import com.example.exceptions.SteelException
import java.util.UUID
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

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


    override fun updateSteel(id: UUID, updates: Map<String, Any?>): Steel {
        return transaction {
            val steelRecord = SteelRecord.findById(id) ?: throw SteelException.NotFound(id)

            SteelTable.update({ SteelTable.id eq id }) {
                updates.forEach { (fieldName, value) ->
                    when (fieldName) {
                        "name" -> it[name] = value as? String ?: steelRecord.name
                        "manufacturer" -> it[manufacturer] = value as? String ?: steelRecord.manufacturer
                        "dataSheet" -> it[dataSheet] = value as String?
                        "history" -> it[history] = value as String?
                        "description" -> it[description] = value as String?
                        "edgeRetention" -> it[edgeRetention] = value as Int?
                        "stainless" -> it[stainless] = value as Int?
                        "toughness" -> it[toughness] = value as Int?
                        "sharpening" -> it[sharpening] = value as Int?
                        "particleMetallurgy" -> it[particleMetallurgy] = value as Boolean?
                        "externalLinks" -> {
                            // Handle updating or creating external links
                            val updatedLinks = value as List<Map<String, Any?>>
                            for (updatedLink in updatedLinks) {
                                val linkId = updatedLink["id"] as UUID?
                                var linkValue = updatedLink["linkValue"] as String?
                                var isActive = updatedLink["isActive"] as Boolean?

                                if (linkId != null) {
                                    // Update existing link if it currently exists
                                    val existingLink = ExternalLinkRecord.findById(linkId) ?: continue
                                    if (linkValue != null) existingLink.linkValue = linkValue
                                    if (isActive != null) existingLink.isActive = isActive
                                    existingLink.flush()
                                } else {
                                    // Create new link if none exists
                                    ExternalLinkRecord.new {
                                        steelId = steelRecord.id
                                        linkValue = linkValue ?: ""
                                        isActive = isActive ?: true
                                    }.flush()
                                }
                            }
                        }
                    }
                }
            }
            steelRecord.refresh()
            steelRecord.toDomain()
        }
    }


    override fun deleteSteel(id: UUID): Steel {
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