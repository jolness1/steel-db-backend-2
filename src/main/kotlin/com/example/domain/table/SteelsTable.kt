import com.example.domain.datamodel.Steel
import com.example.domain.table.ExternalLinkRecord
import com.example.domain.table.ExternalLinkTable
import com.example.domain.table.toDomain
import kotlinx.datetime.toKotlinInstant
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.timestamp
import java.util.UUID

object SteelTable : UUIDTable() {
    val createdAt = timestamp("created_at")
    val name = varchar("name", 255)
    val manufacturer = varchar("manufacturer", 255)
    val dataSheet = text("data_sheet").nullable()
    val history = text("history").nullable()
    val description = text("description").nullable()
    val edgeRetention = integer("edge_retention").nullable()
    val stainless = integer("stainless").nullable()
    val toughness = integer("toughness").nullable()
    val sharpening = integer("sharpening").nullable()
    val particleMetallurgy = bool("particle_metallurgy").nullable()
    val externalLinks = reference("external_links", ExternalLinkTable)
}

class SteelRecord(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<SteelRecord>(SteelTable)

    var createdAt by SteelTable.createdAt
    var name by SteelTable.name
    var manufacturer by SteelTable.manufacturer
    var dataSheet by SteelTable.dataSheet
    var history by SteelTable.history
    var description by SteelTable.description
    var edgeRetention by SteelTable.edgeRetention
    var stainless by SteelTable.stainless
    var toughness by SteelTable.toughness
    var sharpening by SteelTable.sharpening
    var particleMetallurgy by SteelTable.particleMetallurgy

    fun toDomain(): Steel {
        val externalLinks = externalLinks().map { it.toDomain() }
        return Steel(
            id = id.value,
            createdAt = createdAt.toKotlinInstant(),
            name = name,
            manufacturer = manufacturer,
            dataSheet = dataSheet,
            history = history,
            description = description,
            edgeRetention = edgeRetention,
            stainless = stainless,
            toughness = toughness,
            sharpening = sharpening,
            particleMetallurgy = particleMetallurgy,
            externalLinks = externalLinks
        )
    }

    private fun externalLinks(): List<ExternalLinkRecord> {
        return ExternalLinkRecord.find { ExternalLinkTable.steelId eq id }.toList()
    }
}
