package com.example.plugins

import com.example.domain.exposed.ExposedSteelRepository
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

        // search by id
        get("/steel/{id}") {
            val steelId = call.parameters.getOrFail<UUID>("id")
            val steel = ExposedSteelRepository().fetchSteelWithExternalLinks(steelId) ?: return@get call.respond(
                HttpStatusCode.NotFound
            )
            call.respond(steel)
        }

        // Returns list of steels that match the string. ie S3 will return S30V and S35VN
        get("/steels/{name}") {
            val nameQuery = call.request.queryParameters["name"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            val matchingSteels = ExposedSteelRepository().fetchSteelByName(nameQuery)
            call.respond(matchingSteels)
        }

    }
}
