import dto.IpInfo
import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.apache.log4j.BasicConfigurator

private const val HOST = "http://ip-api.com/json/"
private const val FIELDS =
    "?fields=status,message,country,countryCode,region,regionName,city,district,zip,lat,lon,timezone,offset,currency,isp,org,as,asname,reverse,mobile,proxy,hosting,query"

private val json = Json { prettyPrint = true }

private val client = HttpClient(OkHttp) {
    engine {
        config {
            followRedirects(true)
            followSslRedirects(true)
        }
    }
    install(JsonFeature) {
        serializer = KotlinxSerializer(json)
    }
}

fun main() {
    BasicConfigurator.configure()

    embeddedServer(Netty, port = 9001, host = "0.0.0.0") {
        val ipProxyMap = HashMap<String, IpInfo>()

        routing {
            get("/") {
                val ip = call.request.headers["X-Real-Ip"]

                if (ip?.endsWith("localhost") != false) {
                    call.respondText("no result")
                } else {
                    val result = ipProxyMap[ip] ?: client.get("$HOST$ip$FIELDS")
                    ipProxyMap[ip] = result

                    call.respondText(json.encodeToString(result))
                }
            }
        }
    }.start(wait = true)
}