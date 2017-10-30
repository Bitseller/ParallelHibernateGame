package persistencia.controladores;

import persistencia.dao.PersonajeDAO;
import persistencia.entidades.EPersonaje;
import persitencia.dao.impl.PersonajeDAOImplHibernate;

public class ControladorPersonaje {
    private PersonajeDAO objetoDAO;

    public ControladorPersonaje() {
    	objetoDAO=new PersonajeDAOImplHibernate();//CAMBIANDO ESTA LINEA IMPLEMENTO OTRO TIPO DE DAO
    }

    public void guardar(EPersonaje objeto) throws Exception {
    	objetoDAO.guardarOActualizar(objeto);
    }

	public void actualizar(EPersonaje objeto) throws Exception {
    	objetoDAO.actualizar(objeto);
		
	}
    
}