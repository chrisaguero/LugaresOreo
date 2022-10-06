package com.example.lugaresoreo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.lugares.databinding.ActivityMainBinding
import com.example.lugaresoreo.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.internal.NoOpContinuation.context
import kotlin.coroutines.jvm.internal.CompletedContinuation.context
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp( context: this)
        auth = Firebase.auth
//Netodo de login
        binding.btLogin.setOnClickListener(){ it: View!
                hacerlogin();
        }
//Metodo de registro
        binding.btRegister.setOnClickListener(){ it: View!
        hacerRegister();
        }

    }

    public fun actualiza(user: FirebaseUser?){
        if(user != null){
            val intent = Intent("")
        }
    }

    private fun hacerlogin(){
        var email = binding.etMail.text.toString();
        var clave = binding.etClave.text.toString();

        //se hace login


        auth.signInWithEmailAndPassword(email,clave).addOnCompleteListener(this) { tasks ->
            if (task.isSuccessful){
                Log.d(tag: "Autenticando", msg: "Autenticando")
                val user = auth.currentUser
                if (user != null) {
                    actualiza(user)
                }
            }else{
                Log.d("Autenticado", "Fallo")
                Toast.makeText(baseContext, "Fallo", Toast.LENGTH_LONG).show()
            }
        }

    }
}