
package interfaces;

import java.util.List;
import modelo.Persona;


public interface CrudPersona {
    
    public List listarPer();
    public Persona listId(int id);
    public void agregarPer(Persona per);
    public int actualizarPer(Persona per);
    public void eliminarPer(int id);
    public List buscarPer(String texto);
      
    
}
