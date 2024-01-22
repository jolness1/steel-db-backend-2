package com.example.domain.repository

import com.example.domain.datamodel.Steel
import java.util.UUID

interface SteelRepository {

    fun fetchSteelWithExternalLinks(id: UUID): Steel? {
        return null
    }

    fun fetchSteelByName(nameQuery: String): List<Steel>
}
