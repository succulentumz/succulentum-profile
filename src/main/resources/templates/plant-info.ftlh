<!DOCTYPE html>
<html>
<head>
    <title>Succulentum</title>
</head>

<body>
<h1>My collection:</h1>
<h4>Подробная информация о растении</h4>
Название растения: <b>${plant.englishName}</b><br>
Латинское название растения: <b>${plant.englishName}</b><br>
Описание растения: <b>z${plant.description}</b><br>

<hr>

<form action="/collection/plant/delete/{id}" method="post">
    <input type="submit" value="Удалить растение">
</form>
</body>
</html>