<%-- 
    Document   : employ2
    Created on : 02/07/2019, 13:21:53
    Author     : 2016122760350
--%>

<%@page import="model.domain.Titulo"%>
<%@page import="java.util.List"%>
<%@page import="model.application.APLCadastrarTitulo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
     
<%  
    
String spageid=request.getParameter("page");
int pageid=1;
/*
if(spageid == "next"){
  pageid++;    
}else pageid--;  
*/
int total=2;  /*
if(pageid==1){}  
else{  
    pageid=pageid-1;  
    pageid=pageid*total+1;  
}  */
List<Titulo> list= APLCadastrarTitulo.listaPaginacao(Integer.parseInt(spageid), total);   
 


  
out.print("<h1>Pagina : "+spageid+ pageid+"</h1>");  
out.print("<table border='1' cellpadding='4' width='60%'>");  
out.print("<tr><th>Id</th><th>Name</th><th>Salary</th>");  
for(Titulo e:list){  
    out.print("<tr><td>"+e.getId()+"</td><td>"+e.getNome()+"</td><td>"+e.getAno()+"</td></tr>");  
}  
out.print("</table>");  
%>  
<a href="view.jsp?page=1">1</a>  
<a href="view.jsp?page=2">2</a>  
<a href="view.jsp?page=3">3</a> 
    </body>
</html>
