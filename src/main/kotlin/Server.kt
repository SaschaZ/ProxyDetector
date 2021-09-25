import dto.IpInfo
import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

private const val HOST = "http://ip-api.com/json/"
private const val FIELDS =
    "?fields=status,message,country,countryCode,region,regionName,city,district,zip,lat,lon,timezone,offset,currency,isp,org,as,asname,reverse,mobile,proxy,hosting,query"

private val client = HttpClient(OkHttp) {
    engine {
        config {
            followRedirects(true)
            followSslRedirects(true)
        }
    }
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
}

fun main() {
    embeddedServer(Netty, port = 80, host = "0.0.0.0") {
        val ipProxyMap = HashMap<String, IpInfo>()

        routing {
            get("/") {
                val ip = call.request.origin.remoteHost
                if (ip.endsWith("localhost")) {
                    call.respondText("localhost")
                } else {
                    val result = ipProxyMap[ip] ?: client.get("$HOST$ip$ip$FIELDS")
                    ipProxyMap[ip] = result

                    call.respondText("proxy: ${result.proxy}")
                }
            }
        }
    }.start(wait = true)
}