package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class principal_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <nav class=\"navbar navbar-expand-lg navbar-light bg-dark\"> \n");
      out.write("\n");
      out.write("            <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n");
      out.write("                <ul class=\"navbar-nav\">\n");
      out.write("\n");
      out.write("                    <div class=\"nav-item \" >\n");
      out.write("                            <a style=\"margin-left: 10px; border: none ;color: white\" href=\"principal.jsp\" class=\"nav-link\">Home</a>                      \n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"dropdown nav-item\">      \n");
      out.write("                        <a style=\"margin-left: 10px; border: none ;color: white\" href=\"\" class=\"nav-link dropdown-toggle\" data-toggle=\"dropdown\">Usuarios</a>\n");
      out.write("                        <div class=\"dropdown-menu text-center\">\n");
      out.write("                            <a class=\"dropdown-item btn btn-outline-light\" href=\"ControladorPer?menu=Persona&accion=Listar\">Listar Usuarios</a>\n");
      out.write("                            <div class=\"dropdown-divider\"></div>\n");
      out.write("                            <a class=\"dropdown-item btn btn-outline-light\" href=\"personaNueva.jsp\">Nuevo Usuario</a>\n");
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
      out.write("                    \n");
      out.write("                    \n");
      out.write("\n");
      out.write("\n");
      out.write("        <div class=\"embed-responsives\" style=\"height: 517px;\">\n");
      out.write("            <iframe class=\"embed-responsive-item\" name=\"myFrame\"  style=\"height: 100%; width: 100%; border:none\">\n");
      out.write("                \n");
      out.write("            </iframe>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <footer>\n");
      out.write("            <div class=\" footer card-body bg-dark text-white \"  >\n");
      out.write("                <footer class=\"card-title \">Copyright © 2019 Universidad Central del Ecuador. Todos los derechos reservados.\n");
      out.write("                    Quito-Ecuador Autores: José Andrés Naranjo Samaniego, Joel Bernardo Vargas Arcos.</footer>         \n");
      out.write("            </div>\n");
      out.write("        </footer>\n");
      out.write("\n");
      out.write("        <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\n");
      out.write("        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js\" integrity=\"sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49\" crossorigin=\"anonymous\"></script>\n");
      out.write("        <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js\" integrity=\"sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy\" crossorigin=\"anonymous\"></script>\n");
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
