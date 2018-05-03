package com.epam.androidlab.task11_listview_recyclerview.contact_listener

/**
 * This interface in implemented in MainActivity in order to control
 * events, happening in ContactsLVFragment and ContactsRVFragment
 * (clicking on some contact).
 */
interface OnContactClickListener {
    fun onContactClick(contactID: Int)
}
