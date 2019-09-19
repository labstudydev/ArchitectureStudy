package user.manage.fbmember

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_mypage.*

class MypageActivity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        val accountManager = AccountManager.getInstance()
        val user = firebaseAuth.currentUser

        u_email.setText(user?.email)
        u_name.setText(user?.displayName)
        u_uid.setText(user?.uid)

        modify_button.setOnClickListener{
            val modifyIntent = Intent(this, ModifyActivity::class.java)
            startActivity(modifyIntent)
            finish()
        }

        delete_button.setOnClickListener{
            user?.delete()
            Toast.makeText(this@MypageActivity, "탈퇴됐네용", Toast.LENGTH_LONG).show()
            val intent = Intent(this@MypageActivity,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        logout_button.setOnClickListener {
            val intent = Intent(this@MypageActivity,LoginActivity::class.java)
            accountManager.logout()
            startActivity(intent)
            finish()
        }
    }

    companion object {
        const val TAG = "MypageActivity"
    }
}