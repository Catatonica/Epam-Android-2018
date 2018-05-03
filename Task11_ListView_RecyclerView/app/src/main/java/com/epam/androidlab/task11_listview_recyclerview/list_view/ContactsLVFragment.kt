package com.epam.androidlab.task11_listview_recyclerview.list_view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import com.epam.androidlab.task11_listview_recyclerview.R
import com.epam.androidlab.task11_listview_recyclerview.contact_listener.OnContactClickListener
import com.epam.androidlab.task11_listview_recyclerview.model.ContactList
import kotlinx.android.synthetic.main.contacts_lv_fragment.view.*
import kotlinx.android.synthetic.main.floating_action_btn.*

/**
 * This fragment is used to show a scrollable list of contacts( on each of it contact
 * you can click on to dial with).
 */
class ContactsLVFragment : Fragment() {
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
        return inflater.inflate(R.layout.contacts_lv_fragment, container, false)
    }

    /**
     * Gets a list of contacts, sets adapter for the ListView and
     * defines OnScrollListener, which will hide FloatingActionButton if needed.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contactList = ContactList.getContactList()

        fabInfo.setOnClickListener({ Toast.makeText(context, mTOAST, Toast.LENGTH_LONG).show() })

        val lvContactList = view.lvContactsList
        val adapter = ContactsLVAdapter(listener!!, contactList!!, view.context)
        lvContactList.adapter = adapter
        lvContactList.setOnScrollListener(object : AbsListView.OnScrollListener {

            private var lastVisibleItem: Int = 0
            override fun onScroll(absListView: AbsListView?, i: Int, i1: Int, i2: Int) {
                if (lastVisibleItem < i) {
                    fabInfo.visibility = View.INVISIBLE
                }
                if (lastVisibleItem > i) {
                    fabInfo.visibility = View.VISIBLE
                }
                lastVisibleItem = i
            }

            override fun onScrollStateChanged(p0: AbsListView?, p1: Int) {
            }
        })
    }
}
