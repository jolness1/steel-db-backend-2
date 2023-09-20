import com.example.repository.data.Steel
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import java.util.UUID
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.timestamp

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
}

fun toDomain(record: SteelRecord): Steel {
    return Steel(
        id = record.id.value,
        createdAt = record.createdAt,
        name = record.name,
        manufacturer = record.manufacturer,
        dataSheet = record.dataSheet,
        history = record.history,
        description = record.description,
        edgeRetention = record.edgeRetention,
        stainless = record.stainless,
        toughness = record.toughness,
        sharpening = record.sharpening,
        particleMetallurgy = record.particleMetallurgy
    )
}