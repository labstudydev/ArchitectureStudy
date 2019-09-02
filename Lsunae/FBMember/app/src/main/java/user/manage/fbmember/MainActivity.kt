package user.manage.fbmember

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val accountManager = AccountManager.getInstance()

        accountManager.createUser("wos94@naver.com", "784512", "원우석", object : AccountManager.CallBack {
            override fun onSuccess(user: FirebaseUser?) {
                Log.d(TAG, "create : ${user?.email}")
                Log.d(TAG, "create : ${user?.displayName}")
                Log.d(TAG, "create : ${user?.uid}")
                accountManager.loginUser("wos94@naver.com", "784512", object : AccountManager.CallBack{
                    override fun onSuccess(user: FirebaseUser?) {
                        Log.d(TAG, "activity_login : ${user?.email}")
                        Log.d(TAG, "activity_login : ${user?.displayName}")
                        Log.d(TAG, "activity_login : ${user?.uid}")

                        accountManager.updateUser("784512", "우석", object : AccountManager.CallBack{
                            override fun onSuccess(user: FirebaseUser?) {
                                Log.d(TAG, "update : ${user?.email}")
                                Log.d(TAG, "update : ${user?.displayName}")
                                Log.d(TAG, "update : ${user?.uid}")

                                accountManager.deleteUser(object : AccountManager.CallBack{
                                    override fun onSuccess(user: FirebaseUser?) {
                                        Log.d(TAG, "delete success")
                                    }

                                    override fun onFailure(message: String) {
                                        Log.d(TAG, "delete failure")
                                    }
                                })
                            }

                            override fun onFailure(message: String) {
                                Log.d(TAG, "update : $message")
                            }
                        })
                    }
                    override fun onFailure(message: String) {
                        Log.d(TAG, message)
                    }
                })
            }

            override fun onFailure(message: String) {
                Log.d(TAG, message)
            }
        })




    }

    companion object{
        private const val TAG = "MainActivity"
    }
}
