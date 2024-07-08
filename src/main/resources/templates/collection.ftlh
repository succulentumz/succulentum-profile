<!DOCTYPE html>
<html>
<head>
    <title>Succulentum</title>
</head>

<body>
    <h1>My collection:</h1>
    <#list plants as plant>
        <div>
            <p><b>${plant.englishName} (${plant.latinName})</b> | <a href="/collection/plant/${plant.id}">Подробнее...</a> </p>

        </div>
    </#list>

<hr>
<h3>Добавить в коллекцию</h3>
<form action="collection/plant/create" method="post">
    Название растения: <input type="text" name="englishName"/><br><br>
    Латинское название растения: <input type="text" name="latinName"/><br><br>
    Описание растения: <input type="text" name="description"/><br><br>
    <input type="submit" value="Добавить растение">

</form>
</body>
</html>