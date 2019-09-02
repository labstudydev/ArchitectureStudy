package user.manage.fbmember

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val accountManager = AccountManager.getInstance()

//        accountManager.createUser(joinEmail.text.toString(), joinPassword.text.toString(), joinName.text.toString(), object : AccountManager.CallBack {
//            override fun onSuccess(user: FirebaseUser?) {
//                Log.d(TAG, "create : ${user?.email}")
//                Log.d(TAG, "create : ${user?.displayName}")
//                Log.d(TAG, "create : ${user?.uid}")
//                accountManager.loginUser(edtEmail.text.toString(), edtPassword.text.toString(), object : AccountManager.CallBack{
//                    override fun onSuccess(user: FirebaseUser?) {
//                        Log.d(TAG, "login : ${user?.email}")
//                        Log.d(TAG, "login : ${user?.displayName}")
//                        Log.d(TAG, "login : ${user?.uid}")
//
//                        accountManager.updateUser(modifyPassword.text.toString(), modifyName.text.toString(), object : AccountManager.CallBack{
//                            override fun onSuccess(user: FirebaseUser?) {
//                                Log.d(TAG, "update : ${user?.email}")
//                                Log.d(TAG, "update : ${user?.displayName}")
//                                Log.d(TAG, "update : ${user?.uid}")
//
//                                accountManager.deleteUser(object : AccountManager.CallBack{
//                                    override fun onSuccess(user: FirebaseUser?) {
//                                        Log.d(TAG, "delete success")
//                                    }
//
//                                    override fun onFailure(message: String) {
//                                        Log.d(TAG, "delete failure")
//                                    }
//                                })
//                            }
//
//                            override fun onFailure(message: String) {
//                                Log.d(TAG, "update : $message")
//                            }
//                        })
//                    }
//                    override fun onFailure(message: String) {
//                        Log.d(TAG, message)
//                    }
//                })
//            }
//
//            override fun onFailure(message: String) {
//                Log.d(TAG, message)
//            }
//        })

        login.setOnClickListener {
            accountManager.loginUser(
                "${edt_email.text}",
                "${edt_password.text}",
                object : AccountManager.CallBack {
                    override fun onSuccess(user: FirebaseUser?) {
                        val intent = Intent(getApplicationContext(), MypageActivity::class.java)
                        startActivity(intent)
                    }

                    override fun onFailure(message: String) {
                        Log.d("tag", message)
                    }
                })
        }
        join.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
