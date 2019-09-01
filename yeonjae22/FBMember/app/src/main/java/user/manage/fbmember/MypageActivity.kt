package user.manage.fbmember

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_mypage.*


class MypageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        val accountManager = AccountManager.getInstance()
        val user = FirebaseAuth.getInstance().currentUser
        email.text = user?.email
        name.text = user?.displayName
        uid.text = user?.uid
        Log.d("TAG", "update : ${user?.uid}")

        logout_btn.setOnClickListener {
            accountManager.logout()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        update_btn.setOnClickListener {
            val intent = Intent(this, ModifyActivity::class.java)
            startActivity(intent)
        }
        delete_btn.setOnClickListener {
            accountManager.deleteUser(object : AccountManager.CallBack {
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