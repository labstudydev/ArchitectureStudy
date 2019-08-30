package user.manage.fbmember

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.modify.*

class ModifyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modify)

        val accountManager = AccountManager.getInstance()

        val test = FirebaseAuth.getInstance().currentUser

        reviseBtn.setOnClickListener{

            accountManager.updateUser(modify_PW.text.toString(), modify_Name.text.toString(), object : AccountManager.CallBack{
                override fun onSuccess(user: FirebaseUser?) {

                    Log.d("modify",  "수정됨")

                    val intent = Intent(applicationContext, InfoActivity::class.java)
                    startActivity(intent)
                }
                override fun onFailure(message: String) {
                    Log.d("modify", message)
                }
            })
        }

    }
}