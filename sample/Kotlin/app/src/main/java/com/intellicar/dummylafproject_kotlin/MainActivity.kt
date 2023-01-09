package com.intellicar.dummylafproject_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.intellicar.blesdk.LAFM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var lafm: LAFM

    private val jsonString = "{\"vehicleno\": \"KA03MF8135\",\"device_id\": \"16E7AD8FC567D55E\",\"device_name\": \"16E7AD8FC567D55E\",\"debug\": false,\"token\": \"0110270000000000009FABE2ED8C01000000F8813196850100000C0100020003000400050006000B000C001300E803E903EA033BFE699031584EA41D0C1F5FEB6C2FD7906B38A7EC8EF4D0AB124B7523C3DD1DF860DFD4782DDFC41B4F19AB161CE7283510A8F4FFBB2AFD1E9E6D95D9F1BC0B520CC6423CEC920DEF7A15EF41A04150B5D1D817615B54CC122EFE102A542960DFCF1CFA99877557DEF80736BC3D28E439C89944795E3C099B4480CF47407100\"}"
    private var tokenObject: JSONObject = JSONObject(jsonString)
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lafm = LAFM.getInstance(this)

        var keyPair = lafm.generateKeyPair()

//        lafm.setToken(tokenObject, keyPair.privateKey.toString())
        lafm.setToken(tokenObject, "902A7D2E75E893F9C1FFEE21DA125487578A01AF627923EC3419AC2BC987D840")

        lafm.callbackHandler { json ->
            runOnUiThread {
                findViewById<TextView>(R.id.debugMsg).append(json.toString())
                findViewById<TextView>(R.id.debugMsg).append("\n\n")
            }
        }

        findViewById<Button>(R.id.connect).setOnClickListener {
            var command = JSONObject()
            command.accumulate("action", "get_device_info")
            lafm.exec(command)
        }

        findViewById<Button>(R.id.disconnect).setOnClickListener {
            var command = JSONObject()
            command.accumulate("action", "disconnect")
            lafm.exec(command)
        }

        findViewById<Button>(R.id.lock).setOnClickListener {
            var command = JSONObject()
            command.accumulate("action", "lock")
            lafm.exec(command)
        }

        findViewById<Button>(R.id.unlock).setOnClickListener {
            var command = JSONObject()
            command.accumulate("action", "unlock")
            lafm.exec(command)
        }


    }
}