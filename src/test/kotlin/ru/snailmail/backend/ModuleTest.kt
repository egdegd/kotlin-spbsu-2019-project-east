package ru.snailmail.backend

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.testing.*
import io.ktor.sessions.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.*

class ModuleTest {
    @Test
    fun TestGetUsers() {
        val server = embeddedServer(Netty, port = 8080) {
            module()
        }
        server.start(wait = false)
        val reqParam = URLEncoder.encode("", "UTF-8")

        val mURL = URL("http://0.0.0.0:8080/$reqParam")

        lateinit
        var response:String

        with(mURL.openConnection() as HttpURLConnection) {
            requestMethod = "GET"

            BufferedReader(InputStreamReader(inputStream)).use {
                val res = StringBuffer()

                println("jopa")
                var inputLine = it.readLine()
                while (inputLine != null) {
                    res.append(inputLine)
                    inputLine = it.readLine()
                }
                it.close()
                response = res.toString()
            }
            assertEquals(response, "west — lohi!")
        }
    }
}