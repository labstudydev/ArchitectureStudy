package user.manage.fbmember

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_modify.*

class ModifyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify)

        val accountManager = AccountManager.getInstance()

        modify_btn.setOnClickListener {
            accountManager.updateUser(
                "${modify_password.text}",
                "${modify_name.text}",
                object : AccountManager.CallBack {
                    override fun onSuccess(user: FirebaseUser?) {
                        val intent = Intent(getApplicationContext(), MypageActivity::class.java)
                        startActivity(intent)
                    }

                    override fun onFailure(message: String) {
                        Log.d("tag", message)
                    }
                })
        }
    }
}