package com.example.domain.table

import SteelTable
import com.example.domain.datamodel.ExternalLink
import kotlinx.datetime.toKotlinInstant
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.timestamp
import java.util.UUID

object ExternalLinkTable : UUIDTable() {
    val createdAt = timestamp("created_at")
    val steelId = reference("steels_id", SteelTable.id)
    val linkValue = varchar("link_value", 255)
    val isActive = bool("is_active").default(true)
}

class ExternalLinkRecord(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<ExternalLinkRecord>(ExternalLinkTable)

    var createdAt by ExternalLinkTable.createdAt
    var steelId by ExternalLinkTable.steelId
    var linkValue by ExternalLinkTable.linkValue
    var isActive by ExternalLinkTable.isActive
}

fun toDomain(record: ExternalLinkRecord): ExternalLink {
    return ExternalLink(
        linkedId = record.id.value,
        createdAt = record.createdAt.toKotlinInstant(),
        steelId = record.steelId.value,
        linkValue = record.linkValue,
        isActive = record.isActive
    )
}
