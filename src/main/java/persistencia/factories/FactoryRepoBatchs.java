package persistencia.factories;

import config.Config;
import domain.Actividad.BatchDatosActividad;
import persistencia.daos.DAO;
import persistencia.daos.DAOHibernate;
import persistencia.daos.DAOMemoria;
import persistencia.repositorio.RepoBatch;
import persistencia.testMemoData.Data;

public class FactoryRepoBatchs {
    private static RepoBatch repo;

    static {
        repo = null;
    }

    public static RepoBatch get() {
        if (repo == null) {
            if (Config.useDataBase) {
                DAO<BatchDatosActividad> dao = new DAOHibernate<>(BatchDatosActividad.class);
                repo = new RepoBatch(dao);
            } else {
                repo = new RepoBatch(new DAOMemoria<>(Data.getData(BatchDatosActividad.class)));
            }
        }
        return repo;
    }
}
