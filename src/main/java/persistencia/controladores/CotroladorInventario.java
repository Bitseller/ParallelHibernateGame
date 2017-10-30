package persistencia.controladores;

import persistencia.dao.InventarioDAO;
import persitencia.dao.impl.InventarioDAOImplHibernate;

public class CotroladorInventario {
    private InventarioDAO objetoDAO;

    public CotroladorInventario() {
    	objetoDAO=new InventarioDAOImplHibernate();//CAMBIANDO ESTA LINEA IMPLEMENTO OTRO TIPO DE DAO
    }

}
