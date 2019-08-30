package user.manage.fbmember

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.register.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val accountManager = AccountManager.getInstance()

        loginBtn.setOnClickListener{
            accountManager.loginUser(login_email.text.toString(), login_PW.text.toString(), object : AccountManager.CallBack {
                override fun onSuccess(user: FirebaseUser?) {
                    Log.d("login",  "로그인")

                    val intent = Intent(getApplicationContext(), InfoActivity::class.java)
                    intent.putExtra("UID", "${user?.uid}")
                    intent.putExtra("Email", "${user?.email}")
                    intent.putExtra("Name", "${user?.displayName}")
                    startActivity(intent)
                }
                override fun onFailure(message: String) {
                    Log.d("login",  "message")
                }
            })
        }

        registerBtn.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}