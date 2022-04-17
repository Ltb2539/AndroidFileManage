package com.ltb.file_manage.util


class PermissionUtils private constructor() {

    companion object {
        private var instance: PermissionUtils? = null
            //这里使用的是自定义访问器
            get() {
                if (field == null) {
                    field = PermissionUtils()
                }
                return field
            }

        fun get(): PermissionUtils{
            return instance!!
        }
    }

    /**
     *
     * */

}