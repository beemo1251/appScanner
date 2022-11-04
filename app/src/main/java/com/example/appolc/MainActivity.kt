package com.example.appolc

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appolc.databinding.ActivityMainBinding
import com.google.zxing.client.android.Intents
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var inputText: String
    private val almacenes = ArrayList<String>()
    var matriz = arrayOf(
        arrayOf("Codigo", "Serie", "Lote", "Cantidad")
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inputText = ""
        almacenes.add("item 1")
        almacenes.add("item 2")
        almacenes.add("item 3")
        almacenes.add("item 4")

        binding.tableList.removeAllViews()
        var registro = LayoutInflater.from(this).inflate(R.layout.table_row, null, false)
        val txt1=registro.findViewById<View>(R.id.textCodigo) as TextView
        val txt2=registro.findViewById<View>(R.id.textSerie) as TextView
        val txt3=registro.findViewById<View>(R.id.textLote) as TextView
        val txt4=registro.findViewById<View>(R.id.textCantidad) as TextView
        txt1.text=matriz[0][0]
        txt2.text=matriz[0][1]
        txt3.text=matriz[0][2]
        txt4.text=matriz[0][3]
        val boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD)
        txt1.setTypeface(boldTypeface)
        txt2.setTypeface(boldTypeface)
        txt3.setTypeface(boldTypeface)
        txt4.setTypeface(boldTypeface)
        binding.tableList.addView(registro)

        binding.txtSerie.setOnFocusChangeListener(OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                inputText = "serie"
            }
        })

        binding.txtCodigo.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                inputText = "codigo"
            }
        }

        binding.spinnerAlmacen.setAdapter(
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                almacenes
            )
        )

        val scanButton = findViewById<Button>(R.id.btnScanner)

        scanButton.setOnClickListener {
            initScanner()
        }

        binding.btnAgregar.setOnClickListener {
            enviarObj()
        }
    }

    private fun initScanner() {
        barcodeLauncher.launch(ScanOptions())
    }

    private val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents == null) {
            val originalIntent = result.originalIntent
            if (originalIntent == null) {
                Log.d("MainActivity", "Cancelled scan")
                Toast.makeText(this@MainActivity, "Cancelled", Toast.LENGTH_LONG).show()
            } else if (originalIntent.hasExtra(Intents.Scan.MISSING_CAMERA_PERMISSION)) {
                Log.d("MainActivity", "Cancelled scan due to missing camera permission")
                Toast.makeText(
                    this@MainActivity,
                    "Cancelled due to missing camera permission",
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            if (inputText == "serie") {
                binding.txtSerie.setText(result.contents)
            }
            if (inputText == "codigo") {
                binding.txtCodigo.setText(result.contents)
            }
            Log.d("MainActivity", "Scanned")
            Toast.makeText(
                this@MainActivity,
                "Scanned: " + result.contents,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun enviarObj()
    {
        var registro = LayoutInflater.from(this).inflate(R.layout.table_row, null, false)
        val txt1=registro.findViewById<View>(R.id.textCodigo) as TextView
        val txt2=registro.findViewById<View>(R.id.textSerie) as TextView
        val txt3=registro.findViewById<View>(R.id.textLote) as TextView
        val txt4=registro.findViewById<View>(R.id.textCantidad) as TextView
        txt1.text=binding.txtCodigo.text.toString()
        txt2.text=binding.txtSerie.text.toString()
        txt3.text=binding.txtLote.text.toString()
        txt4.text=binding.txtCantidad.text.toString()
        binding.tableList.addView(registro)

        binding.txtCodigo.setText("")
        binding.txtSerie.setText("")
        binding.txtLote.setText("")
        binding.txtCantidad.setText("")

        //val alm = binding.spinnerAlmacen.getSelectedItem()
        //Log.d("MainActivity", "Alm")
        //Toast.makeText(this@MainActivity, "Almacen:" + alm, Toast.LENGTH_LONG).show()
    }
}