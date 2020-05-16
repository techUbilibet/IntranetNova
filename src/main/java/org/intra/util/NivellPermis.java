package org.intra.util;

public enum NivellPermis {
	VEURE_LIMITAT,		// Sol pot veure a excepció d'aquelles dades que es considerin sensibles
	MODIFICAR_LIMITAT,	// Sols pot editar alguns tipus de dades
	VEURE,				// Pot veure però no pot modificar
	MODIFICAR,			// pot modificar a excepció de dades considerades hipersensibles
	ADMINSTRAR			// Pot modificar tot

}
