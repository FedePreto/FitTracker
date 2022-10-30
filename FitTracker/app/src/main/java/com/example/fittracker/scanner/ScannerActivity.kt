package com.example.fittracker.scanner

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseArray
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.fittracker.R
import com.example.fittracker.aggiungi.AggiungiActivity
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.interfaces.Detector
import com.google.zxing.Result
import com.google.zxing.client.android.Intents.Scan.RESULT
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import kotlinx.android.synthetic.main.activity_scanner.*
import kotlinx.android.synthetic.main.fragment_ricerca.*
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScannerActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    var scannerView : ZXingScannerView? = null

    private val requestCodeCameraPermission = 1001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scannerView = ZXingScannerView(this)
        setContentView(scannerView)
        setPermission()
    }

    override fun handleResult(p0: Result?) {
        val intent = Intent(applicationContext, AggiungiActivity::class.java)
        intent.putExtra(RESULT, p0.toString())
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()

        scannerView?.setResultHandler(this)
        scannerView?.startCamera()
    }

    override fun onStop() {
        super.onStop()

        scannerView?.stopCamera()
        onBackPressed()
    }

    private fun setPermission() {
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

        private fun makeRequest(){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),
                1)

        }

        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode,permissions,grantResults)

            when(requestCode){
                1 -> {
                    if(grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(applicationContext, "Hai bisogno dei permessi della fotocamera", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }


        }



}



/*
if (ContextCompat.checkSelfPermission(this@ScannerActivity, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
        ) {
            chiediPermessi()
        } else {
            setupControlli()
        }
    }

    private fun chiediPermessi(){
        ActivityCompat.requestPermissions(this@ScannerActivity,
            arrayOf(android.Manifest.permission.CAMERA),
            requestCodeCameraPermission)


    private fun setupControlli(){
                val scanOptions = ScanOptions()
                scanOptions.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES)
                scanOptions.setBeepEnabled(false)
                scanOptions.setOrientationLocked(true)
                scanOptions.setPrompt("Lettura codice a barre")
        }



override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (resultCode == Activity.RESULT_OK) {
        val result = ScanOptions.parseActivityResult(resultCode, data)
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

         fun receiveDetections(detections: Detector.Detections<Barcode>) {
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
