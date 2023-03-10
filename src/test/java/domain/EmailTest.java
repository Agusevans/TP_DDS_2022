package domain;

import domain.Organizacion.AgenteSectorial;
import domain.Organizacion.Organizacion;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class EmailTest {

    @Test
    public void enviarMail() throws IOException {

        AgenteSectorial agenteSectorial = new AgenteSectorial();
        agenteSectorial.setEmail("javiquintana99@hotmail.com");

        Organizacion organizacion = new Organizacion();

        agenteSectorial.agregarOrganizaciones(organizacion);
        agenteSectorial.obtenerHCTerritorial();

    }
}
