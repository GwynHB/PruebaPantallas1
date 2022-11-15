package cl.gwyn.pruebapantallas1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
enum class  ProviderType{
    BASIC,
    GOOGLE
}

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        /// boton comprar
        val btn1: Button = findViewById(R.id.Comprar)
        btn1.setOnClickListener{
            val intent: Intent = Intent(this, Comprar::class.java)
            startActivity(intent)
        }

        ///boton Venta

        val btn2: Button = findViewById(R.id.venta)
        btn2.setOnClickListener{
            val intent: Intent= Intent(this, Venta::class.java)
            startActivity(intent)
        }
         /// Boton Compras
        val btn3: Button = findViewById(R.id.Compras)
        btn3.setOnClickListener{
            val intent: Intent= Intent(this, Compras::class.java)
            startActivity(intent)
        }
        val btn4: Button = findViewById(R.id.MAPA)
        btn4.setOnClickListener{
            val intent: Intent= Intent(this, Mapa::class.java)
            startActivity(intent)
        }
        //setup


        val bundle=  intent.extras
        val email =bundle?.getString("email")
        setup(email ?: "")
        //Guardado de datos
        val prefs :SharedPreferences.Editor =getSharedPreferences(getString(R.string.prefs_file),Context.MODE_PRIVATE).edit()
        prefs.putString("email",email)
        prefs.apply()
    }

    private fun setup(email:String){
        title ="Inicio"
        val txview: TextView = findViewById(R.id.emailtexview)
        txview.text = email
        val btncer : Button =findViewById(R.id.buttoncer)
        btncer.setOnClickListener {
// con esto al cerrar sesion no la iniciara automaticamente
            val prefs :SharedPreferences.Editor =getSharedPreferences(getString(R.string.prefs_file),Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }



    }

}