package persistencia.repositorio;

import domain.Actividad.DatosActividad;
import persistencia.EntityManagerHelper;
import persistencia.daos.DAO;
import spark.Request;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class  RepoDatosActividad extends Repositorio<DatosActividad>{

    public RepoDatosActividad(DAO<DatosActividad> dao) {
        super(dao);
    }

    //Paginado
    public List<DatosActividad> list(int offset, int limit){
        CriteriaBuilder criteriaBuilder = EntityManagerHelper.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<DatosActividad> criteriaQuery = criteriaBuilder.createQuery(DatosActividad.class);
        Root<DatosActividad> root = criteriaQuery.from(DatosActividad.class);
        criteriaQuery.select(root);

        List<DatosActividad> result =
                EntityManagerHelper.getEntityManager()
                        .createQuery(criteriaQuery)
                        .setMaxResults(limit)
                        .setFirstResult(offset)
                        .getResultList();

        return result;
    }

    //Filtro paginado
    public List<DatosActividad> filter(Request request){
        CriteriaBuilder criteriaBuilder = EntityManagerHelper.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<DatosActividad> criteriaQuery = criteriaBuilder.createQuery(DatosActividad.class);
        Root<DatosActividad> root = criteriaQuery.from(DatosActividad.class);

        List<Predicate> predicatesList = new ArrayList<>();

        if(request.queryParams("actividad_id") != null){
            predicatesList.add(criteriaBuilder.equal(root.get("actividad").get("id"), Integer.parseInt(request.queryParams("actividad_id"))));
        }

        if(request.queryParams("tipoConsumo_id") != null){
            predicatesList.add(criteriaBuilder.equal(root.get("medicion").get("tipoConsumo").get("id"), Integer.parseInt(request.queryParams("tipoConsumo_id"))));
        }

        if(request.queryParams("periodoDeImputacion") != null){
            predicatesList.add(criteriaBuilder.equal(root.get("periodoDeImputacion"), LocalDate.parse(request.queryParams("periodoDeImputacion"))));
        }

        Predicate[] finalPredicates = new Predicate[predicatesList.size()];
        predicatesList.toArray(finalPredicates);
        criteriaQuery.where(finalPredicates);


        List<DatosActividad> result = EntityManagerHelper.getEntityManager()
                .createQuery(criteriaQuery)
                .setMaxResults(Integer.parseInt(request.queryParams("limit")))
                .setFirstResult(Integer.parseInt(request.queryParams("offset")))
                .getResultList();

        return result;
    }

}
