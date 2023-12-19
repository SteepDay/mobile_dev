package com.example.test2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    // Метод, вызываемый при создании активности
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация полей ввода и кнопок
        val userLogin: EditText = findViewById(R.id.user_login)
        val userEmail: EditText = findViewById(R.id.user_email)
        val userPass: EditText = findViewById(R.id.user_pass)
        val button: Button = findViewById(R.id.button_reg)
        val linkToAuth: TextView = findViewById(R.id.link_to_auth)

        // Обработчик клика по ссылке для авторизации
        linkToAuth.setOnClickListener {
            // Создание намерения перехода на страницу авторизации
            val intent = Intent(this, AuthActivity::class.java)
            // Запуск активности авторизации
            startActivity(intent)
        }

        // Обработчик клика по кнопке регистрации
        button.setOnClickListener {
            // Получение данных из полей ввода
            val login = userLogin.text.toString().trim()
            val email = userEmail.text.toString().trim()
            val pass = userPass.text.toString().trim()

            // Проверка на пустые поля
            if(login == "" || email == "" || pass == "")
                Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_LONG).show()
            else {
                // Создание нового пользователя
                val user = User(login, email, pass)

                // Добавление пользователя в базу данных
                val db = DBHelper(this, null)
                db.addUser(user)
                Toast.makeText(this, "Пользователь $login добавлен", Toast.LENGTH_LONG).show()

                // Очистка полей ввода
                userLogin.text.clear()
                userEmail.text.clear()
                userPass.text.clear()
            }
        }
    }
}
