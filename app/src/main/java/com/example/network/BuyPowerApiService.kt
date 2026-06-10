package com.example.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.util.concurrent.TimeUnit
import android.util.Log
import com.example.BuildConfig

data class CustomerInfo(
    val isValid: Boolean,
    val name: String,
    val address: String,
    val errorMessage: String? = null
)

object BuyPowerApiService {
    // Note: BuyPower API requires an authentication token.
    // Configure this in .env (BUY_POWER_API_KEY)
    private const val API_KEY = BuildConfig.BUY_POWER_API_KEY
    private const val BASE_URL = "https://api.buypower.ng/v2/check/meter"

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    suspend fun validateMeter(meterNumber: String, disco: String = "KEDCO"): CustomerInfo = withContext(Dispatchers.IO) {
        if (API_KEY.isEmpty() || API_KEY == "MY_BUYPOWER_KEY") {
            Log.d("BuyPowerApi", "No API key configured. Using Mock fallback for KEDCO validation.")
            return@withContext mockValidation(meterNumber, disco)
        }

        try {
            // Actual API integration format matching buypower endpoints
            val request = Request.Builder()
                .url("$BASE_URL?meter=$meterNumber&disco=$disco")
                .addHeader("Authorization", "Bearer $API_KEY")
                .addHeader("Accept", "application/json")
                .get()
                .build()

            val response = client.newCall(request).execute()
            val responseString = response.body?.string()

            if (response.isSuccessful && responseString != null) {
                val jsonResponse = JSONObject(responseString)
                val status = jsonResponse.optBoolean("status", false)
                
                if (status) {
                    val data = jsonResponse.optJSONObject("data") ?: return@withContext CustomerInfo(false, "", "", "Invalid response from server")
                    
                    val name = data.optString("name", "Unknown Customer")
                    val address = data.optString("address", "Address unavailable")
                    return@withContext CustomerInfo(true, name, address)
                } else {
                    return@withContext CustomerInfo(false, "", "", jsonResponse.optString("message", "Validation failed"))
                }
            } else {
                return@withContext CustomerInfo(false, "", "", "Server Error: ${response.code}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext CustomerInfo(false, "", "", "Connection Error: ${e.message}")
        }
    }

    private suspend fun mockValidation(meterNumber: String, disco: String): CustomerInfo {
        // Simulate network delay
        kotlinx.coroutines.delay(1500)
        
        // Mock successful validation if length is correct for KEDCO
        if (meterNumber.length in 11..13) {
            return CustomerInfo(
                isValid = true,
                name = "Ahmad Daura",
                address = "No 12, Zoo Road, Kano State ($disco)"
            )
        }
        
        return CustomerInfo(
            isValid = false,
            name = "",
            address = "",
            errorMessage = "Invalid meter number format for KEDCO."
        )
    }
}
