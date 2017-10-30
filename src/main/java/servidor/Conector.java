package servidor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import mensajeria.PaquetePersonaje;
import mensajeria.PaqueteUsuario;
import persistencia.controladores.ControladorMochila;
import persistencia.controladores.ControladorPersonaje;
import persistencia.controladores.ControladorUsuario;
import persistencia.entidades.EMochila;
import persistencia.entidades.EPersonaje;
import persistencia.entidades.EUsuario;
import persistencia.hibernate.HibernateUtil;
import properties.Idioma;

/**
 * The Class Conector.
 */
public class Conector {

    private String url = "primeraBase.bd";
    private Connection connect;

    private static final int COLUMNA_1 = 1;
    private static final int COLUMNA_2 = 2;
    private static final int COLUMNA_3 = 3;
    private static final int COLUMNA_4 = 4;
    private static final int COLUMNA_5 = 5;
    private static final int COLUMNA_6 = 6;
    private static final int COLUMNA_7 = 7;
    private static final int COLUMNA_8 = 8;
    private static final int COLUMNA_9 = 9;

    /**
     * Connect.
     */
    public void connect() {
        try {

    		Servidor.appendLog(MensajesLog.estableciendoConexion());
            connect = DriverManager.getConnection("jdbc:sqlite:" + url);
    		Servidor.appendLog(MensajesLog.conexionEstablecida());
        } catch (SQLException ex) {
    		Servidor.appendLog(MensajesLog.errorEstablecimientoConexionDB());
        }
    }

