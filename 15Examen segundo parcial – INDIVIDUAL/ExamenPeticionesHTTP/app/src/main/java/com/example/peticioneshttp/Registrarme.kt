package com.example.peticioneshttp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_registrarme.*

class Registrarme : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarme)

        //Ocultar la barra de acciones
        val actionBar = supportActionBar
        actionBar?.hide()

        //Acultar la barra de progreso
        button3.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val url: String = "https://cursodemoviles.000webhostapp.com/agregar.php?usuario=${usuario.text}&contrasena=${contrasena.text}&nombre=${nombreCompleto.text}"
            //textView.text=url.toString()

            // Request a string response from the provided URL.
            val stringReq = StringRequest(
                    Request.Method.GET, url,
                    Response.Listener<String> { response ->
                        var strResp = response.toString()
                        if (strResp=="")
                        {
                            Alerta("Usuarios","No se pudo registrar!")
                        }
                        else
                        {
                            val dialogBuilder = AlertDialog.Builder(this)
                            // set message of alert dialog
                            dialogBuilder.setMessage(strResp)
                                    // if the dialog is cancelable
                                    .setCancelable(false)
                                    // positive button text and action
                                    .setPositiveButton("OK", DialogInterface.OnClickListener {
                                        dialog, id -> finish()
                                    })
                            // create dialog box
                            val alert = dialogBuilder.create()
                            // set title for alert dialog box
                            alert.setTitle("Registro de usuarios")
                            // show alert dialog
                            alert.show()
                        }
                    },
                    Response.ErrorListener {
                        Alerta("Red","Problemas con el Internet")
                    })
            queue.add(stringReq)
        }
    }
    fun Alerta(Titulo:String, Mensaje:String)
    {
        val dialogBuilder = AlertDialog.Builder(this)
        // set message of alert dialog
        dialogBuilder.setMessage(Mensaje)
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("OK", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
                })
        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle(Titulo)
        // show alert dialog
        alert.show()
    }
}