package domain.Lectores;

import ar.edu.frba.utn.dds.mihuella.fachada.Medible;
import domain.Actividad.*;
import domain.Organizacion.Miembro;
import domain.Trayecto.MedioTransporte;
import domain.Trayecto.Punto;
import domain.Trayecto.Tramo;
import domain.Trayecto.Trayecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.String.valueOf;

public class LectorCSV implements LectorArchivos {
	private String archivo;
	private	String separador;
	public BufferedReader buffer = null;

	public LectorCSV(){}

	public LectorCSV(String nombreArchivo, String separadorArchivo) {
		archivo = nombreArchivo;
		separador = separadorArchivo;
		try	{
			buffer = new BufferedReader(new FileReader(valueOf(archivo)));
		}catch (Exception error) {
			error.printStackTrace();
		}
	}

	public String[] leerLinea(){
		String[] campos = null;
		String linea = null;
		try {
			 linea = this.buffer.readLine();
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(linea != null)
			campos = linea.split(this.separador);
		return campos;
	}

	private DatosActividad convertirLineaADA(String[] linea, List<Actividad> actividades) {

		DatosActividad datosActividad = new DatosActividad();
		Medicion medicion = new Medicion();

		Actividad actividad = actividades.stream().filter(act -> act.getNombre().equals(linea[0])).findFirst().orElse(null);
		TipoConsumo consumo = actividad.getTiposConsumo().stream().filter(tc -> tc.getNombre().equals(linea[1])).findFirst().orElse(null);

		medicion.setTipoConsumo(consumo);
		medicion.setPeriodicidad(Periodicidad.valueOf(linea[3]));
		medicion.setValor(Float.valueOf(linea[2]));

		datosActividad.setActividad(actividad);
		datosActividad.setMedicion(medicion);
		datosActividad.setPeriodoDeImputacion(LocalDate.parse(linea[4]));

		return datosActividad;
	}
	@Override
	public Collection<Medible> leerMediciones(List<Actividad> actividades) {

		Collection<Medible> mediciones = new ArrayList<>();
		String[] linea = leerLinea();

		while(linea != null) {
			DatosActividad medicion = convertirLineaADA(linea, actividades);
			mediciones.add(medicion);

			linea = leerLinea();
		}

		return mediciones;
	}

	public void leerYAgregarTrayectos(List<Miembro> miebros, List<MedioTransporte> transportes) throws IOException {

		//formato del CSV: cant integrantes, dni integrantes ..., veces realizado, tramos (medio transporte, punto inicio, punto fin) ...

		String[] linea = this.leerLinea();
		while(linea != null) {
			Trayecto trayecto = new Trayecto();

			int cantidadIntegrantes = Integer.parseInt(linea[0]);
			for(int i = 1; i <= cantidadIntegrantes; i++){
				int dni = Integer.parseInt(linea[i]);
				Miembro miembro = miebros.stream().filter(m -> m.getNroDocumento() == dni).findFirst().orElse(null);
				miembro.agregarTrayecto(trayecto);
			}

			trayecto.setVecesRealizadoXMes(Integer.parseInt(linea[cantidadIntegrantes + 1]));

			for (int i = cantidadIntegrantes + 2; i < linea.length; i += 5) {
				String nombreMedioTransporte = linea[i];
				Punto puntoInicio = new Punto(Float.valueOf(linea[i + 1]), Float.valueOf(linea[i + 2]));
				Punto puntoFin = new Punto(Float.valueOf(linea[i + 3]), Float.valueOf(linea[i + 4]));

				MedioTransporte medioTransporte = transportes.stream().filter(m -> m.getNombre().equals(nombreMedioTransporte)).findFirst().orElse(null);
				trayecto.agregarTramo(new Tramo(medioTransporte, puntoInicio, puntoFin));
			}

			linea = leerLinea();
		}

	}

	private DatosActividad parsearLinea(String[] linea, List<Actividad> actividades) {

		Actividad actividad = actividades.stream().filter(act -> act.getNombre().equals(linea[0])).findFirst().orElse(null);
		TipoConsumo consumo = actividad.getTipoConsumo(linea[1]);
		Medicion medicion = new Medicion(consumo, Float.valueOf(linea[2]), Periodicidad.valueOf(linea[3]));
		LocalDate periodo = LocalDate.parse(linea[4]);

		return new DatosActividad(actividad, medicion, periodo);
	}

	public BatchDatosActividad leerBatch(List<Actividad> actividades) {

		BatchDatosActividad batch = new BatchDatosActividad();

		String[] linea = leerLinea();
		while(linea != null) {
			DatosActividad medicion = parsearLinea(linea, actividades);
			batch.getDatosAct().add(medicion);

			linea = leerLinea();
		}

		return batch;
	}

	public BatchDatosActividad leerBatchDeString(String csv, List<Actividad> actividades) {

		BatchDatosActividad batch = new BatchDatosActividad();

		String[] mediciones = csv.split("\r\n");
		for(String medicion : mediciones) {
			String[] linea = medicion.split(",");
			DatosActividad datosActividad = parsearLinea(linea, actividades);
			batch.getDatosAct().add(datosActividad);
		}

		return batch;
	}


}
