package modeloDao;

import config.Conexion;
import interfaces.CrudTestPedagogico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.el.ELResolver;
import modelo.Persona;
import modelo.TestPedagogico;


public class TestPedagogicoDAO implements CrudTestPedagogico{
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;


    @Override
    public List listarTest() {
    //String sql = "Select * from testpedagogico";
    String sql ="Select idtestpedagogico,cedula,registro,barras,paralelas,cabos,pecho,abdomen,cunclilla,prom,halon,sentadilla,ushikomi,nagekomi_60s,nagekomi_30s,pique_30m,pique_50m,pique_100m from testpedagogico t, persona p where t.idpersona=p.idpersona";
        List<TestPedagogico>lista=new ArrayList<>();
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                TestPedagogico test = new TestPedagogico();
                test.setIdtest(rs.getInt(1));
                test.setCedula(rs.getString(2));
                test.setRegistro(rs.getString(3));
                test.setBarras(rs.getInt(4));
                test.setParalelas(rs.getInt(5));
                test.setCabos(rs.getInt(6));
                test.setPecho(rs.getInt(7));
                test.setAbdomen(rs.getInt(8));
                test.setCunclilla(rs.getInt(9));
                test.setProm(rs.getDouble(10));
                test.setHalon(rs.getDouble(11));
                test.setSentadilla(rs.getDouble(12));
                test.setUshikomi(rs.getInt(13));
                test.setNagekomi60(rs.getInt(14));
                test.setNagekomi30(rs.getInt(15));
                test.setPique30(rs.getDouble(16));
                test.setPique50(rs.getDouble(17));
                test.setPique100(rs.getDouble(18));
                lista.add(test);
                
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lista;}

    @Override
    public TestPedagogico listId(int id) {
    TestPedagogico test =new TestPedagogico();
        //String sql="select * from testpedagogico where idtestpedagogico="+id;
        String sql="select idtestpedagogico,cedula,registro,barras,paralelas,cabos,pecho,abdomen,cunclilla,prom,halon,sentadilla,ushikomi,nagekomi_60s,nagekomi_30s,pique_30m,pique_50m,pique_100m from testpedagogico t, persona p where t.idpersona=p.idpersona and t.idtestpedagogico="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
                
                test.setCedula(rs.getString(2));
                test.setRegistro(rs.getString(3));
                test.setBarras(rs.getInt(4));
                test.setParalelas(rs.getInt(5));
                test.setCabos(rs.getInt(6));
                test.setPecho(rs.getInt(7));
                test.setAbdomen(rs.getInt(8));
                test.setCunclilla(rs.getInt(9));
                test.setProm(rs.getDouble(10));
                test.setHalon(rs.getDouble(11));
                test.setSentadilla(rs.getDouble(12));
                test.setUshikomi(rs.getInt(13));
                test.setNagekomi60(rs.getInt(14));
                test.setNagekomi30(rs.getInt(15));
                test.setPique30(rs.getDouble(16));
                test.setPique50(rs.getDouble(17));
                test.setPique100(rs.getDouble(18));
                
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return test; }

    @Override
    public int agregarTest(TestPedagogico test) {
        String sql="insert into testpedagogico(idpersona, registro, barras, paralelas, cabos, pecho, abdomen, cunclilla, prom, halon, sentadilla, ushikomi, nagekomi_60s, nagekomi_30s, pique_30m, pique_50m, pique_100m)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, test.getIdpersona());
            ps.setString(2, test.getRegistro());
            ps.setInt(3, test.getBarras());
            ps.setInt(4, test.getParalelas());
            ps.setInt(5, test.getCabos());
            ps.setInt(6, test.getPecho());
            ps.setInt(7, test.getAbdomen());
            ps.setInt(8, test.getCunclilla());
            ps.setDouble(9, test.getProm());
            ps.setDouble(10, test.getHalon());
            ps.setDouble(11, test.getSentadilla());
            ps.setInt(12, test.getUshikomi());
            ps.setInt(13, test.getNagekomi60());
            ps.setInt(14, test.getNagekomi30());
            ps.setDouble(15, test.getPique30());
            ps.setDouble(16, test.getPique50());
            ps.setDouble(17, test.getPique100());            
        
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return r;}

    @Override
    public int actualizarTest(TestPedagogico test) {
     String sql="update testpedagogico set idpersona=?, registro=?, barras=?, paralelas=?, cabos=?, pecho=?, abdomen=?, cunclilla=? ,prom=?, halon=?, sentadilla=?, ushikomi=?, nagekomi_60s=?, nagekomi_30s=?, pique_30m=?, pique_50m=?, pique_100m=? where idtestpedagogico=?";
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, test.getIdpersona());
            ps.setString(2, test.getRegistro());
            ps.setInt(3, test.getBarras());
            ps.setInt(4, test.getParalelas());
            ps.setInt(5, test.getCabos());
            ps.setInt(6, test.getPecho());
            ps.setInt(7, test.getAbdomen());
            ps.setInt(8, test.getCunclilla());
            ps.setDouble(9, test.getProm());
            ps.setDouble(10, test.getHalon());
            ps.setDouble(11, test.getSentadilla());
            ps.setInt(12, test.getUshikomi());
            ps.setInt(13, test.getNagekomi60());
            ps.setInt(14, test.getNagekomi30());
            ps.setDouble(15, test.getPique30());
            ps.setDouble(16, test.getPique50());
            ps.setDouble(17, test.getPique100());
            ps.setInt(18, test.getIdtest());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return r;}

    @Override
    public void eliminarTest(int id) {
     String sql="delete from testpedagogico where idtestpedagogico="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List buscarTest(String texto) {
    
        //String sql = "Select * from testpedagogico where idpersona like '%"+texto+"%' or fecha like '%"+texto+"%' ";
        String sql = "Select idtestpedagogico,cedula,registro,barras,paralelas,cabos,pecho,abdomen,cunclilla,prom,halon,sentadilla,ushikomi,nagekomi_60s,nagekomi_30s,pique_30m,pique_50m,pique_100m from testpedagogico t, persona p where t.idpersona=p.idpersona and p.cedula like '%"+texto+"%' ";//or fecha like '%"+texto+"%' ";;
        List<TestPedagogico>lista=new ArrayList<>();
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                TestPedagogico test = new TestPedagogico();
                test.setIdtest(rs.getInt(1));
                test.setCedula(rs.getString(2));
                test.setRegistro(rs.getString(3));
                test.setBarras(rs.getInt(4));
                test.setParalelas(rs.getInt(5));
                test.setCabos(rs.getInt(6));
                test.setPecho(rs.getInt(7));
                test.setAbdomen(rs.getInt(8));
                test.setCunclilla(rs.getInt(9));
                test.setProm(rs.getDouble(10));
                test.setHalon(rs.getDouble(11));
                test.setSentadilla(rs.getDouble(12));
                test.setUshikomi(rs.getInt(13));
                test.setNagekomi60(rs.getInt(14));
                test.setNagekomi30(rs.getInt(15));
                test.setPique30(rs.getDouble(16));
                test.setPique50(rs.getDouble(17));
                test.setPique100(rs.getDouble(18));              
                lista.add(test);
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lista;}
    
    public Persona buscarId(String cedula) {
    String sql = "Select * from persona where cedula="+cedula;
    Persona test=new Persona();
            try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                                
                test.setId(rs.getInt(1));                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return test;
    }

}