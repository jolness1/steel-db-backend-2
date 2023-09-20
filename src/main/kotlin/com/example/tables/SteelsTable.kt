import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import java.util.UUID
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.timestamp

object Steels : UUIDTable() {
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

class Steel(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<Steel>(Steels)

    var createdAt by Steels.createdAt
    var name by Steels.name
    var manufacturer by Steels.manufacturer
    var dataSheet by Steels.dataSheet
    var history by Steels.history
    var description by Steels.description
    var edgeRetention by Steels.edgeRetention
    var stainless by Steels.stainless
    var toughness by Steels.toughness
    var sharpening by Steels.sharpening
    var particleMetallurgy by Steels.particleMetallurgy
}