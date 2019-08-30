package user.manage.fbmember

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.info.*
import kotlinx.android.synthetic.main.login.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.modify.*


class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info)

        val accountManager = AccountManager.getInstance()

        val uidIntent = getIntent()
        val infoUID = uidIntent.getStringExtra("UID")
        info_UID.setText(infoUID)

        val emailIntent = getIntent()
        val infoEmail = emailIntent.getStringExtra("Email")
        info_email.setText(infoEmail)

        val nameIntent = getIntent()
        val infoName = nameIntent.getStringExtra("Name")
        info_name.setText(infoName)

        modifyBtn.setOnClickListener{
            val intent = Intent(applicationContext, ModifyActivity::class.java)
            startActivity(intent)
        }

        delBtn.setOnClickListener{
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

        logoutBtn.setOnClickListener{
            accountManager.logout()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}