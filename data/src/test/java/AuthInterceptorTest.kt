import com.vnykyt.placesearch.config.EnvironmentConfig
import com.vnykyt.placesearch.data.network.auth.AuthInterceptor
import com.vnykyt.placesearch.data.network.auth.AuthorizationType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class AuthInterceptorTest {

    @ParameterizedTest
    @EnumSource(AuthorizationType::class)
    @DisplayName("Check if every network request contains the auth keys")
    fun onRequestAddToken(authorizationType: AuthorizationType) {
        val mockWebServer = MockWebServer().apply {
            start()
            enqueue(MockResponse())
        }

        val authInterceptor = AuthInterceptor()

        val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .build()

        val authRequest = Request.Builder()
            .url(mockWebServer.url("/"))
            .tag(type = AuthorizationType::class.java, tag = authorizationType)
            .build()
        okHttpClient.newCall(authRequest).execute()

        val requestUrl = mockWebServer.takeRequest().requestUrl
        when (authorizationType) {
            AuthorizationType.FOURSQUARE -> {
                Assertions.assertEquals(requestUrl?.queryParameter("client_id"), EnvironmentConfig.foursquareApiKey())
                Assertions.assertEquals(requestUrl?.queryParameter("client_secret"), EnvironmentConfig.foursquareSecret())
                Assertions.assertEquals(requestUrl?.queryParameter("v"), "20190425")
            }
            AuthorizationType.GOOGLE_MAPS -> {
                Assertions.assertEquals(requestUrl?.queryParameter("key"), EnvironmentConfig.googleMapsApiKey())
            }
            else -> {
                Assertions.assertNotEquals(requestUrl?.queryParameter("client_id"), EnvironmentConfig.foursquareApiKey())
                Assertions.assertNotEquals(requestUrl?.queryParameter("client_secret"), EnvironmentConfig.foursquareSecret())
                Assertions.assertNotEquals(requestUrl?.queryParameter("v"), "20190425")
                Assertions.assertNotEquals(requestUrl?.queryParameter("key"), EnvironmentConfig.googleMapsApiKey())
            }
        }
        mockWebServer.shutdown()
    }
}