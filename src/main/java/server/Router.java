package server;

import middleware.AuthMiddleware;
import persistencia.controllers.*;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {
    private static HandlebarsTemplateEngine engine;

    private static void initEngine() {
        Router.engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
                .withHelper("isTrue", BooleanHelper.isTrue)
                .build();
    }

    public static void init() {
        Router.initEngine();
        Spark.staticFileLocation("/public");
        Router.configure();
    }

    private static void configure(){

        OrganizacionController orgController = new OrganizacionController();
        FactorEmisionController factorEmisionController = new FactorEmisionController();
        AgenteSectorialController agenteSectorialController = new AgenteSectorialController();
        MedicionController medicionController = new MedicionController();
        TrayectoController trayectoController = new TrayectoController();
        AuthMiddleware authMiddleware = new AuthMiddleware();
        BDController bdController = new BDController();

        //AuthMiddleware
        Spark.before("/index", authMiddleware::verificarSesion);
        Spark.before("/miembros", authMiddleware::verificarSesion);
        Spark.before("/miembro/:id_m", authMiddleware::verificarSesion);
        Spark.before("/trayecto", authMiddleware::verificarSesion);
        Spark.before("/carga_mediciones", authMiddleware::verificarSesion);
        Spark.before("/reporte", authMiddleware::verificarSesion);

        //LoginController
        LoginController loginController = new LoginController();
        Spark.get("/", loginController::login, Router.engine);
        Spark.post("/", loginController::loginPost);
        Spark.get("/logout", loginController::logout);
        Spark.get("/index", loginController::mostrarIndex, Router.engine);

        //OrganizacionController
        Spark.post("/organizacion", orgController::createOrg);
        Spark.get("/organizacion/:id_o", orgController::readOrg);
        Spark.put("/organizacion/:id_o", orgController::updateOrg);
        Spark.delete("/organizacion/:id_o", orgController::deleteOrg);

        Spark.post("/organizacion/:id_o/sector/:id_s/miembro", orgController::agregarMiembro);
        Spark.post("/miembro/:id_m/organizacion/:id_o/sector/:id_s", orgController::updateSectorMiembro);

        Spark.get("/organizacion/:id_o/sector/:id_s", orgController::readSect);
        //Spark.post("/organizacion/:id_o/sector/:id_s", orgController::updateSect);
        //Spark.delete("/organizacion/:id_o/sector/:id_s", orgController::deleteSect);
        Spark.get("/miembros", orgController::readMiembros, Router.engine);
        Spark.get("/miembro/:id_m", orgController::readMiembro, Router.engine);

        //TrayectoController
        Spark.get("/trayecto", trayectoController::create, Router.engine);
        Spark.post("/trayecto", trayectoController::save);
        Spark.delete("/trayecto/:id", trayectoController::delete);
        Spark.post(("/miembro/:id_m/trayecto/:id_t"), trayectoController::deleteTrayectoMiembro, Router.engine);
        Spark.get("/success", trayectoController::success, Router.engine);
        Spark.get("/error", trayectoController::error, Router.engine);

        //FactorEmisionController
        Spark.put("/factor_emision/:id", factorEmisionController::update);

        //AgenteSectorialController
        Spark.post("/agente", agenteSectorialController::create);
        Spark.get("/agente/:id", agenteSectorialController::read);
        Spark.put("/agente/:id", agenteSectorialController::update);
        Spark.delete("/agente/:id", agenteSectorialController::delete);
        Spark.post("/organizacion/:id_o/agente/:id_a", agenteSectorialController::asignarAgenteAOrganizacion);

        //MedicionController
        Spark.get("/mediciones",medicionController::filterMed);
        Spark.post("/batch",medicionController::batchAlta);
        Spark.get("/batch/:id",medicionController::leerBatch);
        Spark.delete("/batch/:id",medicionController::batchBaja);
        Spark.get("/reporte", medicionController::reporte, Router.engine);
        Spark.post("/reporte", medicionController::calculoHU, Router.engine);
        Spark.get("/organizacion/:id/calculo_hu", medicionController::apiCalculoHU);

        //BDController
        Spark.post("/bd", bdController::cargarBD);
        Spark.delete("/bd", bdController::borrarBD);

    }
}