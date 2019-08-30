package user.manage.fbmember

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        val accountManager = AccountManager.getInstance()

        joinBtn.setOnClickListener {
            accountManager.createUser(editEmail.text.toString(), editPW.text.toString(), editName.text.toString(), object : AccountManager.CallBack {
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