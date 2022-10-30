package com.example.fittracker.scanner

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseArray
import android.view.SurfaceHolder
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.util.isNotEmpty
import com.example.fittracker.R
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)
        if(ContextCompat.checkSelfPermission(this@ScannerActivity,
                android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            chiediPermessi()
        }else{
            setupControlli()
        }
    }

    private fun setupControlli(){
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
        override fun surfaceCreated(p0: SurfaceHolder) {
            try{
                if (ActivityCompat.checkSelfPermission(
                        this@ScannerActivity,
                        android.Manifest.permission.CAMERA
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                cameraSource.start()
            }catch (exception:Exception){
                Toast.makeText(applicationContext,"Ops, qualcosa è andato storto",Toast.LENGTH_SHORT).show()
            }
        }

        override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

        }

        override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
            cameraSource.stop()
        }
    }

    private val processor = object :Detector.Processor<Barcode>{
        override fun release() {


        }

        override fun receiveDetections(detections: Detector.Detections<Barcode>) {
            if(detections != null && detections.detectedItems.isNotEmpty()){
                val qrCodes: SparseArray<Barcode> = detections.detectedItems
                val code = qrCodes.valueAt(0)
                textScanResult.text = code.displayValue
            }else{
                textScanResult.text = "Nessun codice trovato"
            }

        }
    }


}