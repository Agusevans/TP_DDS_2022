package persistencia.testMemoData.Entrega5;

import domain.Organizacion.Organizacion;
import org.junit.Before;
import org.junit.Test;

public class DataReader {
    DataOrganizaciones a;
    @Before
    void init(){
        a = new DataOrganizaciones();
    }
    @Test
    void read(){
        System.out.println(a.getOrg1().id);
    }
}
