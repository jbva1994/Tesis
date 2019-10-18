<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <title>Listar Usuarios</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-dark"> 

            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">

                    <div class="nav-item " >
                        <a style="margin-left: 10px; border: none ;color: white" href="principal.jsp" class="nav-link"><i class="fas fa-layer-group "></i> Home</a>                      
                    </div>

                    <div class="dropdown nav-item">      
                        <a style="margin-left: 10px; border: none ;color: white" href="" class="nav-link dropdown-toggle" data-toggle="dropdown"><i class=" fas fa-users "></i> Usuarios</a>
                        <div class="dropdown-menu text-center">
                            <a class="dropdown-item" href="ControladorPer?menu=Persona&accion=Listar">Listar Usuarios</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="personaNueva.jsp">Nuevo Usuario</a>
                        </div>
                    </div>
                    <div class="dropdown nav-item">      
                        <a style="margin-left: 10px; border: none ;color: white" href="" class="nav-link dropdown-toggle" data-toggle="dropdown"><i class="far fa-id-badge "></i> Test Pedagógico</a>
                        <div class="dropdown-menu text-center">
                            <a class="dropdown-item" href="ControladorPer?menu=Test&accion=Listar">Listar Tests</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="testNuevo.jsp">Nuevo Test</a>
                        </div>
                    </div>
                </ul>
            </div>
            <div class="dropdown nav-item justify-content-end">      
                <a style="color: white" href="" class="nav-link dropdown-toggle" data-toggle="dropdown"><i class="fas fa-sign-in-alt "></i> Cerrar Sesión</a>
                <div class="dropdown-menu text-center">

                    <a><img src="img/user.png" height="60" width="60"/></a><br>                
                    <a>Judo Pichincha</a>
                    <a>${applicationScope.usuario}</a>
                    <div class="dropdown-divider"></div>
                    <a name="accion" value="Salir" href="ControladorSession" class="dropdown-item">Salir</a>
                </div>
            </div>

        </nav>
                    <div class="col-sm-14">
            <div class="card">
                <div class="card-body">
                    <div class="alert alert-warning alert-dismissible">
                        <a  class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        Ingrese cualquier registro de un Deportista o Entrenador para filtrar los datos en la tabla.
                    </div>
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
                                    <th colspan="12">LISTA DE ENTRENADORES Y DEPORTISTAS DE LA ASOCIACIÓN DE JUDO PICHINCHA</th>
                                </tr>
                                <tr align="center" valign="middle">
                                    <th>USUARIO</th>
                                    <th>FOTO</th>
                                    <th>CÉDULA</th>
                                    <th>NOMBRE</th>
                                    <th>APELLIDO</th>
                                    <th>FECHA DE NACIMIENTO</th>
                                    <th>TIPO</th>
                                    <th>GRADO</th>
                                    <th>CATEGORÍA</th>
                                    <th>SEXO</th>
                                    <th>PESO</th>                                   
                                    <th>OPCIONES</th>
                                </tr>
                            </thead>
                            <tbody> 

                                <c:forEach var="per" items="${personas}">
                                    <tr>
                                        <td>${per.getUsuario()}</td>
                                        <td><img src="ControladorImg?id=${per.getId()}" width="150" heigth="130"></td>
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
                                            <a title="Editar" href="ControladorPer?menu=Persona&accion=Editar&id=${per.getId()}"><i class="fas fa-user-edit fa-2x text-primary"></i></a>
                                            <a title="Eliminar" href="ControladorPer?menu=Persona&accion=Eliminar&id=${per.getId()}"><i class="fas fa-user-times fa-2x red-text text-danger"></i></a>

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
                Quito-Ecuador Autores:  <a href="https://www.facebook.com/jose.naranjo.71">José Naranjo </a> - josenaranjo.50@hotmail.com , <a href="https://www.facebook.com/jbva1994"> Joel Vargas </a> - joel-v1994@hotmail.com.</footer>         
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>
</html>
