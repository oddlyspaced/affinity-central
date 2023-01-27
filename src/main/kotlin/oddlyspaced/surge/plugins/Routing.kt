package oddlyspaced.surge.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import oddlyspaced.surge.routing.nodeRouting

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        nodeRouting()
    }
}