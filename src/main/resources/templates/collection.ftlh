<!DOCTYPE html>
<html>
<head>
    <title>Succulentum</title>
</head>

<body>
    <h1>My collection:</h1>

    <form action="/collection" method="get">
        Поиск по названию: <input type="text" name="englishName"><br>
        <input type="submit" value="Найти">
    </form>


    <#list plants as plant>
        <div>
            <p><b>${plant.englishName} (${plant.latinName})</b> | <a href="/collection/plant/${plant.id}">Подробнее...</a> </p>

        </div>
        <#else>
        <h3>У вас пока нет растений. Спешите добавить!</h3>
    </#list>

    <hr>
    <h3>Добавить в коллекцию</h3>
    <form action="collection/plant/create" method="post">
        Название растения: <label><input type="text" name="englishName"/></label><br><br>
        Латинское название растения: <label><input type="text" name="latinName"/></label><br><br>
        Описание растения: <label> <input type="text" name="description"/></label><br><br>
        <input type="submit" value="Добавить растение">
    </form>

    <hr>
    <h3>Очистить каталог</h3>
    <form action="collection/delete" method="post">
        <input type="submit" value="Очистить каталог">
    </form>

</body>
</html>