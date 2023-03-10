package persistencia.repositorio;

import domain.Organizacion.Miembro;
import domain.Organizacion.Organizacion;
import domain.Trayecto.MedioTransporte;
import domain.Trayecto.Parada;
import domain.Trayecto.TransporteContratado;
import domain.Trayecto.TransportePublico;
import persistencia.EntityManagerHelper;
import persistencia.daos.DAO;

import java.util.List;

public class RepoTransporte extends Repositorio<MedioTransporte>{

    public RepoTransporte(DAO<MedioTransporte> dao) {
        super(dao);
    }

    public List<TransportePublico> obtenerPublicos() {

        String query = "from TransportePublico";

        return EntityManagerHelper.getEntityManager().createQuery(query,TransportePublico.class).getResultList();
    }


    public List<Parada> buscarParadas() {
        String query = "from Parada";

        return EntityManagerHelper.getEntityManager().createQuery(query,Parada.class).getResultList();

    }

    public void borrarTodos(){
        List<MedioTransporte> transportes = this.buscarTodos();
        for (MedioTransporte transporte : transportes) {
            this.borrar(transporte);
        }
    }
}
