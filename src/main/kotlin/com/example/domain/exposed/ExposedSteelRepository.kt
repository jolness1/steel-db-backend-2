package com.example.domain.exposed

import SteelRecord
import com.example.domain.datamodel.Steel
import com.example.domain.repository.SteelRepository
import java.util.UUID

class ExposedSteelRepository : SteelRepository {
    override fun fetchSteelWithExternalLinks(id: UUID): Steel? {
        return SteelRecord.find { SteelTable.id eq id }
            .firstOrNull()
            ?.let { record ->
                record.externalLinks().load()
                record.toDomain()
            }
    }
}