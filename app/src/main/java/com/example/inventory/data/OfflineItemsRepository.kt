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

import kotlinx.coroutines.flow.Flow
/**
 * Kelas ini adalah implementasi dari [ItemsRepository] yang berfungsi untuk mengakses dan memanipulasi data
 * [Item] melalui Room database sebagai sumber data lokal.
 * Kelas ini berinteraksi langsung dengan DAO (Data Access Object) untuk menangani operasi database.
 */
class OfflineItemsRepository(private val itemDao: ItemDao) : ItemsRepository {

    /**
     * Mengambil semua item yang ada dari sumber data (DAO) secara aliran (streaming).
     * Fungsi ini memanfaatkan [getAllItems()] dari DAO untuk menyediakan data dalam bentuk [Flow],
     * yang akan terus mengalir dan selalu diperbarui.
     */
    override fun getAllItemsStream(): Flow<List<Item>> = itemDao.getAllItems()

    /**
     * Mengambil item tertentu berdasarkan [id] dari sumber data (DAO).
     * Fungsi ini memanggil [getItem()] dari DAO dan mengembalikan item dalam bentuk [Flow],
     * yang berarti data dapat berubah seiring waktu dan akan memberikan hasil yang selalu terbarui.
     */
    override fun getItemStream(id: Int): Flow<Item?> = itemDao.getItem(id)

    /**
     * Menambahkan item baru ke dalam database melalui DAO.
     * Fungsi ini memanggil [insert()] dari DAO untuk menyimpan item, dilakukan secara asinkron untuk
     * menjaga agar UI tetap responsif.
     */
    override suspend fun insertItem(item: Item) = itemDao.insert(item)

    /**
     * Menghapus item dari database melalui DAO.
     * Fungsi ini memanggil [delete()] dari DAO untuk menghapus data item secara asinkron.
     */
    override suspend fun deleteItem(item: Item) = itemDao.delete(item)

    /**
     * Memperbarui data item yang sudah ada dalam database melalui DAO.
     * Fungsi ini memanggil [update()] dari DAO untuk mengubah informasi item.
     */
    override suspend fun updateItem(item: Item) = itemDao.update(item)
}