    /**
     * Close.
     */
    public void close() {
        try {
            connect.close();
        } catch (SQLException ex) {
    		Servidor.appendLog(MensajesLog.errorCerrarConexion());
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Registrar usuario.
     *
     * @param user the user
     * @return true, if successful
     */
    public boolean registrarUsuario(final PaqueteUsuario user) {
        boolean resultadoOperacion;
        HibernateUtil.abrirSessionEnHilo();
        ControladorUsuario ctrlUsr = new ControladorUsuario();
        try 
        {
        	if(ctrlUsr.existe(user.getUsername())) 
        	{
        		Servidor.appendLog(MensajesLog.usuarioDuplicado(user.getUsername()));
        		resultadoOperacion = false;
        	}
        	else
        	{
        		EUsuario e = new EUsuario();
        		e.setUserName(user.getUsername());
        		e.setPassword(user.getPassword());
        		
        		ctrlUsr.guardar(e);
        		Servidor.appendLog(MensajesLog.usuarioRegistradoExitosamente(user.getUsername()));
        		resultadoOperacion = true;
        	}
		} 
        catch (Exception ex) 
        {
    		Servidor.appendLog(MensajesLog.usuarioErrorGeneralAlRegistrar(user.getUsername()));
        	System.err.println(ex.getMessage());
			ex.printStackTrace();
	     	resultadoOperacion = false;
		}
        finally
        {    
        	HibernateUtil.cerrarSessionEnHilo();
        }
     	return resultadoOperacion;
    }

    /**
     * Registrar personaje.
     *
     * @param paquetePersonaje the paquete personaje
     * @param paqueteUsuario the paquete usuario
     * @return true, if successful
     */
    public boolean registrarPersonaje(final PaquetePersonaje paquetePersonaje, final PaqueteUsuario paqueteUsuario) {    	
    	boolean resultadoOperacion;
        HibernateUtil.abrirSessionEnHilo();
        ControladorUsuario ctrlUsuario = new ControladorUsuario();
        try 
        {
        	EUsuario u = ctrlUsuario.buscarPorId(paqueteUsuario.getUsername());
        	
        	EPersonaje ePersonaje = new EPersonaje();
        	ePersonaje.setCasta(paquetePersonaje.getCasta());
        	ePersonaje.setRaza(paquetePersonaje.getRaza());
        	ePersonaje.setFuerza(paquetePersonaje.getFuerza());
        	ePersonaje.setDestreza(paquetePersonaje.getDestreza());
    		ePersonaje.setInteligencia(paquetePersonaje.getInteligencia());
    		ePersonaje.setSaludTope(paquetePersonaje.getSaludTope());
    		ePersonaje.setEnergiaTope(paquetePersonaje.getEnergiaTope());
    		ePersonaje.setNombre(paquetePersonaje.getNombre());
    		ePersonaje.setPuntosSkill(paquetePersonaje.getPuntosSkill());

        	u.setPersonaje(new EPersonaje());

        	ctrlUsuario.guardar(u);
        	
    		Servidor.appendLog(MensajesLog.personajeRegistradoExitosamente(paquetePersonaje.getNombre()));
        	resultadoOperacion = true;
		} 
        catch (Exception ex) 
        {
    		Servidor.appendLog(MensajesLog.personajeErrorGeneralAlRegistrar()+paquetePersonaje.getNombre());
        	System.err.println(ex.getMessage());
			ex.printStackTrace();
	     	resultadoOperacion = false;
		}
        finally
        {    
        	HibernateUtil.cerrarSessionEnHilo();
        }
     	return resultadoOperacion;
    	
    }

    /**
     * Loguear usuario.
     *
     * @param user the user
     * @return true, if successful
     */
    public boolean loguearUsuario(final PaqueteUsuario user) 
    {
    	HibernateUtil.abrirSessionEnHilo();
    	ControladorUsuario ctrl = new ControladorUsuario();
    	boolean valido = ctrl.validarUsuario(user.getUsername(),user.getPassword());
    	if(valido)
    		Servidor.appendLog(MensajesLog.ingresoExitoso(user.getUsername()));
    	else
    		Servidor.appendLog(MensajesLog.ingresoFallido(user.getUsername()));
    	HibernateUtil.cerrarSessionEnHilo();
    	return valido;
    }

    /**
     * Actualizar personaje.
     *
     * @param paquetePersonaje the paquete personaje
     */
    public void actualizarPersonaje(final PaquetePersonaje paquetePersonaje) {
        /*
         * Se agrego una instruccion para que se actualizen los puntos de skill ya
         * que, previamente este dato no se actualizada y cargaba 3 puntos cada vez
         * que se abría la ventana de AsignarSkills. AVERIGUAR COMO DIFICAR
         * StActualizarPersonaje (?.
         */
        try {
            int i = 2;
            int j = 1;
            PreparedStatement stActualizarPersonaje = connect.prepareStatement(
                    "UPDATE personaje SET fuerza=?, destreza=?, inteligencia=?, saludTope=?, energiaTope=?, experiencia=?, nivel=?, puntosSkill=? "
                            + "  WHERE idPersonaje=?");

            stActualizarPersonaje.setInt(COLUMNA_1, paquetePersonaje.getFuerza());
            stActualizarPersonaje.setInt(COLUMNA_2, paquetePersonaje.getDestreza());
            stActualizarPersonaje.setInt(COLUMNA_3, paquetePersonaje.getInteligencia());
            stActualizarPersonaje.setInt(COLUMNA_4, paquetePersonaje.getSaludTope());
            stActualizarPersonaje.setInt(COLUMNA_5, paquetePersonaje.getEnergiaTope());
            stActualizarPersonaje.setInt(COLUMNA_6, paquetePersonaje.getExperiencia());
            stActualizarPersonaje.setInt(COLUMNA_7, paquetePersonaje.getNivel());
            stActualizarPersonaje.setInt(COLUMNA_8, paquetePersonaje.getPuntosSkill());
            stActualizarPersonaje.setInt(COLUMNA_9, paquetePersonaje.getId());
            stActualizarPersonaje.executeUpdate();

            PreparedStatement stDameItemsID = connect.prepareStatement("SELECT * FROM mochila WHERE idMochila = ?");
            stDameItemsID.setInt(1, paquetePersonaje.getId());
            ResultSet resultadoItemsID = stDameItemsID.executeQuery();
            PreparedStatement stDatosItem = connect.prepareStatement("SELECT * FROM item WHERE idItem = ?");
            ResultSet resultadoDatoItem = null;
            paquetePersonaje.eliminarItems();

            while (j <= 9) {
                if (resultadoItemsID.getInt(i) != -1) {
                    stDatosItem.setInt(1, resultadoItemsID.getInt(i));
                    resultadoDatoItem = stDatosItem.executeQuery();

                    paquetePersonaje.anadirItem(resultadoDatoItem.getInt("idItem"),
                            resultadoDatoItem.getString("nombre"), resultadoDatoItem.getInt("wereable"),
                            resultadoDatoItem.getInt("bonusSalud"), resultadoDatoItem.getInt("bonusEnergia"),
                            resultadoDatoItem.getInt("bonusFuerza"), resultadoDatoItem.getInt("bonusDestreza"),
                            resultadoDatoItem.getInt("bonusInteligencia"), resultadoDatoItem.getString("foto"),
                            resultadoDatoItem.getString("fotoEquipado"));
                }
                i++;
                j++;
            }
            Servidor.log.append("El personaje " + paquetePersonaje.getNombre() + " se ha actualizado con éxito."
                    + System.lineSeparator());
        } catch (SQLException e) {
            Servidor.log.append("Fallo al intentar actualizar el personaje " + paquetePersonaje.getNombre()
                    + System.lineSeparator());
        }

    }

    /**
     * Gets the personaje.
     *
     * @param user the user
     * @return the personaje
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public PaquetePersonaje getPersonaje(final PaqueteUsuario user) throws IOException {
        int i = 2;
        int j = 0;
        try {

            ControladorUsuario ctrlUsuario = new ControladorUsuario();
            EUsuario u = ctrlUsuario.buscarPorId(user.getUsername());
            EPersonaje p = u.getPersonaje();
            
            PaquetePersonaje personaje = new PaquetePersonaje();

            personaje.setId(p.getId());
            personaje.setRaza(p.getRaza());
            personaje.setCasta(p.getCasta());
            personaje.setFuerza(p.getFuerza());
            personaje.setInteligencia(p.getInteligencia());
            personaje.setDestreza(p.getDestreza());
            personaje.setEnergiaTope(p.getEnergiaTope());
            personaje.setSaludTope(p.getSaludTope());
            personaje.setNombre(p.getNombre());
            personaje.setExperiencia(p.getExperiencia());
            personaje.setNivel(p.getNivel());
            personaje.setPuntosSkill(p.getPuntosSkill());
            
            EMochila m = p.getMochila();
            //personaje.anadirItem(idItem, nombre, wearLocation, bonusSalud, bonusEnergia, bonusAtaque, bonusDefensa, bonusMagia, foto, fotoEquipado);
//            personaje.anadirItem(m.getItem1(), m.getItem1().getNombre() ,  m.getItem1().getWereable(),
  //          		m.getItem1().getBonusSalud(),m.getItem1().getBonusEnergia(),m.getItem1().getBonusAtaque(),m.getItem1().get);
            
            // Devuelvo el paquete personaje con sus datos
            return personaje;

        } catch (SQLException ex) {
            Servidor.log
                    .append("Fallo al intentar recuperar el personaje " + user.getUsername() + System.lineSeparator());
            Servidor.log.append(ex.getMessage() + System.lineSeparator());
        }

        return new PaquetePersonaje();
    }

    /**
     * Gets the usuario.
     *
     * @param usuario the usuario
     * @return the usuario
     */
    public PaqueteUsuario getUsuario(final String usuario) {
    	HibernateUtil.abrirSessionEnHilo();
    	ControladorUsuario ctrl = new ControladorUsuario();
    	PaqueteUsuario paqueteUsuario = new PaqueteUsuario();
    	try 
    	{
    		EUsuario eUsr = ctrl.buscarPorId(usuario);
    		paqueteUsuario.setUsername(usuario);
    		paqueteUsuario.setPassword(eUsr.getPassword());//no se deeberia pasar la contrasenia. cambiar aparte a guardar hash
    		paqueteUsuario.setIdPj(eUsr.getPersonaje().getId());
    	} 
    	catch (Exception ex) 
    	{
    		Servidor.appendLog(MensajesLog.errorAlIntentarRecuperarElUsuario(usuario));
    		Servidor.appendLog(ex.getMessage());
    	}
    	finally
    	{    
    		HibernateUtil.cerrarSessionEnHilo();
    	}
    	return paqueteUsuario;
    }

    /**
     * Actualizar inventario.
     *
     * @param paquetePersonaje the paquete personaje
     */
    public void actualizarInventario(final PaquetePersonaje paquetePersonaje) {
    	int i = 0;
    	HibernateUtil.abrirSessionEnHilo();
    	ControladorMochila ctrl = new ControladorMochila();
    	try 
    	{
    		EMochila mochila = new EMochila();
    		mochila.setId(paquetePersonaje.getId());
    		while (i < paquetePersonaje.getCantItems()) {
    			mochila.setItem(i+1,paquetePersonaje.getItemID(i));
    			i++;
    		}
    		if (paquetePersonaje.getCantItems() < 9) {
    			int itemGanado = new Random().nextInt(29);
    			itemGanado += 1;
    			mochila.setItem(paquetePersonaje.getCantItems() + 1, itemGanado);
    		} 
    		ctrl.actualizar(mochila);
    	} 
    	catch (Exception ex) 
    	{
    		Servidor.appendLog(MensajesLog.inventarioErrorGeneralAlActualizar(paquetePersonaje.getNombre()));
    		System.err.println(ex.getMessage());
    		ex.printStackTrace();
    	}
    	finally
    	{    
    		HibernateUtil.cerrarSessionEnHilo();
    	}
    }

    /**
     * Actualizar inventario.
     *
     * @param idPersonaje the id personaje
     */
    public void actualizarInventario(final int idPersonaje) {
    	actualizarInventario(Servidor.getPersonajesConectados().get(idPersonaje));
    }

    /**
     * Actualizar personaje subio nivel.
     *
     * @param paquetePersonaje the paquete personaje
     */
    public void actualizarPersonajeSubioNivel(final PaquetePersonaje paquetePersonaje) {
        HibernateUtil.abrirSessionEnHilo();
        ControladorPersonaje ctrl = new ControladorPersonaje();
        try 
        {
        	EPersonaje ePersonaje = new EPersonaje();
        	ePersonaje.setCasta(paquetePersonaje.getCasta());
        	ePersonaje.setRaza(paquetePersonaje.getRaza());
        	ePersonaje.setFuerza(paquetePersonaje.getFuerza());
        	ePersonaje.setDestreza(paquetePersonaje.getDestreza());
    		ePersonaje.setInteligencia(paquetePersonaje.getInteligencia());
    		ePersonaje.setSaludTope(paquetePersonaje.getSaludTope());
    		ePersonaje.setEnergiaTope(paquetePersonaje.getEnergiaTope());
    		ePersonaje.setNombre(paquetePersonaje.getNombre());
    		ePersonaje.setPuntosSkill(paquetePersonaje.getPuntosSkill());
    		ePersonaje.setExperiencia(paquetePersonaje.getExperiencia());
    		ePersonaje.setNivel(paquetePersonaje.getNivel());
        	
        	ctrl.actualizar(ePersonaje);
        	Servidor.appendLog(MensajesLog.personajeActualizadoExitosamente(paquetePersonaje.getNombre()));
		} 
        catch (Exception ex) 
        {
    		Servidor.appendLog(MensajesLog.personajeErrorGeneralAlActualizar(paquetePersonaje.getNombre()));
        	System.err.println(ex.getMessage());
			ex.printStackTrace();
		}
        finally
        {    
        	HibernateUtil.cerrarSessionEnHilo();
        }
    }
}
