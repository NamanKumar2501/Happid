package com.example.happid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.happid.databinding.ActivityCreateProfileBinding
import com.example.happid.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.llButtonSubmit.setOnClickListener {
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val phone = binding.etPhone.text.toString()
            val postCode = binding.etPost.text.toString()

            if (firstName.isEmpty()) {
                binding.etFirstName.requestFocus()
                Toast.makeText(this, "Name is Mandatory.", Toast.LENGTH_SHORT).show()

            }  else if (lastName.isEmpty()) {
                binding.etLastName.requestFocus()
                Toast.makeText(this, "Last Name is Mandatory.", Toast.LENGTH_SHORT).show()
            }
            else if (phone.isEmpty()) {
                binding.etPhone.requestFocus()
                Toast.makeText(this, "Phone Number is Mandatory.", Toast.LENGTH_SHORT).show()
            }
            else if (postCode.isEmpty()) {
                binding.etPost.requestFocus()
                Toast.makeText(this, "Post code is Mandatory.", Toast.LENGTH_SHORT).show()
            }else{
                api(firstName, lastName, phone, postCode)
                binding.pbProgress.visibility = View.VISIBLE

            }
        }
    }

    private fun api(firstName: String, lastName: String, phone: String, postCode: String) {
        val user = UserModel(firstName, lastName, phone, postCode)
        RetrofitClient().myApi.createUser(user).enqueue(object : Callback<UserModel> {
            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if (response.isSuccessful) {
                    val createdUser = response.body()
                    binding.pbProgress.visibility = View.GONE
                    Toast.makeText(this@CreateProfileActivity, "User Successfully Created.", Toast.LENGTH_SHORT).show()
                } else {
                    binding.pbProgress.visibility = View.GONE
                    Toast.makeText(this@CreateProfileActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                binding.pbProgress.visibility = View.GONE
                Toast.makeText(this@CreateProfileActivity, t.toString(), Toast.LENGTH_SHORT).show()
            }
        })


    }
}