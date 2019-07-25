package controlador;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Persona;
import modeloDao.PersonaDAO;

public class ControladorPer extends HttpServlet {

    Persona per = new Persona();
    PersonaDAO pdao = new PersonaDAO();
    int idp;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        if (menu.equals("Principal")) {
            request.getRequestDispatcher("principal.jsp").forward(request, response);
        }
        if (menu.equals("Persona")) {
            switch (accion) {
                case "Listar":
                    List lista = pdao.listarPer();
                    request.setAttribute("personas", lista);
                    break;
                case "Agregar":
                    String usuario = request.getParameter("txtUsuario");
                    String clave = request.getParameter("txtClave");
                    String foto = request.getParameter("txtFoto");
                    String cedula = request.getParameter("txtCedula");
                    String nombre = request.getParameter("txtNombre");
                    String apellido = request.getParameter("txtApellido");
                    String fechaNacimiento = request.getParameter("txtFechaNacimiento");
                    String tipo = request.getParameter("txtTipo");
                    String grado = request.getParameter("txtGrado");
                    String categoria = request.getParameter("txtCategoria");
                    String sexo = request.getParameter("txtSexo");
                    String peso = request.getParameter("txtPeso");
                    per.setUsuario(usuario);
                    per.setClave(clave);
                    per.setFoto(foto);
                    per.setCedula(cedula);
                    per.setNombre(nombre);
                    per.setApellido(apellido);
                    per.setFechaNacimiento(fechaNacimiento);
                    per.setTipo(tipo);
                    per.setGrado(grado);
                    per.setCategoria(categoria);
                    per.setSexo(sexo);
                    per.setPeso(peso);
                    pdao.agregarPer(per);
                    request.getRequestDispatcher("ControladorPer?menu=Persona&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    idp = Integer.parseInt(request.getParameter("id"));
                    Persona p = pdao.listId(idp);
                    request.setAttribute("peredit", p);
                    request.getRequestDispatcher("ControladorPer?menu=Persona&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String usuarioA = request.getParameter("txtUsuario");
                    String claveA = request.getParameter("txtClave");
                    String fotoA = request.getParameter("txtFoto");
                    String cedulaA = request.getParameter("txtCedula");
                    String nombreA = request.getParameter("txtNombre");
                    String apellidoA = request.getParameter("txtApellido");
                    String fechaNacimientoA = request.getParameter("txtFechaNacimiento");
                    String tipoA = request.getParameter("txtTipo");
                    String gradoA = request.getParameter("txtGrado");
                    String categoriaA = request.getParameter("txtCategoria");
                    String sexoA = request.getParameter("txtSexo");
                    String pesoA = request.getParameter("txtPeso");
                    per.setUsuario(usuarioA);
                    per.setClave(claveA);
                    per.setFoto(fotoA);
                    per.setCedula(cedulaA);
                    per.setNombre(nombreA);
                    per.setApellido(apellidoA);
                    per.setFechaNacimiento(fechaNacimientoA);
                    per.setTipo(tipoA);
                    per.setGrado(gradoA);
                    per.setCategoria(categoriaA);
                    per.setSexo(sexoA);
                    per.setPeso(pesoA);
                    per.setId(idp);
                    pdao.actualizarPer(per);
                    request.getRequestDispatcher("ControladorPer?menu=Persona&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    idp = Integer.parseInt(request.getParameter("id"));
                    pdao.eliminarPer(idp);
                    request.getRequestDispatcher("ControladorPer?menu=Persona&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }

            request.getRequestDispatcher("persona.jsp").forward(request, response);
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
        /*String acceso = "";
        String action = request.getParameter("accion");
        if (action.equalsIgnoreCase("listar")) {
            acceso = "vistas/listarPersona.jsp";
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);*/
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
