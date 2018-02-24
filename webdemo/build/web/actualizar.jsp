<%-- 
    Document   : actualizar
    Created on : 23/02/2018, 02:53:42 PM
    Author     : lovecraft
--%>
<%@page import ="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizar</title>
    </head>
    <body>
        <h3>Actualizar datos de usuario</h3>
        <%
            int id =new Integer(request.getParameter("id"));
            Statement query = null;
            ResultSet result = null;
            Connection con =null;
            String user="root";
            String password ="";
            String url = "jdbc:mysql://localhost:3306/demo";
            
            try{
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection(url, user, password);
                query = con.createStatement();
                result = query.executeQuery("select * from contactos where id ='"+id+"'");
            
                while(result.next()){
            
        %>
        <form action="">
        <table>
            <tr>
                <td>Id</td>
                <td><input type="text" name="id" value="<%=result.getInt(1)%>" readonly="readonly"></td>
            </tr>
            <tr>
                <td>Nombres</td>
                <td><input type="text" name="nombres" value="<%=result.getString(2)%>"/></td>
            </tr>
            <tr>
                <td>Apellidos</td>
                <td><input type="text" name="apellidos" value="<%=result.getString(3)%>"/></td>
            </tr>
            <tr>
                <td>Correo</td>
                <td><input type="email" name="correo"value="<%=result.getString(4)%>"/></td>
            </tr>
            <tr>
                
                <td colspan="2"><input type="submit" value="Guardar" name="guardar"/></td>
            </tr>
        </table>
        </form>
          <%
              }
            }catch(Exception e){}
            if (request.getParameter("guardar")!=null){
                id = new Integer(request.getParameter("id"));
                String nombre = request.getParameter("nombres");
                String apellido = request.getParameter("apellidos");
                String correo = request.getParameter("correo");
                
                query.executeUpdate("update contactos set nombres = '"+nombre+"',apellidos='"
                                    +apellido+"',correo='"+correo+"' where id='"+id+"'");
                request.getRequestDispatcher("./webdemo/contServ").forward(request, response);
            }
            
          %>
    </body>
</html>
