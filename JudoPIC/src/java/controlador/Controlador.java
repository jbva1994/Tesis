
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Persona;
import modeloDao.LoginDAO;


public class Controlador extends HttpServlet {
    LoginDAO dao=new LoginDAO();
    Persona per=new Persona();
    int r;
    
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controladorPersona</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controladorPersona at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        //processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
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
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
