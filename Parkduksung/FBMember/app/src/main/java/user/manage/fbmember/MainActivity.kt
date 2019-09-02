package user.manage.fbmember

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = FirebaseAuth.getInstance().currentUser

        val useremail = user?.email
        val useruid = user?.uid
        val userdisplayName = user?.displayName

        inform_id.setText(useremail)
        inform_uid.setText(useruid)
        inform_name.setText(userdisplayName)

        main_modify.setOnClickListener {
            val nextIntent = Intent(this, ModifyActivity::class.java)
            nextIntent.putExtra("Name", userdisplayName)
            startActivityForResult(nextIntent, REQUEST_CODE)
        }

        main_back.setOnClickListener {
            val nextIntent = Intent(this, LoginActivity::class.java)
            startActivity(nextIntent)
            finish()
        }
        main_exit.setOnClickListener {
            finish()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE) {
            when (resultCode) {
                RESULT_CODE -> inform_name.setText(FirebaseAuth.getInstance().currentUser?.displayName)
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
        const val REQUEST_CODE = 1
        const val RESULT_CODE = 0


    }
}
