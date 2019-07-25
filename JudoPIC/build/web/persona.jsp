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
        <div class="d-flex ">           
            <div class="col-sm-3">
                <div class="card">
                    <div class="card-body">
                        <form action="ControladorPer?menu=Persona" method="POST">

                            <div class="form-group">
                                <label>Usuario</label>
                                <input type="text" value="${peredit.getUsuario()}" name="txtUsuario" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Clave</label>
                                <input type="text" value="${peredit.getClave()}" name="txtClave" class="form-control">
                            </div>

                            <div class="form-group">
                                <label>Foto</label>
                                <input type="text" value="${peredit.getFoto()}" name="txtFoto" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Cedula</label>
                                <input type="text" value="${peredit.getCedula()}" name="txtCedula" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Nombre</label>
                                <input type="text" value="${peredit.getNombre()}" name="txtNombre" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Apellido</label>
                                <input type="text" value="${peredit.getApellido()}" name="txtApellido" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Fecha de Nacimiento</label>
                                <input type="text" value="${peredit.getFechaNacimiento()}" name="txtFechaNacimiento" placeholder="Ejemplo: YYYY-MM-DD" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Tipo</label>
                                <input type="text" value="${peredit.getTipo()}" name="txtTipo" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Grado</label>
                                <input type="text" value="${peredit.getGrado()}" name="txtGrado" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Categoria</label>
                                <input type="text" value="${peredit.getCategoria()}" name="txtCategoria" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Sexo</label>
                                <input type="text" value="${peredit.getSexo()}" name="txtSexo" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Peso</label>
                                <input type="text" value="${peredit.getPeso()}" name="txtPeso" class="form-control">
                            </div>

                            <input type="submit" name="accion" value="Agregar" class="btn btn-primary">
                            <input type="submit" name="accion" value="Actualizar" class="btn btn-success">
                        </form>
                    </div>                         
                </div>
            </div> 
            <br>            
            <div class="col-sm-16">
                <div class="card">
                    <div class="card-body">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>USUARIO</th>
                                    <th>CLAVE</th>
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
                                    <td>${per.getId()}</td>
                                    <td>${per.getUsuario()}</td>
                                    <td>${per.getClave()}</td>
                                    <td>${per.getFoto()}</td>
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
                                        <a class="btn btn-warning" href="ControladorPer?menu=Persona&accion=Editar&id=${per.getId()}">Editar</a>
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

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
