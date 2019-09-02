package user.manage.fbmember

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_modify.*

class ModifyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify)

        val user = FirebaseAuth.getInstance().currentUser
        modify_id.setText(user?.email)

        val accountManager = AccountManager.getInstance()

        val name = intent.getStringExtra("Name")

        modify_name.setText(name)

        modify_inform.setOnClickListener {
            accountManager.updateUser(
                modify_pw.text.toString(),
                modify_name.text.toString(),

                object : AccountManager.CallBack {
                    override fun onSuccess(user: FirebaseUser?) {
                        Log.d(TAG, "Modify : ${user?.email}")
                        Log.d(TAG, "Modify : ${user?.displayName}")
                        setResult(0)
                        finish()
                    }

                    override fun onFailure(message: String) {
                        Log.d(TAG, message)
                    }
                })

        }
        modify_back.setOnClickListener {
            val nextIntent = Intent(this, MainActivity::class.java)
            startActivity(nextIntent)
            finish()
        }
        modify_exit.setOnClickListener {
            finish()

        }


    }

    companion object {
        private const val TAG = "ModifyActivity"
    }


}