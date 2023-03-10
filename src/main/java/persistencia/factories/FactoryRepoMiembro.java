package persistencia.factories;

import config.Config;
import domain.Organizacion.Miembro;
import persistencia.daos.DAO;
import persistencia.daos.DAOHibernate;
import persistencia.daos.DAOMemoria;
import persistencia.repositorio.RepoMiembro;
import persistencia.testMemoData.Data;

public class FactoryRepoMiembro {
    private static RepoMiembro repo;

    static {
        repo = null;
    }

    public static RepoMiembro get() {
        if (repo == null) {
            if (Config.useDataBase) {
                DAO<Miembro> dao = new DAOHibernate<>(Miembro.class);
                repo = new RepoMiembro(dao);
            } else {
                repo = new RepoMiembro(new DAOMemoria<>(Data.getData(Miembro.class)));
            }
        }
        return repo;
    }
}
