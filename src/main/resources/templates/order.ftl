<#import "templ/templ.ftl" as p>
<@p.pages>

    <h2> Інформація про замовлення </h2>

    <table class="table">

        <thead>
        <tr>
            <th>image</th>
            <th>name</th>
            <th>quantity</th>
            <th>price</th>
            <th>value</th>
        </tr>
        </thead>
        <tbody>


        <#if cart??>
            <#list cart as item>
                <#if item.ingredientsHasDrink._drink.name??>
                    <tr>
                        <td><img src="${item.ingredientsHasDrink._drink.image}" alt="${item.ingredientsHasDrink._drink.name}" height="30px"></td>
                        <td>${item.ingredientsHasDrink._drink.name}</td>
                        <td>${item.ingredientsHasDrink._drink.price}</td>
                    </tr>
                </#if>
            </#list>
        </#if>
        </tbody>
    </table>

    <p> Варість замовлення : ${total} </p>
    <p> Кількість асотиментних позицій у замовленні: ${el}</p>


    <form action="/buy" method="post">

    <h2> Вибір способу оплати </h2>
    <select name="payment">
        <option value="1">Готівка</option>
        <option value="2">Банківська карта</option>
    </select>

        <button type="submit">Підтвердити замовлення</button>

    </form>

</@p.pages>