package com.skyblue.skybluecontacts.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.skyblue.skybluecontacts.model.ContactsRoom

@Dao
interface ContactsRoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: List<ContactsRoom>)

    @Query("SELECT * FROM contacts")
    suspend fun getAllContacts(): List<ContactsRoom>

    @Query("DELETE FROM contacts")
    suspend fun deleteAllContacts()

    @Query("SELECT COUNT(*) FROM contacts")
    suspend fun getContactsCount(): Int

    @Query("SELECT * FROM contacts WHERE firstName LIKE '%' || :query || '%' OR phoneNumber LIKE '%' || :query || '%'")
    fun searchContacts(query: String): LiveData<List<ContactsRoom>>
}