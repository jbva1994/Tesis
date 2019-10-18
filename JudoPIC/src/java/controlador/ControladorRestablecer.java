/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import config.Conexion;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Persona;
import modeloDao.LoginDAO;
import modeloDao.PersonaDAO;

@WebServlet(name = "ControladorRestablecer", urlPatterns = {"/ControladorRestablecer"})
public class ControladorRestablecer extends HttpServlet {

    Persona per = new Persona();
    PersonaDAO pdao = new PersonaDAO();
    Conexion cn = new Conexion();
    Connection con = cn.getConnection();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String accion = request.getParameter("accion");
        if (accion.equals("Confirmar")) {
            String usuario = request.getParameter("txtUsuario");
            String clave = request.getParameter("txtClave");
            String claveE = pdao.Encriptar(clave);
            per.setClave(claveE);
            per.setUsuario(usuario);
            pdao.restablecer(per);

            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
