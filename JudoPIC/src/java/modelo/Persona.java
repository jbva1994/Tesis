package modelo;

import java.io.InputStream;
import java.sql.Date;

public class Persona {

    int id;
    String usuario;
    String clave;
    //InputStream guardarimg;
    //private byte[] mostrarimg;
    InputStream foto;
    String cedula;
    String nombre;
    String apellido;
    String fechaNacimiento;
    String tipo;
    String grado;
    String categoria;
    String sexo;
    String peso;

    public Persona() {

    }

    public Persona(String usuario, String clave, InputStream foto, String cedula, String nombre, String apellido, String fechaNacimiento, String tipo, String grado, String categoria, String sexo, String peso) {
        this.usuario = usuario;
        this.clave = clave;
        this.foto = foto;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.tipo = tipo;
        this.grado = grado;
        this.categoria = categoria;
        this.sexo = sexo;
        this.peso = peso;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

}
