package persistencia.factories;

import config.Config;
import domain.Organizacion.Organizacion;
import persistencia.daos.DAO;
import persistencia.daos.DAOHibernate;
import persistencia.daos.DAOMemoria;
import persistencia.repositorio.RepoOrganizacion;
import persistencia.testMemoData.Data;

public class FactoryRepoOrganizacion {
    private static RepoOrganizacion repo;

    static {
        repo = null;
    }

    public static RepoOrganizacion get() {
        if (repo == null) {
            if (Config.useDataBase) {
                DAO<Organizacion> dao = new DAOHibernate<>(Organizacion.class);
                repo = new RepoOrganizacion(dao);
            } else {
                repo = new RepoOrganizacion(new DAOMemoria<>(Data.getData(Organizacion.class)));
            }
        }
        return repo;
    }
}
