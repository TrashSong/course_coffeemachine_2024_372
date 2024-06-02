<#import "admin_parts_templ/templ_admin.ftl" as p>
<@p.pages>

    <h2> Адміністування напоїв </h2>

    <h5> Завантажити з файлу </h5>
    <form action="saveFromExcel" method="post" enctype="multipart/form-data">
        <input type="file" name="file">
        <input type="submit" value="Завантажити файл" name="submit">
    </form>

    <hr>
    <h4>Форма для додавання нових напоїв</h4>

    <form action="/saveNewDrink" method="post">

        <label for="name">Name</label><br>
        <input type="text" id="name" name="name" placeholder="name"><br>

        <label for="description">description</label><br>
        <input type="text" id="description" name="description" placeholder="description"><br>

        <label for="price">price</label><br>
        <input type="number" id="price" name="price" placeholder="price"><br>

        <label for="link">link</label><br>
        <input type="text" id="link" name="link" placeholder="link"><br>

        <input type="submit" value="addNewDrink">

    </form>

    <hr>
    <br>

    <h2>Перегляд, оновлення та видалення даних</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <td>id</td>
            <td>name</td>
            <td>descr</td>
            <td>image</td>
            <td>price</td>
            <td>update</td>
            <td>delete</td>
        </tr>
        </thead>

        <tbody>
        <#list drinks as drink>
            <form action="/updateDrink" method="post">
                <tr>
                    <td>${drink.id}</td>
                    <input type="hidden" name="id" value="${drink.id}">
                    <td><input type="text" name="name" value="${drink.name}"></td>
                    <td><input type="text" name="description" value="${drink.description}"></td>
                    <td><input type="text" name="image" value="${drink.image}"></td>
                    <td><input type="number" name="price" value="${drink.price?c}"></td>

                    <td><input type="submit" value="update"></td>
            </form>
            <td>

                <form method="post" action="/deleteDrink">
                    <input type="hidden" name="id" value="${drink.id}">
                    <input type="submit" value="delete">
                </form>

            </td>
            </tr>
        </#list>
        </tbody>
    </table>

</@p.pages>