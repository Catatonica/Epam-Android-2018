package com.epam.androidlab.task11_listview_recyclerview.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.epam.androidlab.task11_listview_recyclerview.R
import com.epam.androidlab.task11_listview_recyclerview.contact_listener.OnContactClickListener
import com.epam.androidlab.task11_listview_recyclerview.list_view.ContactsLVFragment
import com.epam.androidlab.task11_listview_recyclerview.model.ContactList
import com.epam.androidlab.task11_listview_recyclerview.recycler_view.ContactsRVFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * This activity shows user a list of his contacts, using ListView or RecyclerView,
 * which are enclosed in fragments. By clicking on some contact, you can dial with it.
 * You can choose what container to use(a fragment with RV or LV) by clicking on
 * appropriate button.
 *
 * @author Elizabeth Gavina
 * @since 29.04.2018
 */
 class MainActivity : AppCompatActivity(), OnContactClickListener {

    private val TAG_RV = "recycler view"
    private val TAG_LV = "list view"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ContactList.setContactList(this)
        init()
        btnRecyclerView.setOnClickListener { replaceFragment(ContactsRVFragment(), TAG_RV) }
        btnListView.setOnClickListener { replaceFragment(ContactsLVFragment(), TAG_LV) }
    }

    /**
     * Initially, fragment with ListView inside is showed.
     */
    private fun init() {
        val container = supportFragmentManager.findFragmentById(R.id.flContainer)
        if (container == null) {
            val fragment = ContactsLVFragment()
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.flContainer, fragment, TAG_LV)
                    .commit()
        }
    }

    /**
     * Replaces old fragment with a new one if needed(using findFragmentByTag).
     *
     * @param newFragment fragment to show
     * @param tag parameter, by which fragment is searched
     */
    private fun replaceFragment(newFragment: Fragment, tag: String) {
        val fragment = supportFragmentManager.findFragmentByTag(tag)
        if (fragment == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.flContainer, newFragment, tag)
                    .commit()
        }
    }

    /**
     * Dials with clicked contact.
     *
     * @param contactID id of the clicked contact
     */
    override fun onContactClick(contactID: Int) {
        val number = Uri.parse("tel:" + ContactList.getContactList()!![contactID].phoneNumber)
        val callIntent = Intent(Intent.ACTION_DIAL, number)
        startActivity(callIntent)
    }
}
