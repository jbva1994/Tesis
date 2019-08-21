<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
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

                    
        <div class="d-flex "> 
            <div class="col-sm-12">
                <div class="card">
                    <div class="card-body">
                        <h3 align="top">FORMULARIO DE INGRESO DE USUARIOS AL SISTEMA</h3>
                        <form class="was-validated" action="ControladorPer?menu=Persona" method="POST" enctype="multipart/form-data">
                            <h5>Datos de login</h5>
                            <div class="form-row">
                                <div class="col-md-3 mb-2">
                                    <label for="validationTooltip01">Usuario:</label>
                                    <input type="email" value="${peredit.getUsuario()}" name="txtUsuario" placeholder="aaaaaa@gmail.com"  class="form-control" id="validationTooltip01" required>                                   
                                    <div class="invalid-feedback">
                                        Por favor rellene este campo!
                                    </div>
                                </div>
                                <div class="col-md-3 mb-2">
                                    <label for="validationTooltip02">Clave:</label>
                                    <input type="password" value="JudoPic2019" name="txtClave" class="form-control" id="validationTooltip02" readonly>
                                    <div class="invalid-feedback">
                                        Por favor rellene este campo!
                                    </div>
                                </div>               
                            </div>
                            <h5>Datos de Usuario</h5>
                            <div class="form-row">
                                <div class="col-md-3 mb-5">
                                    <label for="validationTooltip03">Foto:</label>
                                    <input type="file" value="${peredit.getFoto()}" name="txtFoto"  class="form-control" id="validationTooltip03" >
                                    <div class="invalid-feedback">
                                        Bien!
                                    </div>
                                </div>
                                <div class="col-md-2 mb-5">
                                    <label for="validationTooltip04">Cedula:</label>
                                    <input type="text" value="${peredit.getCedula()}" name="txtCedula" class="form-control" id="validationTooltip04" required>
                                    <div class="invalid-feedback">
                                        Por favor rellene este campo!
                                    </div>
                                </div>
                                <div class="col-md-2 mb-5">
                                    <label for="validationTooltip05">Nombre:</label>
                                    <input type="text" value="${peredit.getNombre()}" name="txtNombre" class="form-control" id="validationTooltip05" required>
                                    <div class="invalid-feedback">
                                        Por favor rellene este campo!
                                    </div>
                                </div> 
                                <div class="col-md-2 mb-5">
                                    <label for="validationTooltip06">Apellido:</label>
                                    <input type="text" value="${peredit.getApellido()}" name="txtApellido" class="form-control" id="validationTooltip06" required>
                                    <div class="invalid-feedback">
                                        Por favor rellene este campo!
                                    </div>
                                </div> 
                                <div class="col-md-3 mb-5">
                                    <label for="validationTooltip07">Fecha de Nacimiento:</label>
                                    <input type="date" value="${peredit.getFechaNacimiento()}" name="txtFechaNacimiento" placeholder="Ejemplo: DD-MM-YYYY"  class="form-control" id="validationTooltip07" required>
                                    <div class="invalid-feedback">
                                        Por favor rellene este campo!
                                    </div>
                                </div> 
                            </div>
                            <h5>Datos Deportivos</h5>
                            <div class="form-row">
                                <div class="col-md-2 mb-5">
                                    <label for="validationTooltip08">Tipo:</label>
                                    <select type="text" value="${peredit.getTipo()}" name="txtTipo" class="form-control" id="validationTooltip08" required>
                                        <option>Deportista</option>
                                        <option>Entrenador</option>
                                    </select>
                                    <div class="invalid-feedback">
                                        Por favor rellene este campo!
                                    </div>
                                </div>
                                    
                                <div class="col-md-2 mb-5">
                                    <label for="validationTooltip09">Grado:</label>
                                    <select type="text" value="${peredit.getGrado()}" name="txtGrado" class="form-control" id="validationTooltip09" required>
                                        <option></option>
                                        <option>6 Kyu</option>
                                        <option>5 Kyu</option>
                                        <option>4 Kyu</option>
                                        <option>3 Kyu</option>
                                        <option>2 Kyu</option>
                                        <option>1 Kyu</option>
                                        <option>1 Dan</option>
                                        <option>2 Dan</option>
                                        <option>3 Dan</option>
                                        <option>6 Dan</option>
                                    </select>
                                    <div class="invalid-feedback">
                                        Por favor rellene este campo!
                                    </div>
                                </div>
                                <div class="col-md-2 mb-5">
                                    <label for="validationTooltip10">Categoria:</label>
                                    <select type="text" value="${peredit.getCategoria()}" name="txtCategoria"  class="form-control" id="validationTooltip10" >
                                        <option></option>
                                        <option>Juvenil</option>
                                        <option>Senior</option>
                                    </select>
                                    <div class="invalid-feedback">
                                        Bien!
                                    </div>
                                </div>
                                <div class="col-md-2 mb-5">
                                    <label for="validationTooltip11">Sexo:</label>
                                    <select type="text" value="${peredit.getSexo()}" name="txtSexo" class="form-control" id="validationTooltip11" >
                                        <option></option>
                                        <option>Masculino</option>           
                                        <option>Femenino</option>
                                    </select>
                                    <div class="invalid-feedback">
                                        Bien!
                                    </div>
                                </div>
                                <div class="col-md-2 mb-5">
                                    <label for="validationTooltip12">Peso:</label>
                                    <select type="text" value="${peredit.getPeso()}" name="txtPeso" class="form-control" id="validationTooltip12" required>
                                        <option></option>
                                        <option>-60Kg</option>
                                        <option>-66Kg</option>
                                        <option>-73Kg</option>
                                        <option>-81Kg</option>
                                        <option>-90Kg</option>
                                        <option>-100Kg</option>
                                        <option>+100Kg</option>
                                        <option>-48Kg</option>
                                        <option>-52Kg</option>
                                        <option>-57Kg</option>
                                        <option>-63Kg</option>
                                        <option>-70Kg</option>
                                        <option>-78Kg</option>
                                        <option>+78Kg</option>
                                    </select>
                                    <div class="invalid-feedback">
                                        Por favor rellene este campo!
                                    </div>
                                </div>

                            </div>

                            <input type="submit" name="accion" value="Agregar" class="btn btn-primary">
                            
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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>