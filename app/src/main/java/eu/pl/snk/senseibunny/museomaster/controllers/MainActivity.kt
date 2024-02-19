package eu.pl.snk.senseibunny.museomaster.controllers

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.security.ProviderInstaller

import eu.pl.snk.senseibunny.museomaster.R
import eu.pl.snk.senseibunny.museomaster.controllers.AdminControllers.AdminActivity
import eu.pl.snk.senseibunny.museomaster.controllers.CuratorControllers.CuratorActivity
import eu.pl.snk.senseibunny.museomaster.controllers.MuseumClientControllers.MuseumClientActivity
import eu.pl.snk.senseibunny.museomaster.controllers.NormalWorkerControllers.NormalWorkerActivity
import eu.pl.snk.senseibunny.museomaster.controllers.PermissionTechWorker.PermissionTechnicalWorkerActivity
import eu.pl.snk.senseibunny.museomaster.controllers.PermissionWorkerControllers.PermissionWorkerActivity
import eu.pl.snk.senseibunny.museomaster.controllers.TechnicalWorkerControllers.TechnicalWorkerActivity
import eu.pl.snk.senseibunny.museomaster.databinding.ActivityAdminBinding
import eu.pl.snk.senseibunny.museomaster.databinding.ActivityMainBinding
import eu.pl.snk.senseibunny.museomaster.databinding.CustomReportPopupBinding
import eu.pl.snk.senseibunny.museomaster.databinding.UserCreatePopupBinding
import eu.pl.snk.senseibunny.museomaster.models.DataBaseDriver
import eu.pl.snk.senseibunny.museomaster.models.Model
import eu.pl.snk.senseibunny.museomaster.models.Report
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.security.NoSuchAlgorithmException
import java.security.Permission
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

        //Create Model on different thread
        Thread {
            try {
                Model.getInstance(this)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }.start()


        binding.loginButton.setOnClickListener {

            val intent = Intent(this, PermissionTechnicalWorkerActivity::class.java)

            startActivity(intent)
        }

        binding.CreateButton.setOnClickListener{
            showPopup()
        }
    }

    fun showPopup() {
        val popupBinding: UserCreatePopupBinding = UserCreatePopupBinding.inflate(LayoutInflater.from(binding.root.context))
        val popupView: View = popupBinding.root
        val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)


        // Set the focusable property to true
        popupWindow.isFocusable = true

        // Set an OnTouchListener to consume touch events
        popupView.setOnTouchListener { _, _ -> true }


        popupBinding.back.setOnClickListener {
            popupWindow.dismiss()
        }

        popupBinding.CreateUserbtn.setOnClickListener{
            if(popupBinding.pass1.text.isBlank() || popupBinding.pass2.text.isBlank() || popupBinding.username.text.isBlank()){
                popupBinding.error.setText("Blank sections")
                popupBinding.error.setTextColor(Color.RED)
            }
            else if(popupBinding.pass1.text.toString() != popupBinding.pass2.text.toString()){

                popupBinding.error.setTextColor(Color.RED)
                popupBinding.error.setText("Passwords are different")
            }
            else{
                var username = popupBinding.username.text.toString()
                var password = popupBinding.pass1.text.toString()
                var flag=1;
                runBlocking {
                    withContext(Dispatchers.IO) {
                        flag = Model.getInstanceWC().dataBaseDriver.createNormalClient("-",password,username)
                    }
                }


                println(flag)
                if(flag==1){
                    popupBinding.error.setTextColor(Color.RED)
                    popupBinding.error.setText("Username already exists")
                }
                else{
                    popupWindow.dismiss()
                    Toast.makeText(this, "Account made succesfully", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Show the popup window in the center of the screen
        popupWindow.showAtLocation(binding.root, Gravity.CENTER, 0, 0)
    }
}

