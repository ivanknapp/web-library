<%@page import="beans.books.BookList"%>
<%@page import="test.TestConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Library::Entres</title>
        <link rel="stylesheet" type="text/css" href="../Library/css/style_index.css">
    </head>
    
    <body>
        
        <div class="main">
            <div class="content">    
                <p class="title"><img src="../Library/images/indexlogo.jpg" width="170" height="80"</p>
                <p class="title">Library</p>
                <p class="text">Welcome to online library, where you can do everything you want</p>
                <p class="text">Project in development by indian developers</p>
            </div>

            <div class="login_div">
                <p class="title">Login: </p>
                <form class="login_form" name="username" action="../Library/pages/main.jsp" method="POST" id="userId">
                    Name: <input type="text" name="username" value="" size="20"/>
                    <input class="button" type="submit" value="Войти" />
                </form>
            </div>
            <div class="footer">
                dev by ivan knapp, 2018
            </div>
        </div>
    </body>
</html>
