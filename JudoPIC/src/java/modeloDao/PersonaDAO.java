package modeloDao;

import config.Conexion;
import interfaces.CrudPersona;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import modelo.Persona;

public class PersonaDAO implements CrudPersona {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    @Override
    public List listarPer() {
        String sql = "Select * from persona";
        List<Persona> lista = new ArrayList<>();
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Persona per = new Persona();
                per.setId(rs.getInt(1));
                per.setUsuario(rs.getString(2));
                per.setClave(rs.getString(3));
                per.setFoto(rs.getBinaryStream(4));
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    @Override
    public Persona listId(int id) {
        Persona per = new Persona();
        String sql = "select * from persona where idpersona=" + id;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                per.setUsuario(rs.getString(2));
                per.setClave(rs.getString(3));
                per.setFoto(rs.getBinaryStream(4));
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return per;
    }

    @Override
    public void agregarPer(Persona per) {
        String sql = "insert into persona(usuario, clave, foto, cedula, nombre, apellido, fechaNacimiento, tipo, grado, categoria, sexo, peso)values(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, per.getUsuario());
            ps.setString(2, per.getClave());
            ps.setBlob(3, per.getFoto());
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public int actualizarPer(Persona per) {
        String sql = "update persona set usuario=?, clave=?, cedula=?, nombre=?, apellido=?, fechaNacimiento=?, tipo=?, grado=?, categoria=?, sexo=?, peso=? where idpersona=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, per.getUsuario());
            ps.setString(2, per.getClave());
            //ps.setBlob(3, per.getFoto());
            ps.setString(3, per.getCedula());
            ps.setString(4, per.getNombre());
            ps.setString(5, per.getApellido());
            ps.setString(6, per.getFechaNacimiento());
            ps.setString(7, per.getTipo());
            ps.setString(8, per.getGrado());
            ps.setString(9, per.getCategoria());
            ps.setString(10, per.getSexo());
            ps.setString(11, per.getPeso());
            ps.setInt(12, per.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return r;
    }
    
    public int restablecer(Persona per) {
        r=0;
        String sql = "update persona set clave=? where usuario=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, per.getClave());
            ps.setString(2, per.getUsuario());
            ps.executeUpdate();
            while (rs.next()) {
                r = r + 1;
                per.setUsuario(rs.getString("usuario"));
                per.setClave(rs.getString("clave"));
            }
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return r;
    }

    @Override
    public void eliminarPer(int id) {
        String sql = "delete from persona where idpersona=" + id;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int actualizarPerImg(Persona per) {
        String sql = "update persona set usuario=?, clave=?, foto=?, cedula=?, nombre=?, apellido=?, fechaNacimiento=?, tipo=? ,grado=?, categoria=?, sexo=?, peso=? where idpersona=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, per.getUsuario());
            ps.setString(2, per.getClave());
            ps.setBlob(3, per.getFoto());
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return r;
    }

    @Override
    public List buscarPer(String texto) {
        //String sql = "Select * from persona where nombre like '%"+texto+"%'";
        String sql = "Select * from persona where idpersona like '%" + texto + "%' "
                + "or cedula like '%" + texto + "%' or nombre like'%" + texto + "%' "
                + "or apellido like'%" + texto + "%' or fechaNacimiento like'%" + texto + "%' "
                + "or tipo like'%" + texto + "%' or grado like'%" + texto + "%' or categoria like'%" + texto + "%' "
                + "or sexo like'%" + texto + "%' or peso like'%" + texto + "%'";
        List<Persona> lista = new ArrayList<>();
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Persona per = new Persona();
                per.setId(rs.getInt("idpersona"));
                per.setUsuario(rs.getString("usuario"));
                per.setClave(rs.getString("clave"));
                per.setFoto(rs.getBinaryStream("foto"));
                per.setCedula(rs.getString("cedula"));
                per.setNombre(rs.getString("nombre"));
                per.setApellido(rs.getString("apellido"));
                per.setFechaNacimiento(rs.getString("fechaNacimiento"));
                per.setTipo(rs.getString("tipo"));
                per.setGrado(rs.getString("grado"));
                per.setCategoria(rs.getString("categoria"));
                per.setSexo(rs.getString("sexo"));
                per.setPeso(rs.getString("peso"));
                lista.add(per);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public boolean enviarCorreo(String txtUsuario, String txtClave) throws IOException {
        boolean enviado = false;
        try {
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.auth", "true");

            String correoRemitente = "informaticauce2019@gmail.com";
            String passwordRemitente = "informatica2019";

            Session session = Session.getDefaultInstance(props);
            //new javax.mail.Authenticator() {
            // protected PasswordAuthentication getPasswordAuthentication(){
            //    return new PasswordAuthentication(correoRemitente, passwordRemitente);
            // }
            //});

            String asunto = "Link de Activación para app JudoPIC";
            /*
            String mensaje = "	<html>\n"
                    + "	<body>\n"
                   + "		<img src=\"cid:figura1\" height=\"47\" width=\"170\" />\n"
                    + "		<p>Bienvenidos a la aplicación para deportistas de Judo de Pichincha</p>\n"
                    + "		<ul style=\"list-style-type:none\">	\n"
                    + "		<p> Link de Activación: "http://localhost:8084/JudoPIC/ActivateAccount?key1="+txtUsuario+"&key2="+myHash+" </p>\n"
                    + "		</ul>\n"
                    + "		<p>Por favor restablecer la contraseña al momento de iniciar sesión por primera vez.</p>\n"
                    + "		<p>Saludos.</p>\n"
                   // + "		<img src=\"cid:${emailFoot}\" />\n"
                    + "	</body>\n"
                    + "	</html>";
             */
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correoRemitente));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(txtUsuario));
            message.setSubject(asunto);
            message.setText("Por favor acceder al link para activar la Cuenta y reestablecer la contraseña. \n"
                    + "Link de Activación: " + "http://localhost:8084/ActivateAccount?key1=" + txtUsuario + "&key2=" + txtClave);

            /*
            Multipart multipart = new MimeMultipart("related");
            
            BodyPart texto = new MimeBodyPart();
            //texto.setContent(mensaje, "text/html");
            multipart.addBodyPart(texto);
            
            MimeBodyPart imagen = new MimeBodyPart();
            
            //DataSource fds = new FileDataSource("C:\\Users\\jbv317\\OneDrive - Sherwin-Williams\\Pictures\\blue.jpg");
            DataSource fds = new URLDataSource(new URL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRka-IgWuEloQHsL8poZ4ByLiQKYZFpAzRbugbCy2U8OkrkXtkp"));
            imagen.setDataHandler(new DataHandler(fds));
            //imagen.attachFile("C:\\Users\\jbv317\\OneDrive - Sherwin-Williams\\Pictures\\blue.jpg");
            imagen.setHeader("Content-ID", "<figura1>");
          
            multipart.addBodyPart(imagen);
            message.setContent(multipart);
             */
            Transport t = session.getTransport("smtp");
            t.connect(correoRemitente, passwordRemitente);
            t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            t.close();

        } catch (AddressException ex) {
            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return enviado;
    }

    public void listarImg(int id, HttpServletResponse response) {
        String sql = "select * from persona where idpersona=" + id;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedinputStream = null;
        BufferedOutputStream bufferedoutputStream = null;
        response.setContentType("imgPerfiles");
        try {
            outputStream = response.getOutputStream();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                inputStream = rs.getBinaryStream("foto");
            }
            bufferedinputStream = new BufferedInputStream(inputStream);
            bufferedoutputStream = new BufferedOutputStream(outputStream);
            int i = 0;
            while ((i = bufferedinputStream.read()) != -1) {
                bufferedoutputStream.write(i);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String Encriptar(String texto) {
        
        String BasicBase64format = java.util.Base64.getEncoder().encodeToString(texto.getBytes());
        return BasicBase64format;
    }

}
