package persistencia.repositorio;

import domain.Organizacion.AgenteSectorial;
import domain.Organizacion.Miembro;
import domain.Organizacion.Organizacion;
import persistencia.BusquedaCondicional;
import persistencia.daos.DAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class RepoAgenteSectorial extends Repositorio<AgenteSectorial> {

    public RepoAgenteSectorial(DAO<AgenteSectorial> dao) {
        super(dao);
    }

    public AgenteSectorial buscarAgente(String nombre) {
        return this.dao.buscar(nombreAgente(nombre));
    }

    BusquedaCondicional nombreAgente(String nombre){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<AgenteSectorial> agenteQuery = criteriaBuilder.createQuery(AgenteSectorial.class);

        Root<AgenteSectorial> raiz = agenteQuery.from(AgenteSectorial.class);

        Predicate predicado = criteriaBuilder.equal(raiz.get("nombre"), nombre);

        agenteQuery.where(predicado);

        return new BusquedaCondicional(null, agenteQuery);
    }

    public void quitarOrganizacion(Organizacion org, AgenteSectorial agente){
        agente.quitarOrganizacion(org);
        this.actualizar(agente);
    }

    public void actualizarAgente(AgenteSectorial agenteViejo, AgenteSectorial agenteNuevo){
        if(agenteNuevo.getNombre() != null)
            agenteViejo.setNombre(agenteNuevo.getNombre());
        if(agenteNuevo.getEmail() != null)
            agenteViejo.setEmail(agenteNuevo.getEmail());
        if(agenteNuevo.getPeriodo() != null)
            agenteViejo.setPeriodo(agenteNuevo.getPeriodo());

        this.actualizar(agenteViejo);
    }

    public void borrarTodos(){
        List<AgenteSectorial> agentes = this.buscarTodos();
        for (AgenteSectorial agente : agentes) {
            this.borrar(agente);
        }
    }
}
