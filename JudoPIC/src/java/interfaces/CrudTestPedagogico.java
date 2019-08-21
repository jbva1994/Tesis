package interfaces;

import java.util.List;
import modelo.TestPedagogico;


public interface CrudTestPedagogico {
    
        public List listarTest();
    public TestPedagogico listId(int id);
    public int agregarTest(TestPedagogico per);
    public int actualizarTest(TestPedagogico per);
    public void eliminarTest(int id);
    public List buscarTest(String texto);
   
}
