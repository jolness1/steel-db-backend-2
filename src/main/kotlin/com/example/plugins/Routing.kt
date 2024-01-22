package com.example.plugins

import SteelTable
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.ktor.server.util.getOrFail
import java.util.UUID

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/steel/{id}") {
            val steelId = call.parameters.getOrFail<UUID>("id")
            val steel = SteelRecord.find { SteelTable.id eq steelId }
                .firstOrNull()
                ?.toDomain() ?: return@get call.respond(HttpStatusCode.NotFound)
            call.respond(steel)
        }
    }
}
