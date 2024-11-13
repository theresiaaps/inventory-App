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
/**
 * Repository bertanggung jawab untuk menyediakan akses ke data [Item] melalui operasi dasar
 * seperti insert, update, delete, dan retrieve. Operasi ini dilakukan terhadap sumber data tertentu
 * seperti database.
 */
import kotlinx.coroutines.flow.Flow

/**
 * Interface yang mendefinisikan kontrak untuk operasi pada data [Item], memungkinkan penggunaan berbagai
 * implementasi sumber data seperti Room database.
 */
interface ItemsRepository {
    /**
     * Mengambil semua item yang ada dalam sumber data secara aliran (streaming).
     * Fungsi ini mengembalikan objek Flow yang memungkinkan data untuk selalu diperbarui secara reaktif.
     */
    fun getAllItemsStream(): Flow<List<Item>>

    /**
     * Mengambil sebuah item berdasarkan [id] dari sumber data secara aliran.
     * Fungsi ini mengembalikan objek Flow yang berisi data item atau null jika item dengan id tersebut tidak ditemukan.
     */
    fun getItemStream(id: Int): Flow<Item?>

    /**
     * Menyisipkan item baru ke dalam sumber data.
     * Fungsi ini bersifat suspending yang berarti operasi ini dilakukan secara asinkron dan memodifikasi data secara permanen.
     */
    suspend fun insertItem(item: Item)

    /**
     * Menghapus item dari sumber data.
     * Fungsi ini bersifat suspending yang artinya menjalankan operasi penghapusan data secara asinkron tanpa memblokir thread utama.
     */
    suspend fun deleteItem(item: Item)

    /**
     * Memperbarui item yang sudah ada dalam sumber data.
     * Fungsi ini bersifat suspending untuk menjalankan operasi pembaruan data secara asinkron tanpa mengganggu jalannya aplikasi lainnya.
     */
    suspend fun updateItem(item: Item)
}
