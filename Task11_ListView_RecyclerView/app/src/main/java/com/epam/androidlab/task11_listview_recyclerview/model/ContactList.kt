package com.epam.androidlab.task11_listview_recyclerview.model

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.ContactsContract

/**
 * Class-repository, containing information about all contacts.
 */
object ContactList {
    private var contacts: ArrayList<Contact>? = null

    fun setContactList(context: Context) {
        contacts = ArrayList()
        val cr = context.contentResolver
        val cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
        if (cursor != null && cursor.count > 0) {
            while (cursor.moveToNext()) {
                val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    setContact(cr, cursor, id)
                }
            }
            cursor.close()
        }
    }

    private fun setContact(cr: ContentResolver, cursor: Cursor, id: String) {
        val cursorInfo = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                Array(1, { id }),
                null)
        val inputStream = ContactsContract.Contacts
                .openContactPhotoInputStream(cr, ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id.toLong()))

        var photo: Bitmap? = null
        if (inputStream != null) {
            photo = BitmapFactory.decodeStream(inputStream)
        }
        while (cursorInfo?.moveToNext()!!) {
            val info = Contact(
                    id,
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)),
                    cursorInfo.getString(cursorInfo.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)),
                    photo
            )
            contacts?.add(info)
        }
        cursorInfo.close()
    }

    fun getContactList(): ArrayList<Contact>? {
        return contacts
    }
}