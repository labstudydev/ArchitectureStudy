package user.manage.fbmember

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import user.manage.fbmember.companion.email
import user.manage.fbmember.companion.name
import user.manage.fbmember.companion.uid

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val accountManager = AccountManager.getInstance()

        login_btn.setOnClickListener{
            accountManager.loginUser("${login_email.text}", "${login_pw.text}", object : AccountManager.CallBack {
                override fun onSuccess(user: FirebaseUser?) {
                    Log.d("activity_login",  "로그인")

                    val intent = Intent(getApplicationContext(), InfoActivity::class.java)
                    intent.putExtra(uid, "${user?.uid}")
                    intent.putExtra(email, "${user?.email}")
                    intent.putExtra(name, "${user?.displayName}")
                    startActivity(intent)
                }
                override fun onFailure(message: String) {
                    Log.d("activity_login",  "message")
                }
            })
        }

        register_btn.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}

object companion {
    val uid = "UID"
    val email = "Email"
    val name = "Name"
}