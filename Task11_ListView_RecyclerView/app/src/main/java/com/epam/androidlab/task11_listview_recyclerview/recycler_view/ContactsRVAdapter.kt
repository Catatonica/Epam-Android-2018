package com.epam.androidlab.task11_listview_recyclerview.recycler_view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.epam.androidlab.task11_listview_recyclerview.R
import com.epam.androidlab.task11_listview_recyclerview.contact_listener.OnContactClickListener
import com.epam.androidlab.task11_listview_recyclerview.model.Contact
import kotlinx.android.synthetic.main.contacts_item.view.*

/**
 * It's an adapter for ContactsRVFragment. It uses OnContactClickListener's
 * method onContactClick when user clicks on list item.
 */
class ContactsRVAdapter(private val listener: OnContactClickListener,
                        private val contacts: ArrayList<Contact>)
    : RecyclerView.Adapter<ContactsRVAdapter.ContactListVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListVH {
        val contactView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.contacts_item, parent, false)
        return ContactListVH(contactView)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    /**
     * Binds a contact with information and sets a listener.
     *
     * @param holder   R.layout.contacts_item
     * @param position index in array
     */
    override fun onBindViewHolder(holder: ContactListVH, position: Int) {
        val contact = contacts[position]
        holder.tvName.text = contact.name
        holder.ivPhoto.setImageBitmap(contact.photo)
        holder.cvContact.setOnClickListener({ listener.onContactClick(position) })
    }

    inner class ContactListVH(v: View) : RecyclerView.ViewHolder(v) {
        val cvContact = v.cvContact!!
        val tvName = v.tvName!!
        val ivPhoto = v.ivPhoto!!
    }
}
