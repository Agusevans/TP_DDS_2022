package persistencia.testMemoData;

import domain.EntidadPersistente;
import domain.Organizacion.Miembro;
import domain.Organizacion.Organizacion;
import domain.Organizacion.Usuario;
import persistencia.testMemoData.Entrega5.*;

import java.util.ArrayList;
import java.util.List;

public class Data {

    public static List<EntidadPersistente> getData(Class type) {
        List<EntidadPersistente> entidades = new ArrayList<>();
        if (type.getName().equals(Miembro.class.getName())) {
            entidades = DataMiembro.getList();
        } else {
            if (type.getName().equals(Organizacion.class.getName())) {
                entidades = DataOrganizaciones.getList();
            }
            if (type.getName().equals(Usuario.class.getName())) {
                entidades = DataUsuarios.getList();
            }
        }

        return entidades;
    }
}
