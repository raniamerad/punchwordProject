package com.example.fetchdatatestpunch

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fetchdatatestpunch.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        fetchCurrencyData().start()
    }

    private fun fetchCurrencyData(): Thread {
        return Thread {
            val url = URL("https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?CMC_PRO_API_KEY=66fc6ce3-95c3-4e8a-a53d-b3fa5a9efe97")
            val connection = url.openConnection() as HttpURLConnection
            if (connection.responseCode == 200) {
                val inputSystem = connection.inputStream
                val inputStreamReader=InputStreamReader(inputSystem, charset("UTF-8"))
                val request=Gson().fromJson(inputStreamReader,Request::class.java)
                updateUI(request)
                inputStreamReader.close()
                inputSystem.close()

            } else {
                runOnUiThread {
                    binding.baseCurrency.text = "Failed"
                }
            }
        }
    }

    private fun updateUI(request: Request?) {
        runOnUiThread {
            kotlin.run {
                if (request != null) {
                   // binding.lastUpdate.text = request.status.timestamp

                    val dataWithId1 = request.data.find { it.id == 23378
                    }
                    val price = dataWithId1?.quote?.USD?.price
                    val dataWithId2 = request.data.find { it.id == 1 }
                    val price2 = dataWithId2?.quote?.USD?.price
                    if (price != null) {
                        binding.lastUpdate.text = price.toString()
                    }
                }
            }
        }
    }
}
