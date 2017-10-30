package persistencia.controladores;

import persistencia.dao.ItemDAO;
import persistencia.entidades.EItem;
import persitencia.dao.impl.ItemDAOImplHibernate;

public class ControladorItem {
	 private ItemDAO objetoDAO;

	    public ControladorItem() {
	    	objetoDAO=new ItemDAOImplHibernate();//CAMBIANDO ESTA LINEA IMPLEMENTO OTRO TIPO DE DAO
	    }

	    public void guardar(EItem objeto) throws Exception {
	    	objetoDAO.guardarOActualizar(objeto);
	    }

		public void actualizar(EItem objeto) throws Exception {
	    	objetoDAO.actualizar(objeto);
			
		}
}
