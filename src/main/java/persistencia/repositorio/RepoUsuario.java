package persistencia.repositorio;

import domain.Actividad.BatchDatosActividad;
import domain.Organizacion.Organizacion;
import domain.Organizacion.Usuario;
import persistencia.BusquedaCondicional;
import persistencia.EntityManagerHelper;
import persistencia.daos.DAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class RepoUsuario extends Repositorio<Usuario> {

    public RepoUsuario(DAO<Usuario> dao) {
        super(dao);
    }

    public Boolean existe(String nombreDeUsuario, String contrasenia){
        return buscarUsuario(nombreDeUsuario, contrasenia) != null;
    }

    public Usuario buscarUsuario(String nombreDeUsuario, String contrasenia){
        return this.dao.buscar(condicionUsuarioYContrasenia(nombreDeUsuario, contrasenia));
    }
    private BusquedaCondicional condicionUsuarioYContrasenia(String nombreDeUsuario, String contrasenia){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Usuario> usuarioQuery = criteriaBuilder.createQuery(Usuario.class);

        Root<Usuario> condicionRaiz = usuarioQuery.from(Usuario.class);

        Predicate condicionNombreDeUsuario = criteriaBuilder.equal(condicionRaiz.get("nombreUsuario"), nombreDeUsuario);
        Predicate condicionContrasenia = criteriaBuilder.equal(condicionRaiz.get("contrasenia"), contrasenia);

        Predicate condicionExisteUsuario = criteriaBuilder.and(condicionNombreDeUsuario, condicionContrasenia);

        usuarioQuery.where(condicionExisteUsuario);

        return new BusquedaCondicional(null, usuarioQuery);
    }

    public void borrarUsuariosDeOrg(Organizacion org){
        CriteriaBuilder criteriaBuilder = EntityManagerHelper.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
        Root<Usuario> root = criteriaQuery.from(Usuario.class);

        List<Predicate> predicatesList = new ArrayList<>();
        predicatesList.add(criteriaBuilder.equal(root.get("organizacion").get("id"), org.getId()));
        Predicate[] finalPredicates = new Predicate[predicatesList.size()];
        predicatesList.toArray(finalPredicates);
        criteriaQuery.where(finalPredicates);

        List<Usuario> result = EntityManagerHelper.getEntityManager()
                .createQuery(criteriaQuery)
                .getResultList();

        for (Usuario usuario : result) {
            this.borrar(usuario);
        }
    }
}
