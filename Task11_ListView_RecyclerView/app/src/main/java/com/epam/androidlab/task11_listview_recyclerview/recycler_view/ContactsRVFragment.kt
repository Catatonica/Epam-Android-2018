package com.epam.androidlab.task11_listview_recyclerview.recycler_view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.epam.androidlab.task11_listview_recyclerview.R
import com.epam.androidlab.task11_listview_recyclerview.contact_listener.OnContactClickListener
import com.epam.androidlab.task11_listview_recyclerview.model.ContactList
import kotlinx.android.synthetic.main.contacts_rv_fragment.view.*
import kotlinx.android.synthetic.main.floating_action_btn.*

/**
 * This fragment is used to show a scrollable list of contacts( on each of it contact
 * you can click on to dial with).
 */
class ContactsRVFragment : Fragment() {

    private val mTOAST = "Press on some contact to dial with it"
    private var listener: OnContactClickListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            listener = context as OnContactClickListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement OnContactClickListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.contacts_rv_fragment, container, false)
    }

    /**
     * Gets a list of contacts, sets layoutManager and adapter for the RecyclerView and
     * defines OnScrollListener, which will hide FloatingActionButton if needed.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contactList = ContactList.getContactList()

        fabInfo.setOnClickListener({ Toast.makeText(context, mTOAST, Toast.LENGTH_LONG).show() })

        val rvContactList = view.rvContactsList
        rvContactList.setHasFixedSize(true)
        val manager = LinearLayoutManager(view.context)
        rvContactList.layoutManager = manager
        val adapter = ContactsRVAdapter(listener!!, contactList!!)
        rvContactList.adapter = adapter
        rvContactList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    fabInfo.visibility = View.INVISIBLE
                } else if (dy < 0) {
                    fabInfo.visibility = View.VISIBLE
                }
            }
        })
    }
}
