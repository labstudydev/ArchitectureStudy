package user.manage.fbmember

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_info.*
import android.util.Log
import com.google.firebase.auth.FirebaseUser
import user.manage.fbmember.companion.email
import user.manage.fbmember.companion.name
import user.manage.fbmember.companion.uid


class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val accountManager = AccountManager.getInstance()

        val infoUID = intent.getStringExtra(uid)
        info_uid.text = infoUID

        val infoEmail = intent.getStringExtra(email)
        info_email.text = infoEmail

        val infoName = intent.getStringExtra(name)
        info_name.text = infoName

        modify_btn.setOnClickListener{
            val intent = Intent(applicationContext, ModifyActivity::class.java)
            startActivity(intent)
        }

        del_btn.setOnClickListener{
            accountManager.deleteUser(object : AccountManager.CallBack {
                override fun onSuccess(user: FirebaseUser?) {
                    Log.d("delete", "delete success")

                    val intent = Intent(applicationContext, LoginActivity::class.java)
                    startActivity(intent)
                }
                override fun onFailure(message: String) {
                    Log.d("delete", message)
                }
            })
        }

        logout_btn.setOnClickListener{
            accountManager.logout()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}