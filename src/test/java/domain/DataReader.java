package domain;

import domain.Organizacion.Organizacion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import persistencia.testMemoData.Entrega5.DataOrganizaciones;
import persistencia.testMemoData.Entrega5.DataUsuarios;

import java.util.List;

public class DataReader {
    DataOrganizaciones a = new DataOrganizaciones();
    DataUsuarios u = new DataUsuarios();
    @Before
    public void init(){
    }
    @Test
    public void Read(){
        System.out.println(u.getUsuario().getId());
        System.out.println(u.getUsuario().getNombreUsuario());
        System.out.println(u.getUsuario().getContrasenia());
        Assert.assertEquals(1,u.getUsuario().getOrganizacion().getId());
    }

}
