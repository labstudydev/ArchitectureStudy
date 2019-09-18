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

        val user = firebaseAuth.currentUser
//        val email = intent.getStringExtra(LoginActivity.EMAIL)
//        val name = intent.getStringExtra(LoginActivity.NAME)
//        val uid = intent.getStringExtra(LoginActivity.UID)

        u_email.setText(user?.email)
        u_name.setText(user?.displayName)
        u_uid.setText(user?.uid)

        modify_button.setOnClickListener{
            val ModifyIntent = Intent(this, ModifyActivity::class.java)
            startActivity(ModifyIntent)
        }

        delete_button.setOnClickListener{
            val user = firebaseAuth.currentUser
            user?.delete()
            Toast.makeText(this@MypageActivity, "탈퇴됐네용", Toast.LENGTH_LONG).show()

        }

        logout_button.setOnClickListener {
            firebaseAuth.signOut()
            finish()
        }
    }

    companion object {
        const val TAG = "MypageActivity"
    }
}