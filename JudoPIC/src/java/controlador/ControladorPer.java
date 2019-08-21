package controlador;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelo.Persona;
import modelo.TestPedagogico;
import modeloDao.PersonaDAO;
import modeloDao.TestPedagogicoDAO;

@MultipartConfig
public class ControladorPer extends HttpServlet {

    Persona per = new Persona();
    PersonaDAO pdao = new PersonaDAO();
    TestPedagogico test = new TestPedagogico();
    TestPedagogicoDAO tdao = new TestPedagogicoDAO();
    int idp;
    int idt;

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
                    List<Persona> lista = pdao.listarPer();
                    request.setAttribute("personas", lista);
                    request.getRequestDispatcher("personaListar.jsp").forward(request, response);
                    break;
                case "Agregar":
                    String usuario = request.getParameter("txtUsuario");
                    String clave = request.getParameter("txtClave");
                    Part part = request.getPart("txtFoto");
                    InputStream foto = part.getInputStream();
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
                    request.setAttribute("Mensaje", "Usuario agregado con exito");
                    request.getRequestDispatcher("ControladorPer?menu=Persona&accion=Enviar").forward(request, response);
                    break;
                case "Enviar":
                    String usuarioC = request.getParameter("txtUsuario");
                    per.setUsuario(usuarioC);
                    pdao.enviarCorreo(per.getUsuario());
                    request.getRequestDispatcher("personaNueva.jsp").forward(request, response);
                    break;
                case "Editar":
                    idp = Integer.parseInt(request.getParameter("id"));
                    Persona p = pdao.listId(idp);
                    request.setAttribute("peredit", p);
                    request.getRequestDispatcher("personaEditar.jsp").forward(request, response);
                    break;
                case "Actualizar":
                    String usuarioA = request.getParameter("txtUsuario");
                    String claveA = request.getParameter("txtClave");
                    Part partA = request.getPart("txtFoto");
                    InputStream fotoA = partA.getInputStream();
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
                    request.setAttribute("Mensaje", "Usuario actualizado con exito");
                    request.getRequestDispatcher("ControladorPer?menu=Persona&accion=EnviarActualizado").forward(request, response);
                    break;
                case "EnviarActualizado":
                    String usuarioCA = request.getParameter("txtUsuario");
                    per.setUsuario(usuarioCA);
                    pdao.enviarCorreo(per.getUsuario());
                    request.getRequestDispatcher("ControladorPer?menu=Persona&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    idp = Integer.parseInt(request.getParameter("id"));
                    pdao.eliminarPer(idp);
                    request.setAttribute("Mensaje", "Usuario eliminado con exito");
                    request.getRequestDispatcher("ControladorPer?menu=Persona&accion=Listar").forward(request, response);
                    break;
                case "Buscar":
                    String dato = request.getParameter("txtBuscar");
                    List<Persona> datos = pdao.buscarPer(dato);
                    request.setAttribute("personas", datos);
                    request.getRequestDispatcher("personaListar.jsp").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }

