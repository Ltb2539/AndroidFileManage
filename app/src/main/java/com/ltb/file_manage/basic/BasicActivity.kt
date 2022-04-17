package com.ltb.file_manage.basic

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 *Time:2022/4/14   22:33
 *Author:林天宝
 *项目名称：AndroidFileManage
 */
abstract class BasicActivity<VB : ViewBinding> : AppCompatActivity() {
    protected abstract fun setBinding(): VB?
    protected abstract fun initView()

    //上下文
    var mContext: Context? = null
    var appContext: Context? = null
    var activity: BasicActivity<*>? = null

    //ViewBinding
    protected var binding: VB? = null

    //回调  权限回调和跳转回调
    private var launcher: ActivityResultLauncher<Intent?>? = null
    private var permissionLauncher: ActivityResultLauncher<Array<String>>? = null
    private var callBack: ResultCallBack? = null
    private var permissionCallBack: PermissionCallBack? = null

    interface ResultCallBack {
        fun success(result: ActivityResult?)
    }

    interface PermissionCallBack {
        fun success(result: Map<String?, Boolean?>?)
    }


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        //必须在onCreate前初始化  回调使用callBack.success(r)
        launcher = registerForActivityResult(StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                callBack!!.success(result)
            }
        }
        permissionLauncher =
            registerForActivityResult(RequestMultiplePermissions()) { result: Map<String?, Boolean?>? ->
                permissionCallBack!!.success(
                    result
                )
            }
        super.onCreate(savedInstanceState)
        //竖屏设置
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        //ViewBinding
        binding = setBinding()
        setContentView(binding?.root)
        appContext = applicationContext
        activity = this
        mContext = this
        initView()
    }


    /**
     * 权限申请回调
     */
    open fun getPermission(callBack: PermissionCallBack?, vararg per: String) {
        permissionCallBack = callBack
        permissionLauncher!!.launch(per as Array<String>?)
    }

    /**
     * 无参跳转
     */
    open fun toActivity(cls: Class<*>?) {
        startActivity(Intent(this, cls))
    }

    /**
     * 带返回+参数的跳转
     */
    open fun toActivity(cls: Class<*>?, key: String?, value: String?, callBack: ResultCallBack?) {
        this.callBack = callBack
        launcher!!.launch(
            Intent(this, cls)
                .putExtra(key, value)
        )
    }


}