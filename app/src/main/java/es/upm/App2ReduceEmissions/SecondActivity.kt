package es.upm.App2ReduceEmissions

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.helloworld.R
import java.io.IOException


class SecondActivity : AppCompatActivity() {
    private val TAG = "btaSecondActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        val bundle = intent.getBundleExtra("locationBundle")
        val location: Location? = bundle?.getParcelable("location")

        if(location != null){
            Log.i(TAG, "onCreate: Location["+location.altitude+"]["+location.latitude+"]["+location.longitude+"][")
        }

        Log.d(TAG, "onCreate: The activity is being created.")
        val buttonMain: Button = findViewById(R.id.secondViewMainButton)
        buttonMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        Log.d(TAG, "onCreate: The activity is being created.")
        val buttonNext: Button = findViewById(R.id.secondViewThirdButton)
        buttonNext.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Display the file contents
        val tvFileContents: TextView = findViewById(R.id.tvFileContents)
        tvFileContents.text = readFileContents()
    }

    private fun readFileContents(): String {
        val fileName = "gps_coordinates.csv"
        return try {
            // Open the file from internal storage
            openFileInput(fileName).bufferedReader().useLines { lines ->
                lines.fold("") { some, text ->
                    "$some\n$text"
                }
            }
        } catch (e: IOException) {
            "Error reading file: ${e.message}"
        }
    }

}