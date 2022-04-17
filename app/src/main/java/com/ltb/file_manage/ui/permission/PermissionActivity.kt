package com.ltb.file_manage.ui.permission

import com.ltb.file_manage.basic.BasicActivity
import com.ltb.file_manage.basic.Bean
import com.ltb.file_manage.databinding.ActivityPermissionBinding
import com.ltb.file_manage.databinding.RecyclerPermissionBinding
import com.ltb.file_manage.util.BaseBindingAdapter

class PermissionActivity : BasicActivity<ActivityPermissionBinding>() {
    override fun setBinding(): ActivityPermissionBinding {
        return ActivityPermissionBinding.inflate(layoutInflater)
    }

    override fun initView() {
        val adapter: BaseBindingAdapter<Bean, RecyclerPermissionBinding> =
            object : BaseBindingAdapter<Bean, RecyclerPermissionBinding>(){
                override fun init(binding: RecyclerPermissionBinding, bean: Bean, pos: Int) {

                }
            }
        adapter.setOnItemClickListener { _, _, _ ->  }
        binding?.recycler?.adapter = adapter
    }

    /**
     * ACCESS_BACKGROUND_LOCATION   允许应用在后台访问位置。
     * */
}