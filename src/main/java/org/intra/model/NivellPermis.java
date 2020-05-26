package org.intra.model;

public enum NivellPermis {
	VEURE_LIMITAT,		// Sol pot veure a excepció d'aquelles dades que es considerin sensibles
	VEURE,				// Pot veure però no pot modificar
	MODIFICAR_LIMITAT,	// Sols pot editar alguns tipus de dades
	MODIFICAR,			// pot modificar a excepció de dades considerades hipersensibles
	ADMINSTRAR			// Pot modificar tot

}
