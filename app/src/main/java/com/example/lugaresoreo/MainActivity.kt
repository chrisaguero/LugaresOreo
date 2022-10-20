package com.example.lugaresoreo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.lugaresoreo.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
//import kotlinx.coroutines.flow.internal.NoOpContinuation.context
import java.security.Principal
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)


        setContentView(binding.root)
        FirebaseApp.initializeApp(this)


        auth = Firebase.auth
//Netodo de login
        binding.btLogin.setOnClickListener(){
                hacelogin();
        }
//Metodo de registro
        binding.btRegister.setOnClickListener(){
        haceRegister();
        }

    }

    private fun haceRegister(){
        var clave = binding.etClave.text.toString()
        var email = binding.etMail.text.toString()
        //se hace login


        auth.createUserWithEmailAndPassword(email,clave).addOnCompleteListener(this){ task ->
            if (task.isSuccessful){
                Log.d("creando usuario", "registrado")

                val user = auth.currentUser
                    actualiza(user)

            }else{
                Log.d("creando usuario", "Fallo")
                Toast.makeText(baseContext, "Fallo", Toast.LENGTH_LONG).show()
                actualiza(null)
            }
        }


    }

    private fun actualiza(user: FirebaseUser?){
        if(user != null){
            val intent = Intent(this, Principal::class.java)
            startActivity(intent)
        }
    }

    public override fun onStart(){
        super.onStart()
        val usuario = auth.currentUser
        actualiza(usuario)
    }

    private fun hacelogin(){
        var email = binding.etMail.text.toString();
        var clave = binding.etClave.text.toString();

        //se hace login


        auth.signInWithEmailAndPassword(email,clave).addOnCompleteListener(this) { task ->
            if (task.isSuccessful){
                Log.d("Autenticando", "Autenticando")
                val user = auth.currentUser
                if (user != null) {
                    actualiza(user)
                }
            }else{
                Log.d("Autenticado", "Fallo")
                Toast.makeText(baseContext, "Fallo", Toast.LENGTH_LONG).show()
                actualiza(null )
            }
        }

    }
}