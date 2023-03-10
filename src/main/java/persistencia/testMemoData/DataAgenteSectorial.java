package persistencia.testMemoData;

import domain.EntidadPersistente;
import domain.Organizacion.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataAgenteSectorial {
        private static List<AgenteSectorial> agentes = new ArrayList<>();

        public static List<EntidadPersistente> getList(){
            if(agentes.size() == 0) {

                AgenteSectorial agente = new AgenteSectorial();
                agente.setNombre("Agente1");
                agente.setPeriodo("Anual");

                agente.setEmail("joseagente@gmail.com");
                agente.setSectorTerritorial(new SectorTerritorial("Mendoza", TipoSectorTerritorial.provincia));
                addAll(agente);
            }
            return (List<EntidadPersistente>)(List<?>) agentes;
        }

        private static void addAll(AgenteSectorial ... agente){
            Collections.addAll(DataAgenteSectorial.agentes, agente);}
    }
