package com.example.shopsavvycommvp.ui.splash.view

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Base64
import android.util.Log
import android.widget.ProgressBar
import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.ui.base.view.BaseActivity
import com.example.shopsavvycommvp.ui.login.view.LoginActivity
import com.example.shopsavvycommvp.ui.main.activities.view.MainActivity

import com.github.ybq.android.spinkit.style.CubeGrid
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_splash_screen.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import com.google.firebase.auth.FirebaseUser


class SplashActivity : BaseActivity(), SplashMVPView {
    override val layoutId: Int
        get() = R.layout.activity_splash_screen


    private val SPLASH_TIME_OUT: Long = 5000 // 3 sec
    private lateinit var mAuth: FirebaseAuth
    private  var mUser: FirebaseUser?=null
    override fun setUp() {
        setProgressbar()
        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth.currentUser
        Handler().postDelayed({
            if (mUser != null && mUser?.getDisplayName() != null) {
                openMainActivity()
            } else {
                openLoginActivity()
            }
        }, SPLASH_TIME_OUT)
    }

    private fun setProgressbar() {
        val progressBar = progess_splash_screen as ProgressBar
        val doubleBounce = CubeGrid()
        progressBar.indeterminateDrawable = doubleBounce

    }

    private fun openMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun openLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
    private fun getKeyHash(){
        try {
            val info = packageManager.getPackageInfo(
                "com.example.shopsavvycommvp",
                PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {

        } catch (e: NoSuchAlgorithmException) {

        }
    }

}
