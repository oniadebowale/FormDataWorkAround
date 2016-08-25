<%-- 
    Document   : index
    Created on : Jun 17, 2015, 5:51:29 PM
    Author     : Programmer's Diary
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            String rootPath = request.getContextPath();
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Programmer's Diary Example</title>
        <script type="text/javascript">
          var G_VAR_CONTEXT_PATH = '<%=rootPath%>';
        </script>

    </head>
    <body>
        <h1>Hello World!</h1>
        <form id="pf_form" method="post" enctype="multipart/form-data" >
            <input type="file" name="pdffile" />  
            <input type="submit" id="J_uploadbtn"/>		
        </form>
        <script src="js/lib/jquery/jquery.min.js"></script>
        <script src="js/app.js"></script>
    </body>
</html>
