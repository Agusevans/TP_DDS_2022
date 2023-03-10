package persistencia.repositorio;

import domain.Actividad.Actividad;
import domain.Organizacion.Usuario;
import persistencia.BusquedaCondicional;
import persistencia.daos.DAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RepoActividad extends Repositorio<Actividad> {

    public RepoActividad(DAO<Actividad> dao) {
        super(dao);
    }

    public Actividad buscarActividadTrayectos(){
        return this.buscarActividad("Traslado de miembros de la Org");
    }

    public Actividad buscarActividad(String nombre){
        return this.dao.buscar(condicionNombreActividad(nombre));
    }

    private BusquedaCondicional condicionNombreActividad(String nombre){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Actividad> actividadQuery = criteriaBuilder.createQuery(Actividad.class);

        Root<Actividad> condicionRaiz = actividadQuery.from(Actividad.class);
        Predicate condicionNombreActividad = criteriaBuilder.equal(condicionRaiz.get("nombre"), nombre);
        actividadQuery.where(condicionNombreActividad);

        return new BusquedaCondicional(null, actividadQuery);
    }

    public void borrarTodas(){
        List<Actividad> actividades = this.buscarTodos();
        for (Actividad actividad : actividades) {
            this.borrar(actividad);
        }
    }

}
