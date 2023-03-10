package persistencia.factories;

import config.Config;
import domain.Organizacion.Miembro;
import domain.Trayecto.MedioTransporte;
import persistencia.daos.DAO;
import persistencia.daos.DAOHibernate;
import persistencia.daos.DAOMemoria;
import persistencia.repositorio.RepoMiembro;
import persistencia.repositorio.RepoTransporte;
import persistencia.testMemoData.Data;

public class FactoryRepoTransporte {

        private static RepoTransporte repo;

        static {
            repo = null;
        }

        public static RepoTransporte get() {
            if (repo == null) {
                if (Config.useDataBase) {
                    DAO<MedioTransporte> dao = new DAOHibernate<>(MedioTransporte.class);
                    repo = new RepoTransporte(dao);
                } else {
                    repo = new RepoTransporte(new DAOMemoria<>(Data.getData(MedioTransporte.class)));
                }
            }
            return repo;
        }

}
