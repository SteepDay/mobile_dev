package com.example.test2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class AuthActivity : AppCompatActivity() {
    // Метод, вызываемый при создании активности
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        // Инициализация полей ввода и кнопок
        val userLogin: EditText = findViewById(R.id.user_login_auth)
        val userPass: EditText = findViewById(R.id.user_pass_auth)
        val button: Button = findViewById(R.id.button_reg_auth)
        val linkToReg: TextView = findViewById(R.id.link_to_reg)

        // Обработчик клика по ссылке для регистрации
        linkToReg.setOnClickListener {
            // Создание намерения перехода на страницу регистрации
            val intent = Intent(this, MainActivity::class.java)
            // Запуск активности регистрации
            startActivity(intent)
        }

        // Обработчик клика по кнопке авторизации
        button.setOnClickListener {
            // Получение данных из полей ввода
            val login = userLogin.text.toString().trim()
            val pass = userPass.text.toString().trim()

            // Проверка на пустые поля
            if(login == "" || pass == "")
                Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_LONG).show()
            else {
                // Проверка пользователя в базе данных
                val db = DBHelper(this, null)
                val isAuth = db.getUser(login, pass)

                // Если пользователь авторизован
                if (isAuth) {
                    Toast.makeText(this, "Пользователь $login авторизован", Toast.LENGTH_LONG)
                        .show()
                    // Очистка полей ввода
                    userLogin.text.clear()
                    userPass.text.clear()

                    // Переход на страницу товаров
                    val intent = Intent(this, ItemsActivity::class.java)
                    startActivity(intent)
                } else
                    Toast.makeText(this, "Пользователь $login не авторизован", Toast.LENGTH_LONG)
                        .show()
            }
        }
    }
}
