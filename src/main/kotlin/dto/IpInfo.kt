package dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IpInfo(
    @SerialName("status")
    val status: String? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("countryCode")
    val countryCode: String? = null,
    @SerialName("region")
    val region: String? = null,
    @SerialName("regionName")
    val regionName: String? = null,
    @SerialName("city")
    val city: String? = null,
    @SerialName("district")
    val district: String? = null,
    @SerialName("zip")
    val zip: String? = null,
    @SerialName("lat")
    val lat: Double? = null,
    @SerialName("lon")
    val lon: Double? = null,
    @SerialName("timezone")
    val timezone: String? = null,
    @SerialName("offset")
    val offset: Int? = null,
    @SerialName("currency")
    val currency: String? = null,
    @SerialName("isp")
    val isp: String? = null,
    @SerialName("org")
    val org: String? = null,
    @SerialName("as")
    val asX: String? = null,
    @SerialName("asname")
    val asname: String? = null,
    @SerialName("reverse")
    val reverse: String? = null,
    @SerialName("mobile")
    val mobile: Boolean? = null,
    @SerialName("proxy")
    val proxy: Boolean? = null,
    @SerialName("hosting")
    val hosting: Boolean? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("query")
    val query: String? = null
)