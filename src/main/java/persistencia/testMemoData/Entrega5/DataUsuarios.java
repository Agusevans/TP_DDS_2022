package persistencia.testMemoData.Entrega5;

import domain.EntidadPersistente;
import domain.Organizacion.Usuario;
import persistencia.testMemoData.DataOrg;

import java.util.ArrayList;
import java.util.List;

public class DataUsuarios {
    static Usuario usuario = new Usuario();
    static Usuario usuario2 = new Usuario();
    static Usuario usuario3 = new Usuario();
    static public DataOrganizaciones a= new DataOrganizaciones();
    static List<Usuario> userList = new ArrayList<>();

    public static List<EntidadPersistente> getList() {
        setUsuario();
        setUsuario2();
        setUsuario3();
        userList.add(usuario);
        userList.add(usuario2);
        userList.add(usuario3);

        return (List<EntidadPersistente>) (List<?>) userList;
    }

    public static void setUsuario() {
        usuario.setId(1);
        usuario.setNombreUsuario("AlaPapa");
        usuario.setContrasenia("AAAAAAAA");
        a.setOrg1();
        usuario.setOrganizacion(a.getOrg1());
    }

    public static void setUsuario2() {
        usuario2.setId(2);
        usuario2.setNombreUsuario("Grandala");
        usuario2.setContrasenia("1234");
        usuario2.setOrganizacion(a.getOrg2());
    }

    public static void setUsuario3() {
        usuario3.setId(3);
        usuario3.setNombreUsuario("APOLA");
        usuario3.setContrasenia("AAAbvcAAAA");
        usuario3.setOrganizacion(a.getOrg3());
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public Usuario getUsuario2() {
        return usuario2;
    }

    public Usuario getUsuario3() {
        return usuario3;
    }
    public DataUsuarios(){
        setUsuario();
        setUsuario2();
        setUsuario3();
    }
}
