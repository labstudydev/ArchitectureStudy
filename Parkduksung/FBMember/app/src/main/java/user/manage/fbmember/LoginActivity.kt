package user.manage.fbmember

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(!isActivate){
            FirebaseAuth.getInstance().signOut()
            isActivate = true
        }

        val accountManager = AccountManager.getInstance()

        Login.setOnClickListener{

            val nextIntent = Intent(this, MainActivity::class.java)

            accountManager.loginUser(User_id.text.toString(), User_pw.text.toString(), object : AccountManager.CallBack {
                override fun onSuccess(user: FirebaseUser?) {
                    Log.d(TAG, "login : ${user?.email}")
                    Log.d(TAG, "login : ${user?.displayName}")
                    startActivity(nextIntent)
                    finish()
                }
                override fun onFailure(message: String) {
                    Log.d(TAG, message)
                }
            })
        }

        Register.setOnClickListener{
            val nextIntent  = Intent(this, RegisterActivity::class.java)
            startActivity(nextIntent)
            finish()
        }

        Exit.setOnClickListener {
            finish()
        }


    }

    companion object{
        private const val TAG = "LoginActivity"
        private var isActivate = false
    }

}