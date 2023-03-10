package persistencia.repositorio;

import domain.Organizacion.Sector;
import persistencia.BusquedaCondicional;
import persistencia.daos.DAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RepoSector extends Repositorio<Sector>{

    public RepoSector(DAO<Sector> dao) {
        super(dao);
    }

    /*
    public Sector buscarSector(String nombreSect, int org) {
        return this.dao.buscar(nombreSector(nombreSect, org));
    }


    BusquedaCondicional nombreSector(String nombreSect, int org){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Sector> sectorQuery = criteriaBuilder.createQuery(Sector.class);

        Root<Sector> raizSect = sectorQuery.from(Sector.class);

        Predicate predicado1 = criteriaBuilder.equal(raizSect.get("nombre"), nombreSect);
        Predicate predicado2 = criteriaBuilder.equal(raizSect.get("organizacion"), org); //suponiendo y rogando que tenga el id por FK

        Predicate ambasCondiciones = criteriaBuilder.and(predicado1, predicado2);

        sectorQuery.where(ambasCondiciones);

        return new BusquedaCondicional(null, sectorQuery);
    }*/
}
