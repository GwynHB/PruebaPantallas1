package cl.gwyn.pruebapantallas1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val screenSplash=installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Thread.sleep(1000)

        //screenSplash.setKeepOnScreenCondition{true}

        val btn1: Button = findViewById(R.id.button2)
        btn1.setOnClickListener{
            val intent: Intent= Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

    }

}