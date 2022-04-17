package com.ltb.file_manage.ui

import com.ltb.file_manage.basic.BasicActivity
import com.ltb.file_manage.databinding.ActivityMainBinding
import com.ltb.file_manage.ui.permission.PermissionActivity

class MainActivity : BasicActivity<ActivityMainBinding>() {
    override fun setBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding?.butPermission?.setOnClickListener { toActivity(PermissionActivity::class.java) }
    }


}