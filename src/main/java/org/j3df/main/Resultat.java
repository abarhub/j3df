/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.j3df.main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alain
 */
public class Resultat {

    private List<String> liste_erreurs;

    public Resultat()
    {
        liste_erreurs=new ArrayList<String>();
    }

    public void ajoute_erreur(String msg_erreur)
    {
        liste_erreurs.add(msg_erreur);
    }

    public boolean isErreur()
    {
        return !liste_erreurs.isEmpty();
    }

    public void integre(Resultat res)
    {
        if(res!=null)
        {
            liste_erreurs.addAll(res.liste_erreurs);
        }
    }

    public String getMsgErreur()
    {
        if(liste_erreurs==null||liste_erreurs.isEmpty())
            return null;
        else
            return liste_erreurs.get(0);
    }
}
