package persitencia.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import persistencia.dao.UsuarioDAO;
import persistencia.entidades.EUsuario;
import persistencia.hibernate.HibernateUtil;

public class UsuarioDAOImplHibernate extends GenericDAOImplHibernate<EUsuario,String> implements UsuarioDAO {
	public boolean existe(String nombreUsuario)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Long> c = cb.createQuery(Long.class);
        Root<EUsuario> r = c.from(EUsuario.class);
        c.select(cb.count(c.from(EUsuario.class)));
        ParameterExpression<String> a = cb.parameter(String.class,"usuario");
        c.where(cb.equal(r.get("usuario"),a));//r.getModel().getName()
        return session.createQuery(c).setParameter("usuario", nombreUsuario).getSingleResult()>0;
	}

	@Override
	public boolean validarUsuario(String username, String password) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Long> c = cb.createQuery(Long.class);
        Root<EUsuario> r = c.from(EUsuario.class);
        c.select(cb.count(c.from(EUsuario.class)));
        ParameterExpression<String> paramNombreUsuario = cb.parameter(String.class,"usuario");
        ParameterExpression<String> paramContrasenia = cb.parameter(String.class,"password");
        c.where(cb.equal(r.get("usuario"),paramNombreUsuario)).where(cb.equal(r.get("password"),paramContrasenia));//r.getModel().getName()
        return session.createQuery(c).setParameter("usuario", username).setParameter("password",password).getSingleResult()>0;
	}
}