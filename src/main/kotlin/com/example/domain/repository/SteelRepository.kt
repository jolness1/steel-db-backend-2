package com.example.domain.repository

import com.example.domain.datamodel.Steel
import java.util.UUID

interface SteelRepository {

    fun createSteel(): Steel

    fun updateSteel(): Steel

    fun deleteSteel()

    fun fetchSteelWithExternalLinks(id: UUID): Steel?

    fun fetchSteelByName(nameQuery: String): List<Steel>
}
