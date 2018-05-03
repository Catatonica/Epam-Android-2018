package com.epam.androidlab.task11_listview_recyclerview.model

import android.graphics.Bitmap

/**
 * Class, containing data about contacts.
 */
data class Contact(val id: String,
                   val name: String,
                   val phoneNumber: String,
                   val photo: Bitmap?)
