
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Persona;
import modeloDao.LoginDAO;
import modeloDao.PersonaDAO;


public class Controlador extends HttpServlet {
    LoginDAO dao=new LoginDAO();
    Persona per=new Persona();
    PersonaDAO pdao=new PersonaDAO();
    int r;
    
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String accion=request.getParameter("accion");
        if(accion.equals("Ingresar")){
            String usuario=request.getParameter("txtUser");
            String claveE=request.getParameter("txtClave");
            String clave = pdao.Encriptar(claveE);
            per.setUsuario(usuario);
            per.setClave(clave);
            dao.validar(per);
            r=dao.validar(per);
            if(r==1){
                
                HttpSession session = request.getSession();
                session.setAttribute("usuario",per);
                
                ServletContext aplicacion= request.getServletContext();
                aplicacion.setAttribute("usuario",per.getUsuario());
                
                request.getRequestDispatcher("ControladorPer?menu=Principal").forward(request, response);           
            }else{
                out.println("<script src='js/sweetalert2.js'></script>");
                out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
                out.println("<script>");
                out.println("$(document).ready(function(){");
                out.println("swal ( 'Usuario y/o Contraseña incorrectos' ,  ' ' ,  'error' );");
                out.println("});");
                out.println("</script>");
                
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.include(request, response);
                //request.getRequestDispatcher("login.jsp").forward(request, response);
            } 
        }else{
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
        /**response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        String accion=request.getParameter("accion");
        if(accion.equals("Ingresar")){
            String usuario=request.getParameter("txtUser");
            String clave=request.getParameter("txtClave");
            per.setUsuario(usuario);
            per.setClave(clave);
            dao.validar(per);
            r=dao.validar(per);
            if(r==1){
                ServletContext aplicacion= request.getServletContext();
                aplicacion.setAttribute("usuario",per.getUsuario());
                
                request.getRequestDispatcher("ControladorPer?menu=Principal").forward(request, response);           
            }else{
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } 
        }else{
                request.getRequestDispatcher("login.jsp").forward(request, response);
         }
        if(accion.equals("Salir")){
            session.invalidate();
            response.sendRedirect("login.jsp");
            //request.getRequestDispatcher("login.jsp").forward(request, response);
        }*/
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
