<#import "templ/templ.ftl" as p>
<@p.pages>

    <h2> Auth </h2>
<div class="row">

    <div class="col-6">
    <form action="/login" method="post">

        <label for="username">Username</label><br>
        <input type="text" name="username" id="username" placeholder="user"><br>

        <label for="password">Password</label><br>
        <input type="password" name="password" id="password" placeholder="user"><br>

        <button class="btn btn-success" type="submit">Log in</button>
    </form>
    </div>
</div>

    <a href="/registration"> Реєстрація </a>

</@p.pages>