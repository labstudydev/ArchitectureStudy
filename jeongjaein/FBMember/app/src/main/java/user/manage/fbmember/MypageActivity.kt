package user.manage.fbmember

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_mypage.*

class MypageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        val email = intent.getStringExtra(LoginActivity.EMAIL)
        val name = intent.getStringExtra(LoginActivity.NAME)
        val uid = intent.getStringExtra(LoginActivity.UID)

        u_email.setText(email)
        u_name.setText(name)
        u_uid.setText(uid)

        modify_button.setOnClickListener{
            val ModifyIntent = Intent(this, ModifyActivity::class.java)

//            intent.putExtra(LoginActivity.EMAIL, user?.email)
//            intent.putExtra(LoginActivity.NAME, user?.displayName)
//            intent.putExtra(LoginActivity.UID, user?.uid)

            startActivity(ModifyIntent)
            finish()


        }

        delete_button.setOnClickListener{
            val user = FirebaseAuth.getInstance().currentUser
            user.delete()

            Toast.makeText(this@MypageActivity, "탈퇴됐네용", Toast.LENGTH_LONG).show()

        }

        logout_button.setOnClickListener {
            user.logout()
            finish()
        }
    }

    companion object {
        const val TAG = "MypageActivity"
    }
}