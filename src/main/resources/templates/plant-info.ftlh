<!DOCTYPE html>
<html>
<head>
    <title>Succulentum</title>
</head>

<body>
    <h1>My collection:</h1>
    <h4>Подробная информация о растении</h4>

    <b>Название растения:</b> ${plant.englishName}<br>
    <b>Латинское название растения:</b> ${plant.englishName}<br>
    <b>Описание растения:</b>${plant.description}<br>

    <hr>

    <form action="/collection/plant/delete/${plant.id}" method="post">
        <input type="submit" value="Удалить растение">
    </form>

    <form action="/collection" method="get">
        <input type="submit" value="Назад в каталог">
    </form>
</body>
</html>