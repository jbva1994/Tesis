package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class personaNueva_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <nav class=\"navbar navbar-expand-lg navbar-light bg-dark\"> \n");
      out.write("\n");
      out.write("            <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n");
      out.write("                <ul class=\"navbar-nav\">\n");
      out.write("                    \n");
      out.write("                    <div class=\"nav-item \" >\n");
      out.write("                            <a style=\"margin-left: 10px; border: none ;color: white\" href=\"principal.jsp\" class=\"nav-link\">Home</a>                      \n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <div class=\"dropdown nav-item\">      \n");
      out.write("                        <a style=\"margin-left: 10px; border: none ;color: white\" href=\"\" class=\"nav-link dropdown-toggle\" data-toggle=\"dropdown\">Usuarios</a>\n");
      out.write("                        <div class=\"dropdown-menu text-center\">\n");
      out.write("                            <a class=\"dropdown-item\" href=\"ControladorPer?menu=Persona&accion=Listar\">Listar Usuarios</a>\n");
      out.write("                            <div class=\"dropdown-divider\"></div>\n");
      out.write("                            <a class=\"dropdown-item\" href=\"personaNueva.jsp\">Nuevo Usuario</a>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"dropdown nav-item\">      \n");
      out.write("                        <a style=\"margin-left: 10px; border: none ;color: white\" href=\"\" class=\"nav-link dropdown-toggle\" data-toggle=\"dropdown\">Test Pedagogico</a>\n");
      out.write("                        <div class=\"dropdown-menu text-center\">\n");
      out.write("                            <a class=\"dropdown-item\" href=\"ControladorPer?menu=Test&accion=Listar\">Listar Tests</a>\n");
      out.write("                            <div class=\"dropdown-divider\"></div>\n");
      out.write("                            <a class=\"dropdown-item\" href=\"testNuevo.jsp\">Nuevo Test</a>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"dropdown nav-item justify-content-end\">      \n");
      out.write("                <a style=\"color: white\" href=\"\" class=\"nav-link dropdown-toggle\" data-toggle=\"dropdown\">Cerrar Sesion</a>\n");
      out.write("                <div class=\"dropdown-menu text-center\">\n");
      out.write("\n");
      out.write("                    <a><img src=\"img/user.png\" height=\"60\" width=\"60\"/></a><br>                \n");
      out.write("                    <a>Judo Pichincha</a>\n");
      out.write("                    <a>Usuario:</a><br>\n");
      out.write("                    <a>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${usuario.getUsuario()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</a>\n");
      out.write("                    <div class=\"dropdown-divider\"></div>\n");
      out.write("                    <form accion=\"Controlador\" method=\"POST\">\n");
      out.write("                        <a name=\"accion\" value=\"Salir\" href=\"login.jsp\" class=\"dropdown-item\">Salir</a>\n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("        </nav>\n");
      out.write("\n");
      out.write("        <div class=\"d-flex \">           \n");
      out.write("            <div class=\"col-sm-3\">\n");
      out.write("                <div class=\"card\">\n");
      out.write("                    <div class=\"card-body\">\n");
      out.write("                        <form action=\"ControladorPer?menu=Persona\" method=\"POST\" enctype=\"multipart/form-data\">\n");
      out.write("\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label>Usuario</label>\n");
      out.write("                                <input type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${peredit.getUsuario()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" name=\"txtUsuario\" class=\"form-control\">\n");
      out.write("                            <div class=\"invalid-tooltip\">\n");
      out.write("        Please provide a valid state.\n");
      out.write("      </div>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label>Clave</label>\n");
      out.write("                                <input type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${peredit.getClave()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" name=\"txtClave\" class=\"form-control\">\n");
      out.write("                            </div>\n");
      out.write("\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label>Foto</label>\n");
      out.write("                                <input type=\"file\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${peredit.getFoto()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" name=\"txtFoto\" class=\"form-control\">\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label>Cedula</label>\n");
      out.write("                                <input type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${peredit.getCedula()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" name=\"txtCedula\" class=\"form-control\">\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label>Nombre</label>\n");
      out.write("                                <input type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${peredit.getNombre()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" name=\"txtNombre\" class=\"form-control\">\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label>Apellido</label>\n");
      out.write("                                <input type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${peredit.getApellido()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" name=\"txtApellido\" class=\"form-control\">\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label>Fecha de Nacimiento</label>\n");
      out.write("                                <input type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${peredit.getFechaNacimiento()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" name=\"txtFechaNacimiento\" placeholder=\"Ejemplo: YYYY-MM-DD\" class=\"form-control\">\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label>Tipo</label>\n");
      out.write("                                <input type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${peredit.getTipo()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" name=\"txtTipo\" class=\"form-control\">\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label>Grado</label>\n");
      out.write("                                <input type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${peredit.getGrado()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" name=\"txtGrado\" class=\"form-control\">\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label>Categoria</label>\n");
      out.write("                                <input type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${peredit.getCategoria()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" name=\"txtCategoria\" class=\"form-control\">\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label>Sexo</label>\n");
      out.write("                                <input type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${peredit.getSexo()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" name=\"txtSexo\" class=\"form-control\">\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label>Peso</label>\n");
      out.write("                                <input type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${peredit.getPeso()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" name=\"txtPeso\" class=\"form-control\">\n");
      out.write("                            </div>\n");
      out.write("\n");
      out.write("                            <input type=\"submit\" name=\"accion\" value=\"Agregar\" class=\"btn btn-primary\">\n");
      out.write("                            <input type=\"submit\" name=\"accion\" value=\"Actualizar\" class=\"btn btn-success\">\n");
      out.write("                        </form>\n");
      out.write("                    </div>                         \n");
      out.write("                </div>\n");
      out.write("            </div>                           \n");
      out.write("\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class=\" footer card-body bg-dark text-white \"  >\n");
      out.write("            <footer class=\"card-title \">Copyright © 2019 Universidad Central del Ecuador. Todos los derechos reservados.\n");
      out.write("                Quito-Ecuador Autores: José Andrés Naranjo Samaniego, Joel Bernardo Vargas Arcos.</footer>         \n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\n");
      out.write("        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\" integrity=\"sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1\" crossorigin=\"anonymous\"></script>\n");
      out.write("        <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\" integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\" crossorigin=\"anonymous\"></script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
