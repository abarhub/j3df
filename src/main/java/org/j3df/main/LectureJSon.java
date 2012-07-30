package org.j3df.main;

import org.j3df.modeldoc.Racine;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: Alain
 * Date: 17/06/11
 * Time: 09:40
 * To change this template use File | Settings | File Templates.
 */
public class LectureJSon implements Lecture {

	private Resultat erreurs;
	private Racine racine;

	public LectureJSon(String fichier) {
        erreurs = new Resultat();
        lecture(fichier);
    }

    private void lecture(String fichier) {
	    BufferedReader buf;
	    String contenu="",s;
	    JSONObject obj_racine;
	    try {
		    buf=new BufferedReader(new FileReader(fichier));
		    while((s = buf.readLine())!=null)
		    {
			    contenu+=s+"\n";
		    }
		    if(contenu.trim().length()==0)
		    {
			    erreurs.ajoute_erreur("Le fichier est vide");
		    }
		    else
		    {
			    obj_racine=new JSONObject(contenu);
			    if(obj_racine.length()==0)
			    {
				    erreurs.ajoute_erreur("Le fichier est vide");
			    }
			    else if(obj_racine.length()!=1)
			    {
				    erreurs.ajoute_erreur("Le fichier n'est pas correcte");
			    }
			    else if(!obj_racine.has("doc"))
			    {
				    erreurs.ajoute_erreur("Le fichier doit avoir pour racine doc");
			    }
		    }
	    } catch (FileNotFoundException e) {
		    e.printStackTrace();
		    erreurs.ajoute_erreur("Erreur pour lire le fichier '"+fichier+"' : "+e.getLocalizedMessage());
	    } catch (IOException e) {
		    e.printStackTrace();
		    erreurs.ajoute_erreur("Erreur pour lire le fichier '"+fichier+"' : "+e.getLocalizedMessage());
	    } catch (JSONException e) {
		    e.printStackTrace();
		    erreurs.ajoute_erreur("Erreur pour lire le fichier '"+fichier+"' : "+e.getLocalizedMessage());
	    }

    }


	public Resultat getErreurs() {
	    return erreurs;
	}

	public Racine getRacine() {
	    return racine;
	}

	public boolean isErreur()
	{
	    return erreurs.isErreur();
	}

}
