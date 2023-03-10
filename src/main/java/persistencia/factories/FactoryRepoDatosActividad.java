package persistencia.factories;

import config.Config;
import domain.Actividad.DatosActividad;
import persistencia.daos.DAO;
import persistencia.daos.DAOHibernate;
import persistencia.daos.DAOMemoria;
import persistencia.repositorio.RepoDatosActividad;
import persistencia.testMemoData.Data;

public class FactoryRepoDatosActividad {
    private static RepoDatosActividad repo;

    static {
        repo = null;
    }

    public static RepoDatosActividad get() {
        if (repo == null) {
            if (Config.useDataBase) {
                DAO<DatosActividad> dao = new DAOHibernate<>(DatosActividad.class);
                repo = new RepoDatosActividad(dao);
            } else {
                repo = new RepoDatosActividad(new DAOMemoria<>(Data.getData(DatosActividad.class)));
            }
        }
        return repo;
    }
}
