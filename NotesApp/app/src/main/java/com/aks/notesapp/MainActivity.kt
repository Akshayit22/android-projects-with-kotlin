package com.aks.notesapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.aks.notesapp.ui.theme.NotesAppTheme

class MainActivity : FragmentActivity() {

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (authViewModel.isAuthenticated) {
                        Navigation()
                    } else {
                        LockScreen(onUnlockClick = ::showBiometricPrompt)
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        authViewModel.checkTimeoutAndLock()
        if (!authViewModel.isAuthenticated) {
            showBiometricPrompt()
        }
    }

    override fun onStop() {
        super.onStop()
        if (!isChangingConfigurations) {
            authViewModel.onAppBackgrounded()
        }
    }

    // @SuppressLint — BiometricManager.Authenticators constants are annotated @RequiresApi(30)
    // but the AndroidX biometric library handles backward compat on API 23+ internally.
    @SuppressLint("WrongConstant")
    private fun showBiometricPrompt() {
        val executor = ContextCompat.getMainExecutor(this)

        val callback = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                authViewModel.onAuthenticated()
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                when (errorCode) {
                    // No PIN / pattern / password configured on the device
                    BiometricPrompt.ERROR_NO_DEVICE_CREDENTIAL -> {
                        Toast.makeText(
                            this@MainActivity,
                            "No screen lock set up. Go to Settings → Security to add one.",
                            Toast.LENGTH_LONG
                        ).show()
                        authViewModel.onAuthenticated()
                    }
                    // Hardware missing or unavailable — skip auth
                    BiometricPrompt.ERROR_HW_NOT_PRESENT,
                    BiometricPrompt.ERROR_HW_UNAVAILABLE -> {
                        authViewModel.onAuthenticated()
                    }
                    // User cancelled or timed out — stay on lock screen so they can retry
                    else -> { /* lock screen stays, retry button available */ }
                }
            }

            override fun onAuthenticationFailed() {
                // Wrong fingerprint — BiometricPrompt handles retries automatically
            }
        }

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Unlock Notes")
            .setSubtitle("Use fingerprint, face, or device PIN / pattern")
            .setAllowedAuthenticators(
                androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG or
                androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK or
                androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
            )
            .build()

        BiometricPrompt(this, executor, callback).authenticate(promptInfo)
    }
}
