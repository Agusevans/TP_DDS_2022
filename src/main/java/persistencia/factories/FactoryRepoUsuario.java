package persistencia.factories;

import config.Config;
import domain.Organizacion.Usuario;
import persistencia.daos.DAO;
import persistencia.daos.DAOHibernate;
import persistencia.daos.DAOMemoria;
import persistencia.repositorio.RepoUsuario;
import persistencia.testMemoData.Data;

public class FactoryRepoUsuario {
        private static RepoUsuario repo;

        static {
                repo = null;
        }

        public static RepoUsuario get() {
                if (repo == null) {
                        if (Config.useDataBase) {
                                DAO<Usuario> dao = new DAOHibernate<>(Usuario.class);
                                repo = new RepoUsuario(dao);
                        } else {
                                repo = new RepoUsuario(new DAOMemoria<>(Data.getData(Usuario.class)));
                        }
                }
                return repo;
        }

}
