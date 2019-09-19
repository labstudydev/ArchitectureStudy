package user.manage.fbmember

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_modify.*
import android.content.Intent
import android.util.Log
import com.google.firebase.auth.FirebaseUser


class ModifyActivity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify)

        val accountManager = AccountManager.getInstance()
        val user = firebaseAuth.currentUser

        modify_email.setText(user?.email)
        modify_name.setText(user?.displayName)

        ok_button.setOnClickListener {
            accountManager.updateUser(
                "${modify_password.text}",
                "${modify_name.text}",
                object : AccountManager.CallBack {

                    override fun onSuccess(user: FirebaseUser?) {
                        val intent = Intent(this@ModifyActivity, MypageActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                    override fun onFailure(message: String) {
                        Log.d("activity_modify", message)
                    }
                })
        }

        back_button.setOnClickListener {
            val intent = Intent(this@ModifyActivity, MypageActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        const val TAG = "ModifyActivity"
    }
}