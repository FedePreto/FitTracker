package com.example.fittracker.scanner

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseArray
import android.view.SurfaceHolder
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.util.isNotEmpty
import androidx.databinding.DataBindingUtil
import com.example.fittracker.R
import com.example.fittracker.aggiungi.AggiungiActivity
import com.example.fittracker.databinding.ActivityScannerBinding
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import kotlinx.android.synthetic.main.activity_scanner.*
import java.lang.Exception

class ScannerActivity : AppCompatActivity() {

    private val requestCodeCameraPermission = 1001
    private lateinit var cameraSource : CameraSource
    private lateinit var detector:BarcodeDetector
    private lateinit var binding: ActivityScannerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_scanner)
        binding.cameraSurfaceView.visibility = View.GONE
        if(ContextCompat.checkSelfPermission(this@ScannerActivity,
                android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            chiediPermessi()
        }else{

            setupControlli()
        }
    }

    private fun setupControlli(){
        binding.cameraSurfaceView.setZOrderMediaOverlay(true)
        binding.cameraSurfaceView.visibility = View.VISIBLE
        detector = BarcodeDetector.Builder(this@ScannerActivity).build()
        cameraSource = CameraSource.Builder(this@ScannerActivity,detector)
            .setAutoFocusEnabled(true).build()
        cameraSurfaceView.holder.addCallback(callBack)
        detector.setProcessor(processor)


    }
    private fun chiediPermessi(){
        ActivityCompat.requestPermissions(this@ScannerActivity,
            arrayOf(android.Manifest.permission.CAMERA),
            requestCodeCameraPermission)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == requestCodeCameraPermission && grantResults.isNotEmpty()){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                setupControlli()
            }else{
                Toast.makeText(applicationContext, "Permessi non dati",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val callBack = object : SurfaceHolder.Callback{

        override fun surfaceDestroyed(p0: SurfaceHolder) {
            cameraSource.stop()
        }

        override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

        }

        override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
            try{
                if (ActivityCompat.checkSelfPermission(
                        this@ScannerActivity,
                        android.Manifest.permission.CAMERA
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                cameraSource.start(surfaceHolder)
            }catch (exception:Exception){
                Toast.makeText(applicationContext,"Ops, qualcosa è andato storto",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private val processor = object :Detector.Processor<Barcode>{
        override fun release() {


        }

        override fun receiveDetections(detections: Detector.Detections<Barcode>) {
            if(detections.detectedItems.isNotEmpty()){
                val qrCodes: SparseArray<Barcode> = detections.detectedItems
                val code = qrCodes.valueAt(0)
                val intent = Intent(this@ScannerActivity, AggiungiActivity::class.java)
                intent.putExtra("upc",code.displayValue)
                intent.putExtra("bottone",intent.extras!!.getString("bottone"))
                startActivity(intent)
                finish()
            }else{
                textScanResult.text = "Nessun codice trovato"
            }

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val bottone = intent.extras!!.getString("bottone")
        val intent = Intent(this@ScannerActivity,AggiungiActivity::class.java)
        intent.putExtra("bottone",bottone )
        startActivity(intent)
    }


}