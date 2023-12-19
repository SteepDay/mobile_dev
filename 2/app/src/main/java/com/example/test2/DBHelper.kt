package com.example.test2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(val context: Context, val factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "app", factory, 1) {

    // Метод, вызываемый при создании базы данных
    override fun onCreate(db: SQLiteDatabase?) {
        // Запрос на создание таблицы пользователей
        val query = "CREATE TABLE users (id INT PRIMARY KEY, login TEXT, email TEXT, pass TEXT)"
        db!!.execSQL(query)
    }

    // Метод, вызываемый при обновлении базы данных
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Удаление старой таблицы, если она существует
        db!!.execSQL("DROP TABLE IF EXISTS users")
        // Создание новой таблицы
        onCreate(db)
    }

    // Метод для добавления пользователя в базу данных
    fun addUser(user: User) {
        // Создание объекта ContentValues для хранения данных пользователя
        val values = ContentValues()
        values.put("login", user.login)
        values.put("email", user.email)
        values.put("pass", user.pass)

        // Получение доступа к базе данных
        val db = this.writableDatabase
        // Вставка данных пользователя в таблицу
        db.insert("users", null, values)

        // Закрытие соединения с базой данных
        db.close()
    }

    // Метод для получения пользователя из базы данных
    fun getUser(login: String, pass: String): Boolean {
        // Получение доступа к базе данных для чтения
        val db = this.readableDatabase

        // Выполнение запроса на выборку данных пользователя
        val result = db.rawQuery("SELECT * FROM users WHERE login = '$login' AND pass = '$pass'", null)

        return result.moveToFirst()
    }
}
