package cl.gwyn.pruebapantallas1

import android.net.Uri.Builder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class Registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        //Setup
        setup()
    }

    private fun setup() {
        title = "Registro"

        val registrar: Button = findViewById(R.id.RegisButton)
        val texemail: EditText = findViewById(R.id.txemail)
        val texcon: EditText = findViewById(R.id.txcontra)
        registrar.setOnClickListener {//comprobamos que los campos no  se encuentren vacios
            if (texemail.text.isNotEmpty() && texcon.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    texemail.text.toString(),
                    texcon.text.toString()
                ).addOnCompleteListener {///alerta si se completo correctamente o no
                    if (it.isSuccessful) {
                    } else {
                      alert()
                    }
                }
            }

        }
    }
    private fun alert(){
        val builder =AlertDialog.Builder(This)
        builder.setTitle("Error")
        builder.setMessage("Se Producido un Error En el registro")
        builder.setPositiveButton("Acepptar",null)
        val dialog:AlertDialog = builder.create()
        dialog.slow()
    }

}