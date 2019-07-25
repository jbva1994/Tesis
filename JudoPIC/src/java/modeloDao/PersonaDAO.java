
package modeloDao;

import config.Conexion;
import interfaces.CrudPersona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Persona;


public class PersonaDAO implements CrudPersona{
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    @Override
    public List listarPer() {     
        String sql = "Select * from persona";
        List<Persona>lista=new ArrayList<>();
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                Persona per = new Persona();
                per.setId(rs.getInt(1));
                per.setUsuario(rs.getString(2));
                per.setClave(rs.getString(3));
                per.setFoto(rs.getString(4));
                per.setCedula(rs.getString(5));               
                per.setNombre(rs.getString(6));
                per.setApellido(rs.getString(7));
                per.setFechaNacimiento(rs.getString(8));
                per.setTipo(rs.getString(9));
                per.setGrado(rs.getString(10));
                per.setCategoria(rs.getString(11));
                per.setSexo(rs.getString(12));
                per.setPeso(rs.getString(13));
                lista.add(per);
                
            }
        } catch (Exception e) {
        }
        return lista;
    }

    @Override
    public Persona listId(int id) {
     Persona per =new Persona();
        String sql="select * from persona where idpersona="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
                per.setUsuario(rs.getString(2));
                per.setClave(rs.getString(3));
                per.setFoto(rs.getString(4));
                per.setCedula(rs.getString(5));
                per.setNombre(rs.getString(6));
                per.setApellido(rs.getString(7));
                per.setFechaNacimiento(rs.getString(8));
                per.setTipo(rs.getString(9));
                per.setGrado(rs.getString(10));
                per.setCategoria(rs.getString(11));
                per.setSexo(rs.getString(12));
                per.setPeso(rs.getString(13));
                
            }
        } catch (Exception e) {
        }
        return per; 
    }

    @Override
    public int agregarPer(Persona per) {
        String sql="insert into persona(usuario, clave, foto, cedula, nombre, apellido, fechaNacimiento, tipo, grado, categoria, sexo, peso)values(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, per.getUsuario());
            ps.setString(2, per.getClave());
            ps.setString(3, per.getFoto());
            ps.setString(4, per.getCedula());
            ps.setString(5, per.getNombre());
            ps.setString(6, per.getApellido());
            ps.setString(7, per.getFechaNacimiento());
            ps.setString(8, per.getTipo());
            ps.setString(9, per.getGrado());
            ps.setString(10, per.getCategoria());
            ps.setString(11, per.getSexo());
            ps.setString(12, per.getPeso());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
   
    }

    @Override
    public int actualizarPer(Persona per) {
     String sql="update persona set usuario=?, clave=?, foto=?, cedula=?, nombre=?, apellido=?, fechaNacimiento=?, tipo=? ,grado=?, categoria=?, sexo=?, peso=? where idpersona=?";
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, per.getUsuario());
            ps.setString(2, per.getClave());
            ps.setString(3, per.getFoto());
            ps.setString(4, per.getCedula());
            ps.setString(5, per.getNombre());
            ps.setString(6, per.getApellido());
            ps.setString(7, per.getFechaNacimiento());
            ps.setString(8, per.getTipo());
            ps.setString(9, per.getGrado());
            ps.setString(10, per.getCategoria());
            ps.setString(11, per.getSexo());
            ps.setString(12, per.getPeso());
            ps.setInt(13, per.getId());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    @Override
    public void eliminarPer(int id) {
     String sql="delete from persona where idpersona="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
}
