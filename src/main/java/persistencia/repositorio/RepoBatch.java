package persistencia.repositorio;

import domain.Actividad.BatchDatosActividad;
import domain.Actividad.DatosActividad;
import domain.Organizacion.Organizacion;
import persistencia.EntityManagerHelper;
import persistencia.daos.DAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class RepoBatch extends Repositorio<BatchDatosActividad>{

    public RepoBatch(DAO<BatchDatosActividad> dao) {
        super(dao);
    }

    public List<DatosActividad> filterDAs(int anio, int mes, int idOrg){
        CriteriaBuilder criteriaBuilder = EntityManagerHelper.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<BatchDatosActividad> criteriaQuery = criteriaBuilder.createQuery(BatchDatosActividad.class);
        Root<BatchDatosActividad> root = criteriaQuery.from(BatchDatosActividad.class);

        List<Predicate> predicatesList = new ArrayList<>();
        //filtro por organizacion
        predicatesList.add(criteriaBuilder.equal(root.get("organizacion").get("id"), idOrg));
        Predicate[] finalPredicates = new Predicate[predicatesList.size()];
        predicatesList.toArray(finalPredicates);
        criteriaQuery.where(finalPredicates);

        List<BatchDatosActividad> result = EntityManagerHelper.getEntityManager()
                .createQuery(criteriaQuery)
                .getResultList();

        List<DatosActividad> datosActividad = new ArrayList<>();
        for (BatchDatosActividad batch : result) {
            for (DatosActividad da : batch.getDatosAct()) {
                if(da.getPeriodoDeImputacion().getMonthValue() == mes && da.getPeriodoDeImputacion().getYear() == anio){
                    datosActividad.add(da);
                }
            }
        }

        return datosActividad;
    }


    public void borrarBatchsDeOrg(Organizacion org) {
        CriteriaBuilder criteriaBuilder = EntityManagerHelper.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<BatchDatosActividad> criteriaQuery = criteriaBuilder.createQuery(BatchDatosActividad.class);
        Root<BatchDatosActividad> root = criteriaQuery.from(BatchDatosActividad.class);

        List<Predicate> predicatesList = new ArrayList<>();
        predicatesList.add(criteriaBuilder.equal(root.get("organizacion").get("id"), org.getId()));
        Predicate[] finalPredicates = new Predicate[predicatesList.size()];
        predicatesList.toArray(finalPredicates);
        criteriaQuery.where(finalPredicates);

        List<BatchDatosActividad> result = EntityManagerHelper.getEntityManager()
                .createQuery(criteriaQuery)
                .getResultList();

        for (BatchDatosActividad batch : result) {
            this.borrar(batch);
        }

    }

}

