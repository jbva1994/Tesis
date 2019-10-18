<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <title>Editar Usuario</title>
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


        <div class="d-flex "> 
            <div class="col-sm-12">
                <div class="card">
                    <div class="card-body">
                        <h3 align="top">FORMULARIO DE INGRESO DE USUARIOS AL SISTEMA</h3>
                        <form class="was-validated" action="ControladorPer?menu=Persona" method="POST" enctype="multipart/form-data">
                            <h5>Datos de login</h5>
                            <div class="form-row">
                                <div class="col-md-3 mb-2">
                                    <label for="validationTooltip01">Usuario</label>
                                    <input type="email" value="${peredit.getUsuario()}" name="txtUsuario" placeholder="aaaaaa@gmail.com"  class="form-control" id="validationTooltip01" required>                                   
                                    <div class="invalid-feedback">
                                        Rellene este campo!
                                    </div>
                                </div>
                                <div class="col-md-3 mb-2">
                                    <label for="validationTooltip02">Clave</label>
                                     <input type="password" value="JudoPic2019" name="txtClave" class="form-control" id="validationTooltip02" readonly>
                                    <div class="invalid-feedback">
                                        Rellene este campo!
                                    </div>
                                </div>               
                            </div>
                            <h5>Datos de Usuario</h5>
                            <div class="form-row">
                                <div class="col-md-3 mb-5">
                                    <label for="validationTooltip03">Foto</label>
                                    <input type="file" name="txtFoto" value="null" accept="image/jpg" class="form-control" id="validationTooltip03" >
                                    <div class="invalid-feedback">
                                        Bien!
                                    </div>
                                </div>
                                <div class="col-md-2 mb-5">
                                    <label for="validationTooltip04">Cedula</label>
                                    <input type="text" value="${peredit.getCedula()}" name="txtCedula" class="form-control" id="validationTooltip04" pattern="^[0-9]{10}$"  oninvalid="setCustomValidity('Ingrese una cédula válida')" required>
                                    <div class="invalid-feedback">
                                        Rellene este campo!
                                    </div>
                                </div>
                                <div class="col-md-2 mb-5">
                                    <label for="validationTooltip05">Nombre</label>
                                    <input type="text" value="${peredit.getNombre()}" name="txtNombre" class="form-control" id="validationTooltip05" pattern="^[A-Za-z]{3,25}$"  oninvalid="setCustomValidity('Ingrese un nombre válido')" required>
                                    <div class="invalid-feedback">
                                        Rellene este campo!
                                    </div>
                                </div> 
                                <div class="col-md-2 mb-5">
                                    <label for="validationTooltip06">Apellido</label>
                                    <input type="text" value="${peredit.getApellido()}" name="txtApellido" class="form-control" id="validationTooltip06" pattern="^[A-Za-z]{3,25}$"  oninvalid="setCustomValidity('Ingresa un  apellido válido')" required>
                                    <div class="invalid-feedback">
                                        Rellene este campo!
                                    </div>
                                </div> 
                                <div class="col-md-3 mb-5">
                                    <label for="validationTooltip07">Fecha de Nacimiento</label>
                                    <input type="date" value="${peredit.getFechaNacimiento()}" name="txtFechaNacimiento" placeholder="Ejemplo: dd/mm/aaaa"  class="form-control" id="validationTooltip07" pattern="^((0[1-9][/]|[1-9][/]|[12][0-9][/]|3[01][/])(0[1-9][/]|[1-9][/]|1[012][/])(19[2-9][0-9]|200[0-9]|201[0-9]))$" oninvalid="setCustomValidity('Ingresa una fecha válida dd/mm/aaaa')" required>
                                    <div class="invalid-feedback">
                                        Rellene este campo!
                                    </div>
                                </div> 
                            </div>
                            <h5>Datos Deportivos</h5>
                            <div class="form-row">
                              
                                <div class="col-md-2 mb-5">
                                    <label for="validationTooltip08">Tipo:</label>
                                    <input type="text" value="${peredit.getTipo()}" name="txtTipo" class="form-control" id="validationTooltip08" pattern="Deportista|Entrenador"  oninvalid="setCustomValidity('Seleccione un campo valido Deportista o Entrenador')" required>
                                    <div class="invalid-feedback">
                                        Rellene este campo!
                                    </div>
                                </div>
                                <div class="col-md-2 mb-5">
                                    <label for="validationTooltip09">Grado</label>
                                    <input type="text" value="${peredit.getGrado()}" name="txtGrado" class="form-control" id="validationTooltip09" pattern="^([1-6] Kyu|[1-7] Dan)$"  oninvalid="setCustomValidity('Ingrese un campo valido #Kyu o #Dan')" required>
                                    <div class="invalid-feedback">
                                        Rellene este campo!
                                    </div>
                                </div>
                                <div class="col-md-2 mb-5">
                                    <label for="validationTooltip10">Categoria</label>
                                    <input type="text" value="${peredit.getCategoria()}" name="txtCategoria"  class="form-control" id="validationTooltip10" pattern="Juvenil|Senior"  oninvalid="setCustomValidity('Ingrese un campo válido Juvenil O Senior')" required>
                                    <div class="invalid-feedback">
                                        Rellene este campo!
                                    </div>
                                </div>
                                <div class="col-md-2 mb-5">
                                    <label for="validationTooltip11">Sexo</label>
                                    <input type="text" value="${peredit.getSexo()}" name="txtSexo" class="form-control" id="validationTooltip11" pattern="Masculino|Femenino"  oninvalid="setCustomValidity('Ingrese un campo válido Masculino O Femenino')" required>
                                    <div class="invalid-feedback">
                                        Rellene este campo!
                                    </div>
                                </div>
                                <div class="col-md-2 mb-5">
                                    <label for="validationTooltip12">Peso</label>
                                    <input type="text" value="${peredit.getPeso()}" name="txtPeso" class="form-control" id="validationTooltip12" pattern="^(-60Kg|-66Kg|-73Kg|-81Kg|-90Kg|-100Kg|\+100Kg|-48Kg|-52Kg|-57Kg|-63Kg|-70Kg|-78Kg|\+78Kg)$"  oninvalid="setCustomValidity('Ingrese un campo válido (+-)##Kg')" required >
                                    <div class="invalid-feedback">
                                        Rellene este campo!
                                    </div>
                                </div>

                            </div>
                            <input type="submit" name="accion" value="Actualizar" class="btn btn-success">
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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>