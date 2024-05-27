<#import "templ/templ.ftl" as p>
<@p.pages>

    <div class="row row-cols-1 row-cols-md-4 g-4">

        <#if drinksByName??>
            <#list drinksByName as drinks>
                <form action="/addToCart" method="post">
                    <div class="col">
                        <div class="card">
                            <input type="hidden" name="id" value="${drinks.id}">
                            <button type="submit">
                                <img src="${drinks.image}" class="card-img-top" alt="${drinks.name}">
                            </button>
                            <div class="card-body text-center">
                                <h5 class="card-title">${drinks.name}</h5>
                                <p class="card-text">${drinks.description}</p>
                                <p class="card-text">${drinks.price}$</p>
                            </div>
                        </div>
                    </div>
                </form>
            </#list>
        </#if>
    </div>
    <br>
    <div class="col-12">
        <div class="btn-group" role="group" aria-label="Basic checkbox toggle button group">
            <input type="checkbox" class="btn-check" id="btncheck1" autocomplete="off">
            <label class="btn btn-outline-primary" for="btncheck1">Молоко</label>

            <input type="checkbox" class="btn-check" id="btncheck2" autocomplete="off">
            <label class="btn btn-outline-primary" for="btncheck2">Вершки</label>

            <input type="checkbox" class="btn-check" id="btncheck3" autocomplete="off">
            <label class="btn btn-outline-primary" for="btncheck3">Сироп</label>
        </div>

        <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
            <input type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" checked>
            <label class="btn btn-outline-primary" for="btnradio1">Small</label>

            <input type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off">
            <label class="btn btn-outline-primary" for="btnradio2">Medium</label>

            <input type="radio" class="btn-check" name="btnradio" id="btnradio3" autocomplete="off">
            <label class="btn btn-outline-primary" for="btnradio3">Large</label>
        </div>

        <div class="btn-group" role="group">
            <a class="btn btn-primary" href="/payment" role="button">Order</a>
        </div>

    </div>

</@p.pages>