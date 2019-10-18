<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">


        <title>Listar Test</title>
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
                            <a class="dropdown-item btn btn-outline-light" href="ControladorPer?menu=Persona&accion=Listar">Listar Usuarios</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item btn btn-outline-light" href="personaNueva.jsp">Nuevo Usuario</a>
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
                        Ingrese la Cédula del Deportista previamente registrado para listar los Test.
                    </div>
                    <div class="navbar container mb-2">
                        
                        <form class="form-inline" action="ControladorPer?menu=Test" method="POST">
                              
                            <input type="text" name="txtBuscar" class="form-control" placeholder="Cédula">
                            
                            <input type="submit" name="accion" value="Buscar" class="btn btn-outline-success">
                            
                        </form>
                        
                    </div>
                    
                </div>
                
            </div>
        </div>

        <div class="d-flex " style="overflow-x:auto;"> 
            <div class="col-sm-24">
                <div class="card">
                    <div class="card-body">
                        <table class="table table-sm table-bordered">
                            <thead>
                                <tr align="center" valign="middle">
                                    <th colspan="18">TEST PEDAGÓGICO JUDO DE PICHINCHA</th>
                                </tr>
                                <tr align="center" valign="middle">                                   
                                    <th rowspan="2">CÉDULA</th>
                                    <th rowspan="2">FECHA</th>
                                    <th colspan="3">RESISTENCIA A LA FUERZA</th>
                                    <th colspan="3">FUERZA EXPLOSIVA</th>
                                    <th colspan="3">FUERZA MÁXIMA</th>
                                    <th colspan="3">RESISTENCIA A LA RAPIDEZ ESPECIAL</th>                               
                                    <th colspan="3">VELOCIDAD DE TRASLACIÓN</th>
                                    <th rowspan="2">OPCIONES</th>
                                </tr>
                                <tr align="center" valign="middle">                                    
                                    <th>BARRAS</th>
                                    <th>PARALELAS</th>
                                    <th>CABOS</th>
                                    <th>PECHO</th>
                                    <th>ABDOMEN</th>
                                    <th>CUNCLILLA</th>
                                    <th>PROM</th>
                                    <th>HALON</th>
                                    <th>SENTADILLA</th>
                                    <th>USHIKOMI</th>
                                    <th>NAGEKOMI 60s</th>                                   
                                    <th>NAGEKOMI 30s</th>
                                    <th>PIQUE 30m</th>
                                    <th>PIQUE 50m</th>
                                    <th>PIQUE 100m</th>
                                </tr>
                            </thead>
                            <tbody> 

                                <c:forEach var="test" items="${tests}">
                                    <tr>
                                        <td>${test.getCedula()}</td>
                                        <td>${test.getFecha()}</td>
                                        <td>${test.getBarras()}</td>
                                        <td>${test.getParalelas()}</td>
                                        <td>${test.getCabos()}</td>
                                        <td>${test.getPecho()}</td>
                                        <td>${test.getAbdomen()}</td>
                                        <td>${test.getCunclilla()}</td>
                                        <td>${test.getProm()}</td>
                                        <td>${test.getHalon()}</td>
                                        <td>${test.getSentadilla()}</td>
                                        <td>${test.getUshikomi()}</td>
                                        <td>${test.getNagekomi60()}</td>
                                        <td>${test.getNagekomi30()}</td>
                                        <td>${test.getPique30()}</td>
                                        <td>${test.getPique50()}</td>
                                        <td>${test.getPique100()}</td>
                                        <td>
                                            <a  title="Editar" href="ControladorPer?menu=Test&accion=Editar&id=${test.getIdtest()}"><i class="fas fa-pencil-alt fa-2x text-primary"></i></a>
                                            <a  title="Eliminar" href="ControladorPer?menu=Test&accion=Eliminar&id=${test.getIdtest()}"><i class="fas fa-trash-alt fa-2x red-text text-danger"></i></a>
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
