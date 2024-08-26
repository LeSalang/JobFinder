import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.ResponseBody
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class MockInterceptor(private val mockJsonFileName: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val mockJsonStream: InputStream? = this.javaClass.classLoader?.getResourceAsStream(mockJsonFileName)
        val mockResponse = mockJsonStream?.bufferedReader()?.use { it.readText() } ?: ""

        return Response.Builder()
            .request(chain.request())
            .protocol(Protocol.HTTP_1_1)
            .code(200)
            .message("OK")
            .body(
                ResponseBody.create(
                    "application/json".toMediaType(), mockResponse
                )
            )
            .build()
    }
}
