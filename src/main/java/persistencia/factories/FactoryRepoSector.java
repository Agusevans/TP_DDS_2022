package persistencia.factories;

import config.Config;
import domain.Organizacion.Sector;
import persistencia.daos.DAO;
import persistencia.daos.DAOHibernate;
import persistencia.daos.DAOMemoria;
import persistencia.repositorio.RepoSector;
import persistencia.testMemoData.Data;

public class FactoryRepoSector {
    private static RepoSector repo;

    static {
        repo = null;
    }

    public static RepoSector get() {
        if (repo == null) {
            if (Config.useDataBase) {
                DAO<Sector> dao = new DAOHibernate<>(Sector.class);
                repo = new RepoSector(dao);
            } else {
                repo = new RepoSector(new DAOMemoria<>(Data.getData(Sector.class)));
            }
        }
        return repo;
    }

}
