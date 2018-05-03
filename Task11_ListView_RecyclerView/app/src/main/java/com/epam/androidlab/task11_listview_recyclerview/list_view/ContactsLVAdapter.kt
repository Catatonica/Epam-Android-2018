package com.epam.androidlab.task11_listview_recyclerview.list_view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.epam.androidlab.task11_listview_recyclerview.R
import com.epam.androidlab.task11_listview_recyclerview.contact_listener.OnContactClickListener
import com.epam.androidlab.task11_listview_recyclerview.model.Contact
import kotlinx.android.synthetic.main.contacts_item.view.*

/**
 * It's an adapter for ContactsLVFragment. It uses OnContactClickListener's
 * method onContactClick when user clicks on list item.
 */
class ContactsLVAdapter(private val listener: OnContactClickListener,
                        private val contacts: ArrayList<Contact>,
                        private val context: Context) : BaseAdapter() {

    /**
     * Binds a contact with information and sets a listener.
     *
     * @param i contact's index
     * @param view   R.layout.contacts_item
     * @param viewGroup
     */
    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View? {
        var v = view
        if (view == null) {
            v = LayoutInflater
                    .from(context)
                    .inflate(R.layout.contacts_item, viewGroup, false)
        }

        val contact = contacts[i]
        v!!.ivPhoto.setImageBitmap(contact.photo)
        v.tvName.text = contact.name
        v.setOnClickListener({ listener.onContactClick(i) })
        return v
    }

    override fun getItem(i: Int): Any {
        return contacts[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return contacts.size
    }
}

