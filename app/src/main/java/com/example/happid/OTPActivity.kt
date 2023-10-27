package com.example.happid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.happid.databinding.ActivityLoginBinding
import com.example.happid.databinding.ActivityOtpactivityBinding

class OTPActivity : AppCompatActivity() {

    private lateinit var otpBinding: ActivityOtpactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        otpBinding = ActivityOtpactivityBinding.inflate(layoutInflater)
        val view: View = otpBinding.root
        setContentView(view)

        val phoneNo: String = intent.getStringExtra("phoneNo").toString()
        val otp: String = intent.getStringExtra("otp").toString()

        otpBinding.txPhoneNumber.text = phoneNo

        otpBinding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        otpBinding.editPhone.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        otpBinding.llOtpSubmit.setOnClickListener {
            val inputOtp = otpBinding.pinview.text.toString()
            if (inputOtp == otp) {
                val intent = Intent(this, CreateProfileActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "InCorrect OTP!", Toast.LENGTH_SHORT).show()
            }
        }
 
    }
}