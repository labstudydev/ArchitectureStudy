package user.manage.fbmember

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest


class AccountManager private constructor() {
    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    interface CallBack {
        fun onSuccess(user: FirebaseUser?)
        fun onFailure(message: String)
    }

    fun createUser(email: String, password: String, userName: String, callback: CallBack) {

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                // 회원가입 성공
                val user = it.result?.user

                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(userName)
                    .build()
                user?.updateProfile(profileUpdates)
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            callback.onSuccess(firebaseAuth.currentUser)
                        } else {
                            callback.onFailure("${it.exception?.message}")
                        }
                    }

            } else {
                // 회원가입 실패
                callback.onFailure("${it.exception?.message}")
            }
        }
    }

    fun loginUser(email: String, password: String, callback: CallBack) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    // 로그인 성공
                    callback.onSuccess(it.result?.user)

                } else {
                    // 로그인 실패
                    callback.onFailure("${it.exception?.message}")
                }
            }
    }

    fun updateUser(password: String, userName: String, callback: CallBack) {
        val user = firebaseAuth.currentUser
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(userName)
            .build()
        user?.let {
            it.updateProfile(profileUpdates)
                .addOnCompleteListener { pfTask ->
                    if (pfTask.isSuccessful) {
                        user.updatePassword(password)
                            .addOnCompleteListener { pwTask ->
                                if (pwTask.isSuccessful) {
                                    callback.onSuccess(user)
                                } else {
                                    callback.onFailure("${pwTask.exception?.message}")
                                }
                            }
                    } else {
                        callback.onFailure("${pfTask.exception?.message}")
                    }
                }
        } ?: callback.onFailure("Not a login user")
    }

    fun deleteUser(callback: CallBack) {
        val user = firebaseAuth.currentUser
        user?.let {
            it.delete().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback.onSuccess(null)
                } else {
                    callback.onFailure("${task.exception?.message}")
                }
            }
        } ?: callback.onFailure("Not a login user")
    }

    fun logout(){
        firebaseAuth.signOut()
    }

    companion object {
        private const val TAG = "AccountManager"
        @Volatile
        private var instance: AccountManager? = null

        @JvmStatic
        fun getInstance(): AccountManager{
            FirebaseAuth.getInstance().signOut()
            return instance ?: synchronized(this) {
                instance ?: AccountManager().also {
                    instance = it
                }
            }
        }

    }
}