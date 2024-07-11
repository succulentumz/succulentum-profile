<!DOCTYPE html>
<html>
<head>
    <title>Регистрация</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }
        form {
            max-width: 400px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        label {
            display: block;
            margin-bottom: 5px;
            color: #333;
        }
        input {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 10px;
            cursor: pointer;
        }
        .error {
            color: red;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<form id="registrationForm">
    <h1>Форма регистрации</h1>
    <div id="error" class="error"></div>
    <label for="username">Имя пользователя:</label>
    <input type="text" id="username" name="username" required>
    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password" required>
    <label for="confirmPassword">Подтвердите пароль:</label>
    <input type="password" id="confirmPassword" name="confirmPassword" required>
    <input type="hidden" id="roles" name="roles" value="USER">
    <input type="submit" value="Зарегистрироваться">
</form>

<script>
    document.getElementById('registrationForm').addEventListener('submit', function(event) {
        event.preventDefault();

        // Очистить предыдущие ошибки
        var errorDiv = document.getElementById('error');
        errorDiv.textContent = '';

        // Получить значения полей
        var username = document.getElementById('username').value;
        var password = document.getElementById('password').value;
        var confirmPassword = document.getElementById('confirmPassword').value;

        // Проверка имени пользователя
        if (username.length < 3 || username.length > 20) {
            errorDiv.textContent = 'Имя пользователя должно быть от 3 до 20 символов.';
            return;
        }

        // Проверка пароля
        if (password.length < 8) {
            errorDiv.textContent = 'Пароль должен быть не менее 8 символов.';
            return;
        }

        // Проверка совпадения паролей
        if (password !== confirmPassword) {
            errorDiv.textContent = 'Пароли не совпадают.';
            return;
        }

        // Если все проверки пройдены
        var formData = {
            username: username,
            password: password,
            roles: document.getElementById('roles').value
        };

        fetch('/new-userEntity', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        }).then(response => {
            if (response.ok) {
                window.location.href = '/';
            } else {
                alert('Ошибка регистрации');
            }
        }).catch(error => {
            console.error('Ошибка:', error);
            alert('Ошибка регистрации');
        });
    });
</script>
</body>
</html>
