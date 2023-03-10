package persistencia.repositorio;

import domain.Organizacion.Miembro;
import domain.Organizacion.Organizacion;
import domain.Trayecto.Trayecto;
import persistencia.BusquedaCondicional;
import persistencia.EntityManagerHelper;
import persistencia.daos.DAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class RepoMiembro extends Repositorio<Miembro>{

    public RepoMiembro(DAO<Miembro> dao) {
        super(dao);
    }

    public List<Miembro> buscarMiembrosDeOrg(int idOrg) {

        String query = "from Organizacion where id = " + idOrg;
        Organizacion org = EntityManagerHelper.getEntityManager().createQuery(query,Organizacion.class).getSingleResult();

        return org.obtenerMiembros();
    }

    public void actualizarMiembros(List<Miembro> miembros){
        for (Miembro miembro : miembros) {
            this.actualizar(miembro);
        }
    }

    //esta mal hecha esta busqueda, revisar
    public Miembro buscarMiembro(int nro_doc) {
        Miembro miembro = null;
        try{
            miembro = this.dao.buscar(miembro(nro_doc));
        }
        catch (Exception e){
            return null;
        }

        return miembro;
    }

    BusquedaCondicional miembro(int nro_doc){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Miembro> agenteQuery = criteriaBuilder.createQuery(Miembro.class);

        Root<Miembro> raiz = agenteQuery.from(Miembro.class);

        Predicate predicado = criteriaBuilder.equal(raiz.get("nroDocumento"), nro_doc);

        agenteQuery.where(predicado);

        return new BusquedaCondicional(null, agenteQuery);
    }

    public List<Miembro> miebrosDeTrayecto(Trayecto trayecto) {

        List<Miembro> miembros = new ArrayList<>(); //se deberia hacer con la criteria
        for(Miembro miembro : this.buscarTodos()){
            if(miembro.contieneElTrayecto(trayecto)){
                miembros.add(miembro);
            }
        }

        /*
        String query = "from Miembro where id in (select miembro_id from miembro_trayecto where trayecto_id = " + idTrayecto + ")";
        List<Miembro> miembros = (List<Miembro>) EntityManagerHelper.getEntityManager().createQuery(query,Miembro.class);
        */

        return miembros;
    }

    public void borrarTrayecto(Miembro miembro, Trayecto trayecto) {
        miembro.borrarTrayecto(trayecto);
        this.actualizar(miembro);
    }

    public void borrarTodos(){
        List<Miembro> miembros = this.buscarTodos();
        for (Miembro miembro : miembros) {
            miembro.borrarTrayectos();
            this.actualizar(miembro);
            this.borrar(miembro);
        }
    }

}

