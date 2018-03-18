
<%@page import="enums.SearchType"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.books.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@include file="../WEB-INF/jspf/left_menu.jspf" %>

<%@include file="../WEB-INF/jspf/letter.jspf" %>

<jsp:useBean id="bookList" class="beans.books.BookList" scope="page"></jsp:useBean>

    
<%
    request.setCharacterEncoding("UTF-8");
    ArrayList<Book> list = null;
    
    try{

        if(request.getParameter("genre_id") != null){
            int genre_id = Integer.parseInt(request.getParameter("genre_id"));
            list = bookList.getBooksByGenre(genre_id);
        }else if(request.getParameter("letter") != null){
            String letter = request.getParameter("letter");
            list = bookList.getBooksByLetter(letter);
        }else if(request.getParameter("search_string") != null){
            String searchStr = request.getParameter("search_string");
            SearchType searchType = SearchType.AUTHOR;

            if(request.getParameter("search_option").equals("book title")){
                searchType = SearchType.TITLE;
            }
            if(searchStr != null && !searchStr.trim().equals("")){
                list = bookList.getBookBySearch(searchStr, searchType);
            }
        }  
    }catch(Exception ex){
        ex.printStackTrace();
        System.out.println("Ошибка в book.jsp возврат листа");
        
    }
    
    
%>



    <div class="book_list">
        <h5 style="text-align: left; margin-top:20px;">Найдено книг: <%=list.size() %> </h5>
        
        <%
            session.setAttribute("list", list);
            for (Book book : list){
        %>
        <div class="book_info">
            <div class="book_title">
                <p> <a href="<%=request.getContextPath()%>/PdfContent?index=<%=list.indexOf(book)%>"><%=book.getName() %></a></p>
            </div>
            <div class="book_image">
                <a href="<%=request.getContextPath()%>/PdfContent?index=<%=list.indexOf(book)%>">
                    <img src="<%=request.getContextPath()%>/Image?index=<%=list.indexOf(book) %>" height="250" width="190" alt="Обложка"/>
                </a>
            </div>
            <div class="book_details">
                <br><strong>ISBN:</strong><%=book.getIsbn() %>
                <br><strong>Publisher:</strong><%=book.getPublisher() %>
                <br><strong>Page count: </strong><%=book.getPageCount() %>
                <br><strong>Year:</strong><%=book.getPublishDate() %>
                <br><strong>Author:</strong><%=book.getAuthor() %>
                <p style="margin:10px;"> <a href="<%=request.getContextPath()%>/PdfContent?index=<%=list.indexOf(book)%>">Читать</a></p>
            </div>
        </div>
                
        <% } %>  
        
    </div>