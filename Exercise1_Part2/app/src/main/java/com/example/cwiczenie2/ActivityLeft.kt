package com.example.cwiczenie2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.ServiceWorkerWebSettings
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class ActivityLeft : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_left)

        fun runDial(phoneNum: String) {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNum")
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
        fun runBrowser(url: String) {
            var url_standardized = url
            if (!url_standardized.startsWith("http://") && !url_standardized.startsWith("https://"))
                url_standardized = "http://" + url;
            val intent = Intent(Intent.ACTION_VIEW).apply {

                data = Uri.parse(url_standardized)
            }
            startActivity(intent)
        }
        fun runSMS(sms: String) {
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("sms: 729 644 132"))
            intent.putExtra("sms_body", sms)
            startActivity(intent)
        }
        fun runMaps(mapName: String) {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("http://maps.google.com/maps?q=$mapName")
            }
            startActivity(intent)
        }
        val buttonRunDialer = findViewById<Button>(R.id.runDialer)
        val buttonRunBrowser = findViewById<Button>(R.id.runBrowser)
        val buttonSendSMS = findViewById<Button>(R.id.sendSMS)
        val buttonGeoLoc = findViewById<Button>(R.id.geoLoc)
        buttonRunDialer.setOnClickListener { _ ->
            val phoneNumber = findViewById<TextView>(R.id.phoneNumber)
            runDial(phoneNumber.text.toString())
        }
        buttonRunBrowser.setOnClickListener { _ ->
            val url = findViewById<TextView>(R.id.website)
            runBrowser(url.text.toString())
        }
        buttonSendSMS.setOnClickListener { _ ->
            val sms = findViewById<TextView>(R.id.sms)
            runSMS(sms.text.toString())
        }
        buttonGeoLoc.setOnClickListener { _ ->
            val mapName = findViewById<TextView>(R.id.mapName)
            runMaps(mapName.text.toString())
        }


    }
}