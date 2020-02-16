package com.yuvasai.baseproject.utils


import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.yuvasai.baseproject.R
import java.util.*

/**
 * Created by Chandrakanth on 1/22/2018.
 */

open class PermissionChecker : AppCompatActivity() {
    private var PERMISSIONS: Array<String>? = arrayOf()
    private var OPTIONAL_PERMISSIONS: Array<String>? = arrayOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = intent.extras

        when (data?.getInt("requestCode")) {
            0 -> {
                PERMISSIONS = arrayOf(
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.CAMERA
                )
            }
            FINGERPRINT_PERMISSION -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    PERMISSIONS = arrayOf(Manifest.permission.USE_BIOMETRIC)
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    PERMISSIONS = arrayOf(Manifest.permission.USE_FINGERPRINT)

                } else {
                    setResult(RESULT_REJECTED)
                    finish()
                }
            }
            1 -> {
                PERMISSIONS = arrayOf(Manifest.permission.CALL_PHONE)
            }
            CAMERA_PERMISSION -> {
                PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
            }

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PERMISSIONS?.isNotEmpty()!!) {
                requestUserPermission(true)
            } else {
                setResult(RESULT_REJECTED)
                finish()
            }
        } else {
            setResult(RESULT_OK)
            finish()
        }
    }

    private fun requestUserPermission(recall: Boolean) {
        if (recall) {
            val temp = ArrayList<String>()
            for (i in PERMISSIONS!!.indices) {
                if (!listOf(*OPTIONAL_PERMISSIONS!!).contains(PERMISSIONS!![i])) {
                    temp.add(PERMISSIONS!![i])
                }
            }
            PERMISSIONS = temp.toTypedArray()
        }
        ActivityCompat.requestPermissions(
            this,
            PERMISSIONS as Array<out String>,
            PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        state: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                var ungrantedMandatoryPermissions = ArrayList<String>()
                var ungrantedMandatoryPermissionsCount = 0

                for (i in state.indices) {
                    //continue if permission is not optional and is not granted
                    if (!listOf(*OPTIONAL_PERMISSIONS!!).contains(permissions[i]) && state[i] != PackageManager.PERMISSION_GRANTED) {
                        ungrantedMandatoryPermissionsCount++
                        ungrantedMandatoryPermissions.add(permissions[i])
                    }
                }
                if (ungrantedMandatoryPermissionsCount != 0) {
                    for (i in state.indices) {
                        if (ungrantedMandatoryPermissions.contains(permissions[i]) &&//mandatory permission
                            state[i] != PackageManager.PERMISSION_GRANTED &&//not granted
                            shouldShowRequestPermissionRationale(permissions[i])
                        ) {//user not checked never ask again
                            showMessageOKCancel(resources.getString(R.string.text_app_requires_mandatory_permissions),
                                DialogInterface.OnClickListener { _, _ -> requestUserPermission(true) },
                                DialogInterface.OnClickListener { _, _ ->
                                    setResult(RESULT_CANCELED_EXPLICIT)
                                    finish()
                                })
                            break
                        } else if (!ungrantedMandatoryPermissions.contains(permissions[i]) || //not a mandatory permission
                            state[i] == PackageManager.PERMISSION_GRANTED
                        ) {//permission granted
                            continue
                        } else {
                            Toast.makeText(
                                this,
                                resources.getString(R.string.text_go_to_setting),
                                Toast.LENGTH_LONG
                            ).show()
                            setResult(RESULT_REJECTED)
                            finish()
                            break
                        }
                    }
                } else {
                    setResult(RESULT_OK)
                    finish()
                }
            } else {
                setResult(RESULT_OK)
                finish()
            }
        }
    }

    private fun showMessageOKCancel(
        message: String,
        okListener: DialogInterface.OnClickListener,
        cancelListener: DialogInterface.OnClickListener
    ) {
        val builder = AlertDialog.Builder(this)
            .setMessage(message).setPositiveButton("OK", okListener)
            .setNegativeButton("Cancel", cancelListener)
        val alert = builder.create()
        alert.setCancelable(false)
        alert.show()
        val nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE)
        //Set negative button background
        nbutton.setTextColor(
            ContextCompat.getColor(
                this@PermissionChecker,
                R.color.colorPrimaryDark
            )
        )
        //Set negative button text color
        val pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE)
        //Set positive button text color
        pbutton.setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))

    }

    companion object {
        private const val PERMISSION_REQUEST_CODE = 200
        const val RESULT_REJECTED = 3
        const val RESULT_CANCELED_EXPLICIT = 4
        const val FINGERPRINT_PERMISSION = 6
        const val CAMERA_PERMISSION = 7


        fun createActivity(context: Context, requestCode: Int): Intent {
            val intent = Intent(context, PermissionChecker::class.java)
            intent.putExtra("requestCode", requestCode)
            return intent
        }
    }
}
