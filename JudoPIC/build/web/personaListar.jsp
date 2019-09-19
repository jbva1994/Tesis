<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-dark"> 

            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">

                    <div class="nav-item " >
                        <a style="margin-left: 10px; border: none ;color: white" href="principal.jsp" class="nav-link">Home</a>                      
                    </div>

                    <div class="dropdown nav-item">      
                        <a style="margin-left: 10px; border: none ;color: white" href="" class="nav-link dropdown-toggle" data-toggle="dropdown">Usuarios</a>
                        <div class="dropdown-menu text-center">
                            <a class="dropdown-item" href="ControladorPer?menu=Persona&accion=Listar">Listar Usuarios</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="personaNueva.jsp">Nuevo Usuario</a>
                        </div>
                    </div>
                    <div class="dropdown nav-item">      
                        <a style="margin-left: 10px; border: none ;color: white" href="" class="nav-link dropdown-toggle" data-toggle="dropdown">Test Pedagogico</a>
                        <div class="dropdown-menu text-center">
                            <a class="dropdown-item" href="ControladorPer?menu=Test&accion=Listar">Listar Tests</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="testNuevo.jsp">Nuevo Test</a>
                        </div>
                    </div>
                </ul>
            </div>
            <div class="dropdown nav-item justify-content-end">      
                <a style="color: white" href="" class="nav-link dropdown-toggle" data-toggle="dropdown">Cerrar Sesion</a>
                <div class="dropdown-menu text-center">

                    <a><img src="img/user.png" height="60" width="60"/></a><br>                
                    <a>Judo Pichincha</a>
                    <a>Usuario:</a><br>
                    <a>${applicationScope.usuario}</a>
                    <div class="dropdown-divider"></div>
                    <form accion="Controlador" method="POST">
                        <a name="accion" value="Salir" href="login.jsp" class="dropdown-item">Salir</a>
                    </form>
                </div>
            </div>

        </nav>
        <div class="col-sm-14">
            <div class="card">
                <div class="card-body">
                    <div class="navbar container mb-2">
                        <form class="form-inline" action="ControladorPer?menu=Persona" method="POST">
                            <input type="text" name="txtBuscar" class="form-control" placeholder="Cualquier campo de la tabla" >
                            <input type="submit" name="accion" value="Buscar"  class="btn btn-outline-success">
                        </form>
                    </div> 
                </div>
            </div>
        </div>

        <div class="d-flex " style="overflow-x:auto;">

            <div class="col-sm-14">
                <div class="card">
                    <div class="card-body">
                        <table class="table table-sm table-bordered ">
                            <thead>
                                <tr align="center" valign="middle">
                                    <th colspan="12">LISTA DE ENTRENADORES Y DEPORTISTAS DE LA ASOCIACION DE JUDO PICHINCHA</th>
                                </tr>
                                <tr align="center" valign="middle">
                                    <th>USUARIO</th>
                                    <th>FOTO</th>
                                    <th>CEDULA</th>
                                    <th>NOMBRE</th>
                                    <th>APELLIDO</th>
                                    <th>FECHA DE NACIMIENTO</th>
                                    <th>TIPO</th>
                                    <th>GRADO</th>
                                    <th>CATEGORIA</th>
                                    <th>SEXO</th>
                                    <th>PESO</th>                                   
                                    <th>OPCIONES</th>
                                </tr>
                            </thead>
                            <tbody> 

                                <c:forEach var="per" items="${personas}">
                                    <tr>
                                        <td>${per.getUsuario()}</td>
                                        <td><img src="ControlalorImg?id=${per.getId()}" width="150" heigth="130"></td>
                                        <td>${per.getCedula()}</td>
                                        <td>${per.getNombre()}</td>
                                        <td>${per.getApellido()}</td>
                                        <td>${per.getFechaNacimiento()}</td>
                                        <td>${per.getTipo()}</td>
                                        <td>${per.getGrado()}</td>
                                        <td>${per.getCategoria()}</td>
                                        <td>${per.getSexo()}</td>
                                        <td>${per.getPeso()}</td>
                                        <td>
                                            <a class="btn btn-warning" href="ControladorPer?menu=Persona&accion=Editar&id=${per.getId()}">Editar  </a>
                                            <a class="btn btn-danger" href="ControladorPer?menu=Persona&accion=Eliminar&id=${per.getId()}">Eliminar</a>
                                        </td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

        </div> 





        <div class=" footer card-body bg-dark text-white "  >
            <footer class="card-title ">Copyright © 2019 Universidad Central del Ecuador. Todos los derechos reservados.
                Quito-Ecuador Autores: José Andrés Naranjo Samaniego, Joel Bernardo Vargas Arcos.</footer>         
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>
</html>
