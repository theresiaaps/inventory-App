/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Database access object to access the Inventory database
 */
@Dao
interface ItemDao {
    /**
     * Fungsi untuk mengambil semua data item dari database.
     * Mengembalikan Flow yang dapat diamati dan mengurutkan item berdasarkan nama secara ascending.
     */
    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>

    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    /**
     * Fungsi untuk menyisipkan data item ke dalam database.
     * Jika ada konflik, item yang sama akan diabaikan (ignore).
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    /**
     * Fungsi untuk memperbarui data item yang sudah ada di database.
     * Data item akan digantikan dengan nilai baru jika ada perubahan.
     */
    @Update
    suspend fun update(item: Item)

    /**
     * Fungsi untuk menghapus item dari database.
     * Menghapus item sesuai dengan yang diberikan sebagai parameter.
     */
    @Delete
    suspend fun delete(item: Item)
}
