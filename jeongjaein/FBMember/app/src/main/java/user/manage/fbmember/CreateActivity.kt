package user.manage.fbmember

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        val accountManager = AccountManager.getInstance()

        btncreate.setOnClickListener {
            val intent = Intent(this@CreateActivity, LoginActivity::class.java)
            accountManager.createUser("${C_email.text}", "${C_password.text}", "${C_name.text}",
                object : AccountManager.CallBack {
                    override fun onSuccess(user: FirebaseUser?) {

                        startActivity(intent)
                        finish()

                    }

                    override fun onFailure(message: String) {
                        Log.d(TAG, message)
                    }
                })
        }
    }

    companion object {
        private const val TAG = "CreateActivity"
    }
}
