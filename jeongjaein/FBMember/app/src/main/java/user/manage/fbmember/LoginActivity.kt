package user.manage.fbmember

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val accountManager = AccountManager.getInstance()

        btnlogin.setOnClickListener{
            val mypageIntent = Intent(this, MypageActivity::class.java)

            val email = "${email.text}"
            val password = "${L_password.text}"

            if(email.isNotEmpty() && password.isNotEmpty()){
                accountManager.loginUser(email, password , object : AccountManager.CallBack{
                    override fun onSuccess(user: FirebaseUser?) {
                        startActivity(mypageIntent)
                        finish()
                    }
                    override fun onFailure(message: String) {
                        Log.d(TAG, message)
                        Toast.makeText(this@MypageActivity, "아이디나 비밀번호가 틀렸어용~", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }

        btnaccount.setOnClickListener{
            val createIntent = Intent(this, CreateActivity::class.java)
            startActivity(createIntent)
            finish()

        }
    }

    companion object{
        private const val TAG = "LoginActivity"
    }
}