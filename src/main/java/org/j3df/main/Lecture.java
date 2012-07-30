package org.j3df.main;

import org.j3df.modeldoc.Racine;

/**
 * Created by IntelliJ IDEA.
 * User: Alain
 * Date: 17/06/11
 * Time: 10:13
 * To change this template use File | Settings | File Templates.
 */
public interface Lecture {

	public boolean isErreur();

	public Resultat getErreurs();

	public Racine getRacine();
}
