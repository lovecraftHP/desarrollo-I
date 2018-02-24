/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.web;

import com.demo.db.DbConnect;
import com.demo.model.Contacto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author docente
 */
@WebServlet(name = "ContactoServlet", urlPatterns = {"/contServ/*"})
public class ContactoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            int id = new Integer(request.getParameter("id"));
            String nombres = request.getParameter("nombres");
            String apellidos = request.getParameter("apellidos");
            String correo = request.getParameter("correo");
            Contacto contacto=new Contacto(id, nombres, apellidos, correo);
            DbConnect.save(contacto);
            List<Contacto> list = DbConnect.list();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Resultado</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<td>Id</td>");
            out.println("<td>Nombres</td>");
            out.println("<td>Apellidos</td>");
            out.println("<td>Correo</td>");
            out.println("</tr>");
            for (Contacto contacto1 : list) {
                out.println("<tr>");
            out.println("<td>"+contacto1.getId()+"</td>");
            out.println("<td>"+contacto1.getNombres()+"</td>");
            out.println("<td>"+contacto1.getApellidos()+"</td>");
            out.println("<td>"+contacto1.getCorreo()+"</td>");
            out.println("<td><a href='actualizar.jsp?id="+contacto1.getId()+"'><button>Editar</button></a></td>"
                    + "");
            out.println("</tr>");
            }
             out.println("</table>");
             out.println("</body>");
            out.println("</html>");
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ContactoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
