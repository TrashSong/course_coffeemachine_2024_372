<#import "/spring.ftl" as spring>
<#import "templ/templ.ftl" as p>
<@p.pages>

    <h2> Registration </h2>

<div class="row">

    <div class="col-6">
    <form action="/registration" method="post">

        <@spring.bind "users"/>

        <label> Username </label>
        <@spring.formInput "users.username"/><br>
        <@spring.showErrors "<br>"/><br>

        <label> Password </label>
        <@spring.formInput "users.password"/><br>
        <@spring.showErrors "<br>"/><br>

        <label> First name </label>
        <@spring.formInput "users.firstName"/><br>
        <@spring.showErrors "<br>"/><br>

        <label> Last name </label>
        <@spring.formInput "users.lastName"/><br>
        <@spring.showErrors "<br>"/><br>

        <button type="submit">Add</button>

    </form>
    </div>

</div>
</@p.pages>