            request.getRequestDispatcher("principal.jsp").forward(request, response);
        }

        if (menu.equals("Test")) {
            switch (accion) {
                case "Listar":
                    List lista = tdao.listarTest();
                    request.setAttribute("tests", lista);
                    request.getRequestDispatcher("testListar.jsp").forward(request, response);
                    break;
                case "Agregar":
                    int idpersona = Integer.parseInt(request.getParameter("txtIdpersona"));
                    String fecha = request.getParameter("txtFecha");
                    int barras = Integer.parseInt(request.getParameter("txtBarras"));
                    int paralelas = Integer.parseInt(request.getParameter("txtParalelas"));
                    int cabos = Integer.parseInt(request.getParameter("txtCabos"));
                    int pecho = Integer.parseInt(request.getParameter("txtPecho"));
                    int abdomen = Integer.parseInt(request.getParameter("txtAbdomen"));
                    int cunclilla = Integer.parseInt(request.getParameter("txtCunclilla"));
                    double prom = Double.parseDouble(request.getParameter("txtProm"));
                    double halon = Double.parseDouble(request.getParameter("txtHalon"));
                    double sentadilla = Double.parseDouble(request.getParameter("txtSentadilla"));
                    int ushikomi = Integer.parseInt(request.getParameter("txtUshikomi"));
                    int nagekomi60 = Integer.parseInt(request.getParameter("txtNagekomi60"));
                    int nagekomi30 = Integer.parseInt(request.getParameter("txtNagekomi30"));
                    double pique30 = Double.parseDouble(request.getParameter("txtPique30"));
                    double pique50 = Double.parseDouble(request.getParameter("txtPique50"));
                    double pique100 = Double.parseDouble(request.getParameter("txtPique100"));

                    test.setIdpersona(idpersona);
                    test.setFecha(fecha);
                    test.setBarras(barras);
                    test.setParalelas(paralelas);
                    test.setCabos(cabos);
                    test.setPecho(pecho);
                    test.setAbdomen(abdomen);
                    test.setCunclilla(cunclilla);
                    test.setProm(prom);
                    test.setHalon(halon);
                    test.setSentadilla(sentadilla);
                    test.setUshikomi(ushikomi);
                    test.setNagekomi60(nagekomi60);
                    test.setNagekomi30(nagekomi30);
                    test.setPique30(pique30);
                    test.setPique50(pique50);
                    test.setPique100(pique100);
                    tdao.agregarTest(test);
                    request.getRequestDispatcher("testNuevo.jsp").forward(request, response);
                    break;
                case "Editar":
                    idt = Integer.parseInt(request.getParameter("id"));
                    TestPedagogico t = tdao.listId(idt);
                    request.setAttribute("testedit", t);
                    request.getRequestDispatcher("testEditar.jsp").forward(request, response);
                    break;
                case "Actualizar":
                    int idpersonaA = Integer.parseInt(request.getParameter("txtIdpersona"));
                    String fechaA = request.getParameter("txtFecha");
                    int barrasA = Integer.parseInt(request.getParameter("txtBarras"));
                    int paralelasA = Integer.parseInt(request.getParameter("txtParalelas"));
                    int cabosA = Integer.parseInt(request.getParameter("txtCabos"));
                    int pechoA = Integer.parseInt(request.getParameter("txtPecho"));
                    int abdomenA = Integer.parseInt(request.getParameter("txtAbdomen"));
                    int cunclillaA = Integer.parseInt(request.getParameter("txtCunclilla"));
                    double promA = Double.parseDouble(request.getParameter("txtProm"));
                    double halonA = Double.parseDouble(request.getParameter("txtHalon"));
                    double sentadillaA = Double.parseDouble(request.getParameter("txtSentadilla"));
                    int ushikomiA = Integer.parseInt(request.getParameter("txtUshikomi"));
                    int nagekomi60A = Integer.parseInt(request.getParameter("txtNagekomi60"));
                    int nagekomi30A = Integer.parseInt(request.getParameter("txtNagekomi30"));
                    double pique30A = Double.parseDouble(request.getParameter("txtPique30"));
                    double pique50A = Double.parseDouble(request.getParameter("txtPique50"));
                    double pique100A = Double.parseDouble(request.getParameter("txtPique100"));
                    test.setIdpersona(idpersonaA);
                    test.setFecha(fechaA);
                    test.setBarras(barrasA);
                    test.setParalelas(paralelasA);
                    test.setCabos(cabosA);
                    test.setPecho(pechoA);
                    test.setAbdomen(abdomenA);
                    test.setCunclilla(cunclillaA);
                    test.setProm(promA);
                    test.setHalon(halonA);
                    test.setSentadilla(sentadillaA);
                    test.setUshikomi(ushikomiA);
                    test.setNagekomi60(nagekomi60A);
                    test.setNagekomi30(nagekomi30A);
                    test.setPique30(pique30A);
                    test.setPique50(pique50A);
                    test.setPique100(pique100A);
                    test.setIdtest(idt);
                    tdao.actualizarTest(test);
                    request.getRequestDispatcher("ControladorPer?menu=Test&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    idt = Integer.parseInt(request.getParameter("id"));
                    tdao.eliminarTest(idt);
                    request.getRequestDispatcher("ControladorPer?menu=Test&accion=Listar").forward(request, response);
                    break;
                case "Buscar":
                    String dato = request.getParameter("txtBuscar");
                    List datos = tdao.buscarTest(dato);
                    request.setAttribute("tests", datos);
                    request.getRequestDispatcher("testListar.jsp").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }

            request.getRequestDispatcher("principal.jsp").forward(request, response);
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
