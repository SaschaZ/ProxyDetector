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
import org.apache.log4j.BasicConfigurator

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
    BasicConfigurator.configure()

    embeddedServer(Netty, port = 9000, host = "0.0.0.0") {
        val ipProxyMap = HashMap<String, IpInfo>()

        routing {
            get("/") {
                val ip = call.request.origin.remoteHost
                if (ip.endsWith("localhost")) {
                    call.respondText("localhost")
                } else {
                    val result = ipProxyMap[ip] ?: client.get("$HOST$ip$ip$FIELDS")
                    ipProxyMap[ip] = result

                    call.respondText("result for ip $ip: $result")
                }
            }
        }
    }.start(wait = true)
}