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

        join_btn.setOnClickListener {
            accountManager.createUser("${edit_email.text}", "${edit_pW.text}", "${edit_name.text}", object : AccountManager.CallBack {
                override fun onSuccess(user: FirebaseUser?) {
                    Log.d("create",  "성공")

                    val intent = Intent(getApplicationContext(), LoginActivity::class.java)
                    startActivity(intent)
                }
                override fun onFailure(message: String) {
                    Log.d("create",  "message")
                }
            })
        }

    }
}