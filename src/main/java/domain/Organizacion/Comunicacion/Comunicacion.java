package domain.Organizacion.Comunicacion;

import domain.Organizacion.AgenteSectorial;

public interface Comunicacion {
    public void notificar(AgenteSectorial agenteTerritorial, Float huella);
}
