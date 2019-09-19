package modeloDao;

import config.Conexion;
import interfaces.CrudTestPedagogico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    String sql ="Select idtestpedagogico,cedula,fecha,barras,paralelas,cabos,pecho,abdomen,cunclilla,prom,halon,sentadilla,ushikomi,nagekomi_60s,nagekomi_30s,pique_30m,pique_50m,pique_100m from testpedagogico t, persona p where t.idpersona=p.idpersona";
        List<TestPedagogico>lista=new ArrayList<>();
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                TestPedagogico test = new TestPedagogico();
                test.setIdtest(rs.getInt(1));
                test.setCedula(rs.getString(2));
                test.setFecha(rs.getString(3));
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
        } catch (Exception e) {
        }
        return lista;}

    @Override
    public TestPedagogico listId(int id) {
    TestPedagogico test =new TestPedagogico();
        //String sql="select * from testpedagogico where idtestpedagogico="+id;
        String sql="select idtestpedagogico,cedula,barras,paralelas,cabos,pecho,abdomen,cunclilla,prom,halon,sentadilla,ushikomi,nagekomi_60s,nagekomi_30s,pique_30m,pique_50m,pique_100m from testpedagogico t, persona p where t.idpersona=p.idpersona and t.idtestpedagogico="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
               
                
                test.setCedula(rs.getString(2));
                test.setBarras(rs.getInt(3));
                test.setParalelas(rs.getInt(4));
                test.setCabos(rs.getInt(5));
                test.setPecho(rs.getInt(6));
                test.setAbdomen(rs.getInt(7));
                test.setCunclilla(rs.getInt(8));
                test.setProm(rs.getDouble(9));
                test.setHalon(rs.getDouble(10));
                test.setSentadilla(rs.getDouble(11));
                test.setUshikomi(rs.getInt(12));
                test.setNagekomi60(rs.getInt(13));
                test.setNagekomi30(rs.getInt(14));
                test.setPique30(rs.getDouble(15));
                test.setPique50(rs.getDouble(16));
                test.setPique100(rs.getDouble(17));
                
                
            }
        } catch (Exception e) {
        }
        return test; }

    @Override
    public int agregarTest(TestPedagogico test) {
        String sql="insert into testpedagogico(idpersona, barras, paralelas, cabos, pecho, abdomen, cunclilla, prom, halon, sentadilla, ushikomi, nagekomi_60s, nagekomi_30s, pique_30m, pique_50m, pique_100m)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, test.getIdpersona());
            //ps.setString(2, test.getFecha());
            ps.setInt(2, test.getBarras());
            ps.setInt(3, test.getParalelas());
            ps.setInt(4, test.getCabos());
            ps.setInt(5, test.getPecho());
            ps.setInt(6, test.getAbdomen());
            ps.setInt(7, test.getCunclilla());
            ps.setDouble(8, test.getProm());
            ps.setDouble(9, test.getHalon());
            ps.setDouble(10, test.getSentadilla());
            ps.setInt(11, test.getUshikomi());
            ps.setInt(12, test.getNagekomi60());
            ps.setInt(13, test.getNagekomi30());
            ps.setDouble(14, test.getPique30());
            ps.setDouble(15, test.getPique50());
            ps.setDouble(16, test.getPique100());            
        
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;}

    @Override
    public int actualizarTest(TestPedagogico test) {
     String sql="update testpedagogico set idpersona=?, barras=?, paralelas=?, cabos=?, pecho=?, abdomen=?, cunclilla=? ,prom=?, halon=?, sentadilla=?, ushikomi=?, nagekomi_60s=?, nagekomi_30s=?, pique_30m=?, pique_50m=?, pique_100m=? where idtestpedagogico=?";
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, test.getIdpersona());
            ps.setInt(2, test.getBarras());
            ps.setInt(3, test.getParalelas());
            ps.setInt(4, test.getCabos());
            ps.setInt(5, test.getPecho());
            ps.setInt(6, test.getAbdomen());
            ps.setInt(7, test.getCunclilla());
            ps.setDouble(8, test.getProm());
            ps.setDouble(9, test.getHalon());
            ps.setDouble(10, test.getSentadilla());
            ps.setInt(11, test.getUshikomi());
            ps.setInt(12, test.getNagekomi60());
            ps.setInt(13, test.getNagekomi30());
            ps.setDouble(14, test.getPique30());
            ps.setDouble(15, test.getPique50());
            ps.setDouble(16, test.getPique100());
            ps.setInt(17, test.getIdtest());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;}

    @Override
    public void eliminarTest(int id) {
     String sql="delete from testpedagogico where idtestpedagogico="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }}

    @Override
    public List buscarTest(String texto) {
    
        //String sql = "Select * from testpedagogico where idpersona like '%"+texto+"%' or fecha like '%"+texto+"%' ";
        String sql = "Select idtestpedagogico,cedula,fecha,barras,paralelas,cabos,pecho,abdomen,cunclilla,prom,halon,sentadilla,ushikomi,nagekomi_60s,nagekomi_30s,pique_30m,pique_50m,pique_100m from testpedagogico t, persona p where t.idpersona=p.idpersona and p.cedula like '%"+texto+"%' ";//or fecha like '%"+texto+"%' ";;
        List<TestPedagogico>lista=new ArrayList<>();
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                TestPedagogico test = new TestPedagogico();
                test.setIdtest(rs.getInt(1));
                test.setCedula(rs.getString(2));
                test.setFecha(rs.getString(3));
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
        } catch (Exception e) {
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
        } catch (Exception e) {
        }
        return test;
    }

}
