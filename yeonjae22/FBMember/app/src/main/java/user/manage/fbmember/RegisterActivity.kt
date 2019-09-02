package user.manage.fbmember

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val accountManager = AccountManager.getInstance()

        register_btn.setOnClickListener {
            accountManager.createUser(
                "${join_email.text}",
                "${join_password.text}",
                "${join_name.text}",
                object : AccountManager.CallBack {
                    override fun onSuccess(user: FirebaseUser?) {
                        val intent = Intent(getApplicationContext(), MainActivity::class.java)
                        startActivity(intent)
                    }

                    override fun onFailure(message: String) {
                        Log.d("tag", message)
                    }
                })
        }
    }
}
