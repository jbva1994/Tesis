<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <title>Editar Test</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-dark"> 

            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">

                    <div class="nav-item " >
                        <a style="margin-left: 10px; border: none ;color: white" href="principal.jsp" class="nav-link"></i> Home</a>                      
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


        <div class="d-flex "> 
            <div class="col-sm-12">
                <div class="card">
                    <div class="card-body">
                        <legend align="top">FORMULARIO DE INGRESO DE TEST PEGAGÓGICOS</legend>
                        <form class="was-validated" action="ControladorPer?menu=Test" method="POST">
                            <legend>Datos de Test</legend>
                            <div class="form-row">
                                <div class="col-md-3 mb-2">
                                    <label for="validationTooltip01">Cédula</label>
                                    <input type="text" value="${testedit.getCedula()}" name="txtCedula" class="form-control" id="validationTooltip01" pattern="^[0-9]{10}$"  oninvalid="setCustomValidity('Ingrese una cedula valida')" required>                                   
                                    <div class="invalid-feedback">
                                        Por favor rellene este campo!
                                    </div>
                                </div>         
                            </div>
                            <legend>Resistencia a la Fuerza:</legend>
                            <div class="form-row">
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip03">Barras</label>
                                    <input type="text" value="${testedit.getBarras()}" name="txtBarras" class="form-control" id="validationTooltip03" pattern="^([0-9]|[1-9][0-9]|100)$"  oninvalid="setCustomValidity('Ingrese un numero valido entre 0-100')">
                                    <div class="invalid-feedback">
                                        Ingrese un número entre 0-100!
                                    </div>
                                </div>
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip04">Paralelas</label>
                                    <input type="text" value="${testedit.getParalelas()}" name="txtParalelas" class="form-control" id="validationTooltip04" pattern="^([0-9]|[1-9][0-9]|100)$"  oninvalid="setCustomValidity('Ingrese un numero valido entre 0-100')">
                                    <div class="invalid-feedback">
                                        Ingrese un número entre 0-100!
                                    </div>
                                </div>
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip05">Cabos</label>
                                    <input type="text" value="${testedit.getCabos()}" name="txtCabos" class="form-control" id="validationTooltip05" pattern="^([0-9]|1[0-9]|20)$"  oninvalid="setCustomValidity('Ingrese un numero valido entre 0-20')">
                                    <div class="invalid-feedback">
                                        Ingrese un número entre 0-20!
                                    </div>
                                </div> 

                            </div>
                            <legend>Fuerza Explosiva</legend>
                            <div class="form-row">
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip06">Pecho</label>
                                    <input type="text" value="${testedit.getPecho()}" name="txtPecho" class="form-control" id="validationTooltip06" pattern="^([0-9]|[1-9][0-9]|1[0-9][0-9]|200)$"  oninvalid="setCustomValidity('Ingrese un numero valido entre 0-200')" >
                                    <div class="invalid-feedback">
                                        Ingrese un número entre 0-200!
                                    </div>
                                </div>                 
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip07">Abdomen</label>
                                    <input type="text" value="${testedit.getAbdomen()}" name="txtAbdomen" class="form-control" id="validationTooltip07" pattern="^([0-9]|[1-9][0-9]|1[0-9][0-9]|200)$"  oninvalid="setCustomValidity('Ingrese un numero valido entre 0-200')" >
                                    <div class="invalid-feedback">
                                        Ingrese un número entre 0-200!
                                    </div>
                                </div>
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip8">Cunclilla</label>
                                    <input type="text" value="${testedit.getCunclilla()}" name="txtCunclilla"  class="form-control" id="validationTooltip08" pattern="^([0-9]|[1-9][0-9]|1[0-9][0-9]|200)$"  oninvalid="setCustomValidity('Ingrese un numero valido entre 0-200')" >
                                    <div class="invalid-feedback">
                                        Ingrese un número entre 0-200!
                                    </div>
                                </div>
                            </div>
                            <legend>Fuerza Máxima</legend>
                            <div class="form-row">
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip09">Prom</label>
                                    <input type="text" value="${testedit.getProm()}" name="txtProm" class="form-control" id="validationTooltip09" pattern="^([0-9]|[1-9][0-9]|1[0-5][0-9]|160)[.]([0-9]|[1-9][0-9])$"  oninvalid="setCustomValidity('Ingrese un numero valido entre 0.0 - 160.00')" >
                                    <div class="invalid-feedback">
                                        Ingrese un número decimal entre 0-160!
                                    </div>
                                </div>                 
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip10">Halon</label>
                                    <input type="text" value="${testedit.getHalon()}" name="txtHalon" class="form-control" id="validationTooltip10" pattern="^([0-9]|[1-9][0-9]|1[0-5][0-9]|160)[.]([0-9]|[1-9][0-9])$"  oninvalid="setCustomValidity('Ingrese un numero valido entre 0.0 - 160.00')">
                                    <div class="invalid-feedback">
                                        Ingrese un número decimal entre 0-160!
                                    </div>
                                </div>
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip11">Sentadilla</label>
                                    <input type="text" value="${testedit.getSentadilla()}" name="txtSentadilla"  class="form-control" id="validationTooltip11" pattern="^([0-9]|[1-9][0-9]|1[0-5][0-9]|160)[.]([0-9]|[1-9][0-9])$"  oninvalid="setCustomValidity('Ingrese un numero valido entre 0.0 - 160.00')">
                                    <div class="invalid-feedback">
                                        Ingrese un número decimal entre 0-160!
                                    </div>
                                </div>
                            </div>

                            <legend>Resistencia a la Rapidez Especial</legend>
                            <div class="form-row">
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip12">Ushikomi</label>
                                    <input type="text" value="${testedit.getUshikomi()}" name="txtUshikomi" class="form-control" id="validationTooltip12" pattern="^([0-9]|[1-9][0-9]|1[0-9][0-9]|200)$"  oninvalid="setCustomValidity('Ingrese un numero valido entre 0-200')" >
                                    <div class="invalid-feedback">
                                        Ingrese un número entre 0-200!
                                    </div>
                                </div>                 
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip13">Nagekomi 60s</label>
                                    <input type="text" value="${testedit.getNagekomi60()}" name="txtNagekomi60" class="form-control" id="validationTooltip13" pattern="^([0-9]|[1-6][0-9]|70)$"  oninvalid="setCustomValidity('Ingrese un numero valido entre 0-70')">
                                    <div class="invalid-feedback">
                                        Ingrese un número entre 0-70!
                                    </div>
                                </div>
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip14">Nagekomi 30s</label>
                                    <input type="text" value="${testedit.getNagekomi30()}" name="txtNagekomi30"  class="form-control" id="validationTooltip14" pattern="^([0-9]|[1-3][0-9]|40)$"  oninvalid="setCustomValidity('Ingrese un numero valido entre 0-40')">
                                    <div class="invalid-feedback">
                                        Ingrese un número entre 0-40!
                                    </div>
                                </div>
                            </div>

                            <legend>Velocidad de Traslación</legend>
                            <div class="form-row">
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip15">Pique 30m</label>
                                    <input type="text" value="${testedit.getPique30()}" name="txtPique30" class="form-control" id="validationTooltip15" pattern="^([0-9]|[1-2][0-9]|30)[.]([0-9]|[1-9][0-9])$"  oninvalid="setCustomValidity('Ingrese un numero valido entre 0.0 - 30.0')">
                                    <div class="invalid-feedback">
                                        Ingrese un número decimal entre 0-30!
                                    </div>
                                </div>                 
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip16">Pique 50m</label>
                                    <input type="text" value="${testedit.getPique50()}" name="txtPique50" class="form-control" id="validationTooltip16" pattern="^([0-9]|[1-3][0-9]|40)[.]([0-9]|[1-9][0-9])$"  oninvalid="setCustomValidity('Ingrese un numero valido entre 0.0 - 40.0')">
                                    <div class="invalid-feedback">
                                        Ingrese un número decimal entre 0-40!
                                    </div>
                                </div>
                                <div class="col-md-2 mb-3">
                                    <label for="validationTooltip17">Pique 100m</label>
                                    <input type="text" value="${testedit.getPique100()}" name="txtPique100"  class="form-control" id="validationTooltip17" pattern="^([0-9]|[1-5][0-9]|60)[.]([0-9]|[1-9][0-9])$"  oninvalid="setCustomValidity('Ingrese un numero valido entre 0.0 - 60.0')">
                                    <div class="invalid-feedback">
                                        Ingrese un número decimal entre 0-60!
                                    </div>
                                </div>
                            </div>

                            
                            <input type="submit" name="accion" value="Actualizar" class="btn btn-success" >
                             <a type="submit" href="principal.jsp" class="btn btn-warning">Cancelar</a>
                        </form>

                    </div> 
                    <div class="alert alert-warning alert-dismissible">
                        <a  class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>Atención!</strong> Verificar que todos los campos se encuentren en verde. Si ingresó mal un campo por favor <strong>Actualice</strong> la página.
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

