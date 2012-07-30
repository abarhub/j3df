package org.j3df.main;

/**
 * Created by IntelliJ IDEA.
 * User: Alain
 * Date: 17/06/11
 * Time: 10:12
 * To change this template use File | Settings | File Templates.
 */
public class LectureGenerique {

	public static final Lecture lecture(String fichier)
	{
		Lecture imp;

		if(fichier!=null&&fichier.toLowerCase().endsWith(".json"))
		{
			return new LectureJSon(fichier);
		}
		else
		{
			return new LectureXML(fichier);
		}
	}
}
