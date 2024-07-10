<!DOCTYPE html>
<html>
<head>
    <title>Succulentum</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }
        h1, h3 {
            text-align: center;
            color: #333;
        }
        form {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin-bottom: 5px;
            color: #333;
        }
        input[type="text"] {
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
        .plant {
            max-width: 600px;
            margin: 10px auto;
            padding: 10px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .plant p {
            margin: 0;
        }
    </style>
</head>

<body>
<h1>My collection:</h1>

<form id="searchForm" action="/collection" method="get">
    <label>Поиск по названию:</label>
    <input type="text" name="englishName" id="searchInput">
    <div id="searchError" class="error"></div>
    <input type="submit" value="Найти">
</form>

<#list plants as plant>
    <div class="plant">
        <p><b>${plant.englishName} (${plant.latinName})</b> | <a href="/collection/plant/${plant.id}">Подробнее...</a></p>
    </div>
<#else>
    <h3>У вас пока нет растений. Спешите добавить!</h3>
</#list>

<hr>
<h3>Добавить в коллекцию</h3>
<form id="addPlantForm" action="collection/plant/create" method="post">
    <label>Название растения:</label>
    <input type="text" name="englishName" id="englishName">
    <div id="englishNameError" class="error"></div>

    <label>Латинское название растения:</label>
    <input type="text" name="latinName" id="latinName">
    <div id="latinNameError" class="error"></div>

    <label>Описание растения:</label>
    <input type="text" name="description" id="description">
    <div id="descriptionError" class="error"></div>

    <input type="submit" value="Добавить растение">
</form>

<hr>
<h3>Очистить каталог</h3>
<form action="collection/delete" method="post">
    <input type="submit" value="Очистить каталог">
</form>

<script>
    document.getElementById('searchForm').addEventListener('submit', function(event) {
        var searchInput = document.getElementById('searchInput').value;
        var searchError = document.getElementById('searchError');
        searchError.textContent = '';

        if (searchInput.length < 2) {
            searchError.textContent = 'Название должно содержать минимум 2 символа.';
            event.preventDefault();
        }
    });

    document.getElementById('addPlantForm').addEventListener('submit', function(event) {
        var englishName = document.getElementById('englishName').value;
        var latinName = document.getElementById('latinName').value;
        var description = document.getElementById('description').value;

        var englishNameError = document.getElementById('englishNameError');
        var latinNameError = document.getElementById('latinNameError');
        var descriptionError = document.getElementById('descriptionError');

        englishNameError.textContent = '';
        latinNameError.textContent = '';
        descriptionError.textContent = '';

        if (englishName.length < 3) {
            englishNameError.textContent = 'Название должно содержать минимум 3 символа.';
            event.preventDefault();
        }
        if (latinName.length < 3) {
            latinNameError.textContent = 'Латинское название должно содержать минимум 3 символа.';
            event.preventDefault();
        }
        if (description.length < 10) {
            descriptionError.textContent = 'Описание должно содержать минимум 10 символов.';
            event.preventDefault();
        }
    });
</script>
</body>
</html>
