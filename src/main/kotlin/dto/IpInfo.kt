package dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IpInfo(
    @SerialName("status")
    val status: String,
    @SerialName("country")
    val country: String,
    @SerialName("countryCode")
    val countryCode: String,
    @SerialName("region")
    val region: String,
    @SerialName("regionName")
    val regionName: String,
    @SerialName("city")
    val city: String,
    @SerialName("district")
    val district: String,
    @SerialName("zip")
    val zip: String,
    @SerialName("lat")
    val lat: Double,
    @SerialName("lon")
    val lon: Double,
    @SerialName("timezone")
    val timezone: String,
    @SerialName("offset")
    val offset: Int,
    @SerialName("currency")
    val currency: String,
    @SerialName("isp")
    val isp: String,
    @SerialName("org")
    val org: String,
    @SerialName("as")
    val asX: String,
    @SerialName("asname")
    val asname: String,
    @SerialName("reverse")
    val reverse: String,
    @SerialName("mobile")
    val mobile: Boolean,
    @SerialName("proxy")
    val proxy: Boolean,
    @SerialName("hosting")
    val hosting: Boolean,
    @SerialName("query")
    val query: String
)