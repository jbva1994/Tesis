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
                            <a class="dropdown-item btn btn-outline-light" href="ControladorPer?menu=Persona&accion=Listar">Listar Usuarios</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item btn btn-outline-light" href="personaNueva.jsp">Nuevo Usuario</a>
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


        <div class="d-flex "> 
            <div class="col-sm-12">
                <div class="card">
                    <div class="card-body">
                        <legend align="top">FORMULARIO DE INGRESO DE TEST PEGAGOGICOS</legend>
                        <form class="was-validated" action="ControladorPer?menu=Test" method="POST">
                            <legend>Datos de Test</legend>
                            <div class="form-row">
                                <div class="col-md-3 mb-2">
                                    <label for="validationTooltip01">ID Persona</label>
                                    <input type="text" value="${testedit.getIdpersona()}" name="txtIdpersona" class="form-control" id="validationTooltip01" required>                                   
                                    <div class="invalid-feedback">
                                        Por favor rellene este campo!
                                    </div>
                                </div>
                                <div class="col-md-3 mb-2">
                                    <label for="validationTooltip02">Fecha</label>
                                    <input type="text" value="${testedit.getFecha()}" placeholder="Ejemplo: YYYY-MM-DD" name="txtFecha" class="form-control" id="validationTooltip02" required>
                                    <div class="invalid-feedback">
                                        Por favor rellene este campo!
                                    </div>
                                </div>               
                            </div>
                            <legend>Resistencia a la Fuerza:</legend>
                            <div class="form-row">
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip03">Barras</label>
                                    <input type="text" value="${testedit.getBarras()}" name="txtBarras" class="form-control" id="validationTooltip03" >
                                    <div class="invalid-feedback">
                                        Bien!
                                    </div>
                                </div>
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip04">Paralelas</label>
                                    <input type="text" value="${testedit.getParalelas()}" name="txtParalelas" class="form-control" id="validationTooltip04" >
                                    <div class="invalid-feedback">
                                        Bien!
                                    </div>
                                </div>
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip05">Cabos</label>
                                    <input type="text" value="${testedit.getCabos()}" name="txtCabos" class="form-control" id="validationTooltip05" >
                                    <div class="invalid-feedback">
                                        Bien!
                                    </div>
                                </div> 

                            </div>
                            <legend>Fuerza Explosiva</legend>
                            <div class="form-row">
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip06">Pecho</label>
                                    <input type="text" value="${testedit.getPecho()}" name="txtPecho" class="form-control" id="validationTooltip06" >
                                    <div class="invalid-feedback">
                                        Bien!
                                    </div>
                                </div>                 
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip07">Abdomen</label>
                                    <input type="text" value="${testedit.getAbdomen()}" name="txtAbdomen" class="form-control" id="validationTooltip07" >
                                    <div class="invalid-feedback">
                                        Bien!
                                    </div>
                                </div>
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip8">Cunclilla</label>
                                    <input type="text" value="${testedit.getCunclilla()}" name="txtCunclilla"  class="form-control" id="validationTooltip08" >
                                    <div class="invalid-feedback">
                                        Bien!
                                    </div>
                                </div>
                            </div>
                            <legend>Fuerza Maxima</legend>
                            <div class="form-row">
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip09">Prom</label>
                                    <input type="text" value="${testedit.getProm()}" name="txtProm" class="form-control" id="validationTooltip09" >
                                    <div class="invalid-feedback">
                                        Bien!
                                    </div>
                                </div>                 
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip10">Halon</label>
                                    <input type="text" value="${testedit.getHalon()}" name="txtHalon" class="form-control" id="validationTooltip10" >
                                    <div class="invalid-feedback">
                                        Bien!
                                    </div>
                                </div>
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip11">Sentadilla</label>
                                    <input type="text" value="${testedit.getSentadilla()}" name="txtSentadilla"  class="form-control" id="validationTooltip11" >
                                    <div class="invalid-feedback">
                                        Bien!
                                    </div>
                                </div>
                            </div>

                            <legend>Resistencia a la Rapidez Especial</legend>
                            <div class="form-row">
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip12">Ushikomi</label>
                                    <input type="text" value="${testedit.getUshikomi()}" name="txtUshikomi" class="form-control" id="validationTooltip12" >
                                    <div class="invalid-feedback">
                                        Bien!
                                    </div>
                                </div>                 
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip13">Nagekomi 60s</label>
                                    <input type="text" value="${testedit.getNagekomi60()}" name="txtNagekomi60" class="form-control" id="validationTooltip13" >
                                    <div class="invalid-feedback">
                                        Bien!
                                    </div>
                                </div>
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip14">Nagekomi 30s</label>
                                    <input type="text" value="${testedit.getNagekomi30()}" name="txtNagekomi30"  class="form-control" id="validationTooltip14" >
                                    <div class="invalid-feedback">
                                        Bien!
                                    </div>
                                </div>
                            </div>

                            <legend>Velocidad de Traslacion</legend>
                            <div class="form-row">
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip15">Pique 30m</label>
                                    <input type="text" value="${testedit.getPique30()}" name="txtPique30" class="form-control" id="validationTooltip15" >
                                    <div class="invalid-feedback">
                                        Bien!
                                    </div>
                                </div>                 
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip16">Pique 50m</label>
                                    <input type="text" value="${testedit.getPique50()}" name="txtPique50" class="form-control" id="validationTooltip16" >
                                    <div class="invalid-feedback">
                                        Bien!
                                    </div>
                                </div>
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip17">Pique 100m</label>
                                    <input type="text" value="${testedit.getPique100()}" name="txtPique100"  class="form-control" id="validationTooltip17" >
                                    <div class="invalid-feedback">
                                        Bien!
                                    </div>
                                </div>
                            </div>

                            
                            <input type="submit" name="accion" value="Actualizar" class="btn btn-success">
                        </form>

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

