package cl.gwyn.pruebapantallas1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private val Google_Sing_IN = 100
    override fun onCreate(savedInstanceState: Bundle?) {

        val screenSplash = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Thread.sleep(1000)

        //screenSplash.setKeepOnScreenCondition{true}

        //setup
        setup()
        session()
    }

    private fun setup() {
        title = "registro"
        val btnreg: Button = findViewById(R.id.RegisButton)
        val txemail: EditText = findViewById(R.id.txemail)
        val contraseña: EditText = findViewById(R.id.txcontra)
        val btnlogin: Button = findViewById(R.id.loginbutton)
        val btngoogle :Button =findViewById(R.id.Google)

        btnreg.setOnClickListener {
            if (txemail.text.isNotEmpty() && contraseña.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    txemail.text.toString(),
                    contraseña.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Semenu(it.result?.user?.email ?: "")

                    } else {
                        alert()
                    }
                }


            }

        }
        btngoogle.setOnClickListener {
            //autenticacion
           // val googlecf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id))
                //.requestEmail()
               // .build()
            //val googleClient = GoogleSignIn.getClient(this,googlecf )
           //StarActivityFoRresult(googleClient.signInIntent,Google_Sing_IN )


        }
        btnlogin.setOnClickListener {
            if (txemail.text.isNotEmpty() && contraseña.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    txemail.text.toString(),
                    contraseña.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Semenu(it.result?.user?.email ?: "")

                    } else {
                        alert1()
                    }
                }


            }

        }
    }
//alerta de registro
    private fun alert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se Producido un Error En el registro")
        builder.setPositiveButton("Acepptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun Semenu(email: String) {
        val menuintent = Intent(this, MainActivity2::class.java).apply {
            putExtra("email", email)
        }
        startActivity(menuintent)

    }
//alerta de error de inicio de session
    private fun alert1() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("usuario no registrado o contraseña incorrecta")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    //inicio de session
    private  fun session(){
        val prefs : SharedPreferences =getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email",null)
        if (email !=null){

            Semenu(email)
        }

    }
}

