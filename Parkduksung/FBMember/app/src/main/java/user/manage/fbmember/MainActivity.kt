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

        var user_email = user?.email
        var user_uid = user?.uid
        var user_displayName = user?.displayName

        Inform_id.setText(user_email)
        Inform_uid.setText(user_uid)
        Inform_name.setText(user_displayName)

        Main_modify.setOnClickListener {
            val nextIntent = Intent(this, ModifyActivity::class.java)
            nextIntent.putExtra("Name", user_displayName)
            startActivityForResult(nextIntent, 1)
        }

        Main_back.setOnClickListener {
            val nextIntent = Intent(this, LoginActivity::class.java)
            startActivity(nextIntent)
            finish()
        }
        Main_exit.setOnClickListener {
            finish()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            when (resultCode) {
                0 -> Inform_name.setText(FirebaseAuth.getInstance().currentUser?.displayName)
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
