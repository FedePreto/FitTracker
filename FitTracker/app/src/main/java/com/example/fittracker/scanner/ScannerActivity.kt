/*package com.example.fittracker.scanner

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.fittracker.aggiungi.AggiungiActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.ScanOptions
import kotlinx.android.synthetic.main.fragment_ricerca.*

class ScannerActivity : AppCompatActivity() {

    private val requestCodeCameraPermission = 1001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (ContextCompat.checkSelfPermission(this@ScannerActivity, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
        ) {
            chiediPermessi()
        } else {
            setupControlli()
        }
    }


    private fun setupControlli(){
                val scanOptions = ScanOptions()
                scanOptions.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES)
                scanOptions.setBeepEnabled(false)
                scanOptions.setOrientationLocked(true)
                scanOptions.setPrompt("Lettura codice a barre")
        }

    private fun chiediPermessi(){
        ActivityCompat.requestPermissions(this@ScannerActivity,
            arrayOf(android.Manifest.permission.CAMERA),
            requestCodeCameraPermission)
    }

override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (resultCode == Activity.RESULT_OK) {
        val result = IntentIntegrator.parseActivityResult(resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
/*
        override fun receiveDetections(detections: Detector.Detections<Barcode>) {
            if(detections.detectedItems.isNotEmpty()){
                val qrCodes: SparseArray<Barcode> = detections.detectedItems
                val code = qrCodes.valueAt(0)
                val bottone = intent.extras!!.getString("bottone")
                val aggiungi = Intent(this@ScannerActivity, AggiungiActivity::class.java)
                aggiungi.putExtra("upc",code.displayValue)
                aggiungi.putExtra("bottone",bottone)
                startActivity(aggiungi)
                finish()
            }else{
                textScanResult.text = "Nessun codice trovato"
            }

        }


    }

 */

    override fun onBackPressed() {
        super.onBackPressed()
        val bottone = intent.extras!!.getString("bottone")
        val aggiungi = Intent(this@ScannerActivity,AggiungiActivity::class.java)
        aggiungi.putExtra("bottone",bottone )
        startActivity(aggiungi)
        finish()
    }


}
 */