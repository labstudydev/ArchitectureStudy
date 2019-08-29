package user.manage.fbmember

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
        val accountManager = AccountManager.getInstance()

        registerBtn.setOnClickListener {
            accountManager.createUser(joinEmail.text.toString(), joinPassword.text.toString(), joinName.text.toString(), object : AccountManager.CallBack {
                override fun onSuccess(user: FirebaseUser?) {
                    val intent = Intent(getApplicationContext(), MainActivity::class.java)
                    startActivity(intent)
                }
                override fun onFailure(message: String) {
                    Log.d("tag",message)
                }
            })
        }
    }
}
