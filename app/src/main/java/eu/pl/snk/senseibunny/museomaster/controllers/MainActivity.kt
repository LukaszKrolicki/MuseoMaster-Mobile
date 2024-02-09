package eu.pl.snk.senseibunny.museomaster.controllers

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.security.ProviderInstaller

import eu.pl.snk.senseibunny.museomaster.R
import eu.pl.snk.senseibunny.museomaster.controllers.AdminControllers.AdminActivity
import eu.pl.snk.senseibunny.museomaster.controllers.CuratorControllers.CuratorActivity
import eu.pl.snk.senseibunny.museomaster.databinding.ActivityAdminBinding
import eu.pl.snk.senseibunny.museomaster.databinding.ActivityMainBinding
import eu.pl.snk.senseibunny.museomaster.models.DataBaseDriver
import java.security.NoSuchAlgorithmException
import javax.net.ssl.SSLContext

class MainActivity : AppCompatActivity() {
    private lateinit var dataBaseDriver: DataBaseDriver
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try {
            ProviderInstaller.installIfNeeded(getApplicationContext())

            SSLContext.getInstance("TLSv1.2").apply {
                init(null, null, null)
                createSSLEngine()
            }

            println("start")
        } catch (e: Exception) {
            e.printStackTrace()
        }

        Thread {
            try {
                System.out.println("koniec")
                dataBaseDriver = DataBaseDriver(this);
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }.start()

        System.out.println("xd")

        binding.loginButton.setOnClickListener {
            val intent = Intent(this, CuratorActivity::class.java)
            startActivity(intent)
        }
    }
}

