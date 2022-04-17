package com.ltb.file_manage.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import java.lang.reflect.ParameterizedType

/**
 *Time:2022/4/17   20:07
 *Author:林天宝
 *项目名称：AndroidFileManage
 */
class VBViewHolder<VB : ViewBinding>(val vb: VB, view: View) : BaseViewHolder(view)

abstract class BaseBindingAdapter<T, VB : ViewBinding>(data: MutableList<T>? = null) :
    BaseQuickAdapter<T, VBViewHolder<VB>>(0, data) {

    //重写返回自定义 ViewHolder
    override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int): VBViewHolder<VB> {
        //这里为了使用简洁性，使用反射来实例ViewBinding
        val vbClass: Class<VB> =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VB>
        val inflate = vbClass.getDeclaredMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        val mBinding =
            inflate.invoke(null, LayoutInflater.from(parent.context), parent, false) as VB
        return VBViewHolder(mBinding, mBinding.root)
    }

    override fun convert(holder: VBViewHolder<VB>, item: T) {
        init(holder.vb, item, holder.layoutPosition)
    }

    abstract fun init(binding: VB, bean: T, pos: Int)
}