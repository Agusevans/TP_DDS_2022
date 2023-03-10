package domain;

import domain.Organizacion.Organizacion;
import domain.Organizacion.Usuario;
import org.junit.jupiter.api.Test;
import persistencia.EntityManagerHelper;

public class CargaUsuarioTest {

    @Test
    void cargarUsuarioABD()
    {
        Organizacion org = new Organizacion();
        Usuario admin = new Usuario("admin", "admin", org);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(org);
        EntityManagerHelper.getEntityManager().persist(admin);
        EntityManagerHelper.commit();
    }

    @Test
    void recuperarUsuarioCorrectamente()
    {
        Usuario admin = (Usuario) EntityManagerHelper.createQuery("from Usuario where nombreUsuario = 'admin' and contraseña = 'admin'").getSingleResult();
        org.junit.jupiter.api.Assertions.assertTrue(admin.getNombreUsuario().equals("admin") && admin.getContrasenia().equals("admin"));
    }


}
