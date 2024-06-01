package cl.cfuentes.prueba3aplicacionesmoviles.helpers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import cl.cfuentes.prueba3aplicacionesmoviles.dataclasses.Animal

/**
 * Clase VeterinariaDatabaseHelper
 * Desarrollado por: Christian Fuentes Unda
 * Fecha: 15/05/2024 09:47
 *
 * Ultima modificacion por:
 * Fecha:
 */
class VeterinariaDatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION){

    companion object{
        private const val DATABASE_NAME="veterinaria.db"
        private const val DATABASE_VERSION=2
        private const val TABLE_NAME="animales"
        private const val COLUMN_CHIP="chip_animal"
        private const val COLUMN_NOMBRE_ANIMAL="nombre_animal"
        private const val COLUMN_NOMBRE_DUENO="nombre_dueno"
        private const val COLUMN_NOMBRE_RAZA="raza"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_CHIP INTEGER PRIMARY KEY, $COLUMN_NOMBRE_ANIMAL TEXT, $COLUMN_NOMBRE_DUENO TEXT, $COLUMN_NOMBRE_RAZA TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertAnimal(animal:Animal):Int{
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_CHIP, animal.chip)
            put(COLUMN_NOMBRE_ANIMAL, animal.nombre_animal)
            put(COLUMN_NOMBRE_DUENO, animal.nombre_dueno)
            put(COLUMN_NOMBRE_RAZA , animal.raza)
        }
        val estado = db.insert(TABLE_NAME,   null, values)
        db.close()
        return estado.toInt()

    }

    fun getAllAnimales():List<Animal> {
        val listaAnimales = mutableListOf<Animal>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_CHIP DESC"
        val cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()){
            val chip = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CHIP))
            val nombre_animal = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE_ANIMAL))
            val nombre_dueno = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE_DUENO))
            val nombre_raza = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE_RAZA))

            val animal = Animal(chip, nombre_animal, nombre_dueno ,nombre_raza)
            listaAnimales.add(animal)
        }
        cursor.close()
        db.close()
        return listaAnimales
    }

    fun getAnimalByChip(numChip:Int):Animal{
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_CHIP = $numChip"
        val cursor = db.rawQuery(query,null)
        cursor.moveToFirst()
        val chip = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CHIP))
        val nombre_animal = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE_ANIMAL))
        val nombre_dueno = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE_DUENO))
        val nombre_raza = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE_RAZA))

        cursor.close()
        db.close()
        return Animal(chip, nombre_animal, nombre_dueno, nombre_raza)

    }

    //ToDo Agregar funcion para eliminar

    fun deleteAnimal(numChip: Int) {
        val db = writableDatabase
        val whereClause = "$COLUMN_CHIP = ?"
        val whereArgs = arrayOf(numChip.toString())
        db.delete(TABLE_NAME, whereClause, whereArgs)
        db.close()
    }

    //ToDo Agregar funcion para actualizar

    fun actualizarAnimal(animal: Animal){

        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE_ANIMAL,animal.nombre_animal)
            put(COLUMN_NOMBRE_DUENO,animal.nombre_dueno)
            put(COLUMN_NOMBRE_RAZA,animal.raza)
        }

        val whereClause = "$COLUMN_CHIP = ?"
        val whereArgs = arrayOf(animal.chip.toString())

        db.update(TABLE_NAME,values,whereClause,whereArgs)
        db.close()



    }




}