package com.example.network

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Entity
data class MoviesDB(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "idProduct") val idProduct: Int?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "release_date") val release_date: String?,
    @ColumnInfo(name = "poster_path") val poster_path: String?,
    @ColumnInfo(name = "original_lang") val original_lang: String?,
    @ColumnInfo(name = "overview") val overview: String?,
    @ColumnInfo(name = "vote_avg") val vote_avg: Float?
)

fun toEntity(idProd: Int, nameProd: String, releaseDate: String, posterPath: String?, originalLang: String, overviewProd: String, voteAvg: Float, typeOf: String) = MoviesDB(
    id = 0,
    type = typeOf,
    idProduct = idProd,
    name = nameProd,
    release_date = releaseDate,
    poster_path = posterPath,
    original_lang = originalLang,
    overview = overviewProd,
    vote_avg = voteAvg
)

@Dao
interface MoviesDao {
    @Insert
    fun insert(product: MoviesDB)

    @Query("SELECT COUNT(idProduct) FROM MoviesDB WHERE idProduct = :idProduct")
    suspend fun checkIfIsFavourite(idProduct: Int): Int

    @Query("DELETE FROM MoviesDB WHERE idProduct = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM MoviesDB ORDER BY type")
    suspend fun getDataFromDB(): List<MoviesDB>?
}

@Database(entities = [MoviesDB::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao

    companion object {
        @Volatile
        private var INSTANCE: MoviesDatabase? = null

        fun getDatabase(context: Context): MoviesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesDatabase::class.java,
                    "Movies-DB"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
