package server;

import persistencia.EntityManagerHelper;
import spark.Spark;
import java.net.URISyntaxException;

public class Server {

    private static int getHerokuAssignedPort() {
        String herokuPort = System.getenv("PORT");
        if (herokuPort != null) {
            return Integer.parseInt(herokuPort);
        }
        return 4567;
    }

    public static void main(String[] args) throws URISyntaxException {
        //Spark.port(9000);
        EntityManagerHelper.getEntityManager();
        Spark.port(getHerokuAssignedPort());

        Router.init();
    }

}