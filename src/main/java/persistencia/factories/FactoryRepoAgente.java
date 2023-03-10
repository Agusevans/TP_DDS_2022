package persistencia.factories;

import config.Config;
import domain.Organizacion.AgenteSectorial;
import persistencia.daos.DAO;
import persistencia.daos.DAOHibernate;
import persistencia.daos.DAOMemoria;
import persistencia.repositorio.RepoAgenteSectorial;
import persistencia.testMemoData.Data;

public class FactoryRepoAgente {
    private static RepoAgenteSectorial repo;

    static {
        repo = null;
    }

    public static RepoAgenteSectorial get() {
        if (repo == null) {
            if (Config.useDataBase) {
                DAO<AgenteSectorial> dao = new DAOHibernate<>(AgenteSectorial.class);
                repo = new RepoAgenteSectorial(dao);
            } else {
                repo = new RepoAgenteSectorial(new DAOMemoria<>(Data.getData(AgenteSectorial.class)));
            }
        }
        return repo;
    }

}
