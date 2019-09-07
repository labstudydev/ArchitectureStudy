package user.manage.fbmember
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MypageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        val email = intent.getStringExtra(LoginActivity.EMAIL)
        val name = intent.getStringExtra(LoginActivity.NAME)
        val uid = intent.getStringExtra(LoginActivity.UID)

        Log.d(TAG, email)
        Log.d(TAG, name)
        Log.d(TAG, uid)
    }

    companion object{
        const val TAG = "MypageActivity"
    }
}