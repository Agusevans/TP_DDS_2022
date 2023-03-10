package persistencia.factories;

import config.Config;
import domain.Actividad.Actividad;
import persistencia.daos.DAO;
import persistencia.daos.DAOHibernate;
import persistencia.daos.DAOMemoria;
import persistencia.repositorio.RepoActividad;
import persistencia.testMemoData.Data;

public class FactoryRepoActividad {
    private static RepoActividad repo;

    static {
        repo = null;
    }

    public static RepoActividad get() {
        if (repo == null) {
            if (Config.useDataBase) {
                DAO<Actividad> dao = new DAOHibernate<>(Actividad.class);
                repo = new RepoActividad(dao);
            } else {
                repo = new RepoActividad(new DAOMemoria<>(Data.getData(Actividad.class)));
            }
        }
        return repo;
    }
}
