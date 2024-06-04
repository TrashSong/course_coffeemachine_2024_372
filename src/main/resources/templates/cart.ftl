<#import "templ/templ.ftl" as p>
<@p.pages>
    <h2> Cart </h2>

    <table class="table table-striped table-hover">
        <tbody>
        <th>id</th>
        <th>name</th>
        <th>ingredient</th>
<#--        <th>update</th>-->
<#--        <th>delete</th>-->
        </tbody>
        <tbody>
        <#if cart??>
            <#list cart as item>
<#--                <#if item.ingredientsHasDrink.name??>-->
                <tr>
                    <form action="/updateItemInCart" method="post">
                        <td>${item?index+1}</td>
                        <td>${item.ingredientsHasDrink._drink.name}</td>
                        <td>${item.ingredientsHasDrink._ingredient.name}</td>
<#--                        <td>${item.ingredientsHasDrink._size}</td>-->
<#--                        <td><img src="${item.ingredientsHasDrink.image}" alt="${item.ingredientsHasDrink.image}" height="30px"></td>-->
<#--                        <td>${item.ingredientsHasDrink.name}</td>-->
<#--                        <td><input type="number" value="${item.quantity}" name="quantity" min="0" step="1"></td>-->
<#--                        <td>${item.ingredientsHasDrink.price}</td>-->
<#--                        <td>${item.ingredientsHasDrink.price * item.quantity}</td>-->
<#--                        <td><button type="submit">Update</button></td>-->
                    </form>

<#--                    <form action="/deleteItemFromCart" method="post">-->
<#--                        <td>-->
<#--                            <input type="hidden" name="id" value="${item.ingredientsHasDrink.id}">-->
<#--                            <button type="submit" class="btn btn-success">Delete</button>-->
<#--                        </td>-->
<#--                    </form>-->
                </tr>
<#--                </#if>-->
            </#list>
        </#if>
        </tbody>
    </table>

    <h5> total: [${total}] </h5>

    <h5> Очистити кошик </h5>
    <form method="post" action="/deleteAllItem">
        <button class="btn btn-success" type="submit">Delete all</button>
    </form>

    <form method="get" action="/order">
        <button type="submit">Order</button>
    </form>

</@p.pages>