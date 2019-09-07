package user.manage.fbmember

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val accountManager = AccountManager.getInstance()

        btnlogin.setOnClickListener{
            val intent = Intent(this, MypageActivity::class.java)

            val email = "${email.text}"
            val password = "${L_password.text}"

            if(email.isNotEmpty() && password.isNotEmpty()){
                accountManager.loginUser(email, password , object : AccountManager.CallBack{
                    override fun onSuccess(user: FirebaseUser?) {
                        Log.d(TAG, "login : ${user?.email}")
                        Log.d(TAG, "login : ${user?.displayName}")
                        Log.d(TAG, "login : ${user?.uid}")

                        intent.putExtra(EMAIL, user?.email)
                        intent.putExtra(NAME, user?.displayName)
                        intent.putExtra(UID, user?.uid)

                        startActivity(intent)
                        finish()
                    }
                    override fun onFailure(message: String) {
                        Log.d(TAG, message)
                    }
                })//login finish*/
            }
        }

        btnaccount.setOnClickListener{
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    companion object{
        private const val TAG = "LoginActivity"

        const val EMAIL = "EMAIL"
        const val NAME = "NAME"
        const val UID = "UID"
    }
}