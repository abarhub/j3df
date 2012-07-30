/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.j3df.main;

import org.j3df.modeldoc.Racine;
import org.j3df.moteur3d.MoteurRendu;

/**
 *
 * @author Alain
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Lecture imp;
        String rep,nom_fichier;
        //rep="C:\\Users\\Alain\\Documents\\NetBeansProjects\\J3dXml\\doc";
	    rep="C:\\projet\\j3df\\doc";
        //nom_fichier=rep+"\\test1.xml";
        //nom_fichier=rep+"\\test2.xml";
        //nom_fichier=rep+"\\test3.xml";
		nom_fichier=rep+"\\test4.xml";
	    //nom_fichier=rep+"\\test4.json";
	    //nom_fichier=rep+"\\test5.xml";
        System.out.println("Lecture du fichier : "+nom_fichier);
        //imp=new LectureXML(nom_fichier);
	    imp=LectureGenerique.lecture(nom_fichier);
        if(imp.isErreur())
        {
            System.out.println("Erreur:"+imp.getErreurs().getMsgErreur());
        }
        else
        {
            System.out.println("Aucune erreur trouvé");
            affiche(imp.getRacine());
        }
    }

    private static void affiche(Racine racine) {
        //Moteur moteur;
        //moteur=new Moteur(racine);
        //moteur.demarrage();
        MoteurRendu.demarrage(racine);
    }

}
