package com.example.happid

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import com.example.happid.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.llOtpButton.setOnClickListener {
            val countryCode: String = binding.countryCode.selectedCountryCode
            val phoneNo = binding.etPhoneNumber.text.toString()
            if (phoneNo.length < 10){
                Toast.makeText(this, "Invalid Phone Number", Toast.LENGTH_SHORT).show()
            }else{
                val startTwoDigits = phoneNo.substring(0, 2)
                val endTwoDigits = phoneNo.substring(phoneNo.length - 2)
                otp(startTwoDigits, endTwoDigits, phoneNo, countryCode)
            }
        }

    }

    private fun otp(startTwoDigits: String, endTwoDigits: String, phoneNo: String, countryCode: String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.otp_dialog)
        dialog.setCancelable(true)

        val otpText = dialog.findViewById<TextView>(R.id.txOTP)
        val otp = startTwoDigits + endTwoDigits
        otpText.text = otp

        dialog.show()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, OTPActivity::class.java)
            intent.putExtra("phoneNo", "+$countryCode $phoneNo")
            intent.putExtra("otp", otp)
            startActivity(intent)
            dialog.dismiss()
        }, 2000)

    }
}