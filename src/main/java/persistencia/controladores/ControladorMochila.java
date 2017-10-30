package persistencia.controladores;

import persistencia.dao.MochilaDAO;
import persistencia.entidades.EMochila;
import persitencia.dao.impl.MochilaDAOImplHibernate;

public class ControladorMochila {
	 private MochilaDAO objetoDAO;

	    public ControladorMochila() {
	    	objetoDAO=new MochilaDAOImplHibernate();//CAMBIANDO ESTA LINEA IMPLEMENTO OTRO TIPO DE DAO
	    }

	    public void guardar(EMochila objeto) throws Exception {
	    	objetoDAO.guardarOActualizar(objeto);
	    }

		public void actualizar(EMochila objeto) throws Exception {
	    	objetoDAO.actualizar(objeto);
			
		}
}
