package net.deali.navigator

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

abstract class NavigatorContentProvider : ContentProvider() {

    abstract fun registerActivities()

    final override fun onCreate(): Boolean {
        registerActivities()
        return false
    }

    final override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }

    final override fun getType(uri: Uri): String? {
        return null
    }

    final override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    final override fun delete(
        uri: Uri,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return -1
    }

    final override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return -1
    }

}