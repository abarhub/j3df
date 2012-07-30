/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.j3df.main;

import java.io.*;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.filter.*;
import java.util.List;

import org.j3df.modeldoc.Camera;
import org.j3df.modeldoc.Couleur;
import org.j3df.modeldoc.Lumiere;
import org.j3df.modeldoc.Mesh;
import org.j3df.modeldoc.MeshBox;
import org.j3df.modeldoc.MeshSphere;
import org.j3df.modeldoc.ModelLumiere;
import org.j3df.modeldoc.Racine;
import org.j3df.modeldoc.Scene;
import org.j3df.modeldoc.Texture;
import org.j3df.modeldoc.TypeNavigation;
import org.j3df.modeldoc.Vecteur;

/**
 *
 * @author Alain
 */
public class LectureXML implements Lecture {

    private Resultat erreurs;
    private Racine racine;

    public LectureXML(String fichier) {
        erreurs = new Resultat();
        lecture(fichier);
    }

    private void lecture(String fichier) {
        Document document;
        Element racine0,e1,e2,e3,e4;
        String version,s;
        int width=-1,height=-1,x,y,z;
        Scene scene;
        Camera camera;
        Vecteur v;
        List liste_elt;
        Couleur c;

        //On crée une instance de SAXBuilder
        SAXBuilder sxb = new SAXBuilder();
        try {
            //On crée un nouveau document JDOM avec en argument le fichier XML
            //Le parsing est terminé ;)
            document = sxb.build(new File(fichier));

            //On initialise un nouvel élément racine avec l'élément racine du document.
            racine0 = document.getRootElement();
            if (racine0 == null) {
                erreurs.ajoute_erreur("document invalide");
            }
            else
            {
                if(racine0.getName()==null||!racine0.getName().equals("doc"))
                {
                    erreurs.ajoute_erreur("la balise racine est incorrecte");
                }
            }
            if (!erreurs.isErreur()) {
                
                version=racine0.getAttributeValue("version");
                this.racine=new Racine(version);
                liste_elt=racine0.getChildren();
                if(liste_elt!=null&&!liste_elt.isEmpty())
                {
                    for(Object tmp2:liste_elt)
                    {
                        if(tmp2!=null&&tmp2 instanceof Element)
                        {
                            Element tmp3=(Element) tmp2;
                            if(tmp3.getName().equals("scene"))
                            {
                                scene=new Scene();
                                racine.setScene(scene);
                                e2=tmp3.getChild("window_size");
                                if(e2!=null)
                                {
                                    s=e2.getAttributeValue("width");
                                    width=Integer.parseInt(s);
                                    s=e2.getAttributeValue("height");
                                    height=Integer.parseInt(s);
                                    scene.setWidth(width);
                                    scene.setHeight(height);
                                }
                                s=tmp3.getAttributeValue("display_fps");
                                if(s!=null&&s.equalsIgnoreCase("true"))
                                {
                                    scene.setAffiche_fps(true);
                                    System.out.println("fps=oui");
                                }
                                else
                                {
                                    scene.setAffiche_fps(false);
                                    System.out.println("fps=non");
                                }
                                s=tmp3.getAttributeValue("script");
                                if(s!=null&&!s.trim().equals(""))
                                {
                                    scene.setClasse_script(s);
                                }
	                            s=tmp3.getAttributeValue("show_ref_grid");
                                if(s!=null&&s.equalsIgnoreCase("true"))
                                {
                                    scene.setAffiche_grille(true);
                                    System.out.println("grille=oui");
                                }
                                else
                                {
                                    scene.setAffiche_grille(false);
                                    System.out.println("grille=non");
                                }
                            }
                            else if(tmp3.getName().equals("camera"))
                            {
                                camera=new Camera();
                                racine.addCamere(camera);
                                v=donne_vecteur(tmp3, "position");
                                camera.setPosition(v);
                                v=donne_vecteur(tmp3, "lookat");
                                camera.setDirection(v);
                                v=donne_vecteur(tmp3, "up");
                                camera.setUp(v);
                                s=tmp3.getAttributeValue("name");
                                if(s!=null)
                                {
                                    camera.setNom(s);
                                }
                                camera.setNavigation(TypeNavigation.Fixe);
                                s=tmp3.getAttributeValue("navigation_mode");
                                if(s!=null)
                                {
                                    if(s.equals("FIXED"))
                                    {
                                        camera.setNavigation(TypeNavigation.Fixe);
                                    }
                                    else if(s.equals("Keybord"))
                                    {
                                        camera.setNavigation(TypeNavigation.Clavier);
                                    }
                                }
                            }
                            else if(tmp3.getName().equals("light"))
                            {
                                Lumiere lum;
                                lum=new Lumiere();
                                v=donne_vecteur(tmp3, "position");
                                lum.setPosition(v);
                                s=tmp3.getAttributeValue("type");
                                if(s!=null&&s.equalsIgnoreCase("OMNI"))
                                {
                                    lum.setModelLumiere(ModelLumiere.Omidirectionnel);
                                }
                                s=tmp3.getAttributeValue("name");
                                lum.setNom(s);
                                c=donne_couleur(tmp3,"ambient");
                                lum.setAmbient(c);
                                c=donne_couleur(tmp3,"diffuse");
                                lum.setDiffuse(c);
                                c=donne_couleur(tmp3,"specular");
                                lum.setSpecular(c);
                                racine.ajouteLumiere(lum);
                            }
                            else if(tmp3.getName().equals("box"))
                            {
                                MeshBox box;
                                float f;
                                int n;
                                //mesh=null;
                                box=new MeshBox();
                                initialise_mesh(box,tmp3);
                                s=tmp3.getAttributeValue("size");
                                if(s!=null)
                                {
                                    f=Float.parseFloat(s);
                                    box.setTaille_cote(f);
                                }
                                s=tmp3.getAttributeValue("segments");
                                if(s!=null)
                                {
                                    n=Integer.parseInt(s);
                                    box.setNbSegments(n);
                                }
                                racine.ajouteMesh(box);
                            }
                            else if(tmp3.getName().equals("sphere"))
                            {
                                MeshSphere sphere;
                                float f;
                                int n;
                                //mesh=null;
                                sphere=new MeshSphere();
                                initialise_mesh(sphere,tmp3);
                                s=tmp3.getAttributeValue("rayon");
                                if(s!=null)
                                {
                                    f=Float.parseFloat(s);
                                    sphere.setRayon(f);
                                }
                                s=tmp3.getAttributeValue("slice");
                                if(s!=null)
                                {
                                    n=Integer.parseInt(s);
                                    sphere.setSlice(n);
                                }
                                s=tmp3.getAttributeValue("stack");
                                if(s!=null)
                                {
                                    n=Integer.parseInt(s);
                                    sphere.setStack(n);
                                }
                                racine.ajouteMesh(sphere);
                            }
                            else if(tmp3.getName().equals("texture"))
                            {
                                Texture texture;
                                texture=new Texture();
                                s=tmp3.getAttributeValue("name");
                                if(s!=null)
                                {
                                    texture.setNom(s);
                                }
                                s=tmp3.getAttributeValue("filename");
                                if(s!=null)
                                {
                                    texture.setNom_fichier(s);
                                }
                                racine.ajouteTexture(texture);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            erreurs.ajoute_erreur("Erreur:" + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    private Vecteur donne_vecteur(Element e1, String nom) throws NumberFormatException {
        Vecteur v=null;
        Element e2;
        String s;
        float x,y,z;
        e2 = e1.getChild(nom);
        if (e2 != null) {
            s = e2.getAttributeValue("x");
            x = Float.parseFloat(s);
            s = e2.getAttributeValue("y");
            y = Float.parseFloat(s);
            s = e2.getAttributeValue("z");
            z = Float.parseFloat(s);
            v = new Vecteur(x, y, z);
        }
        return v;
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

    private Couleur donne_couleur(Element e, String name) {
        Couleur c=null;
        Element e2;
        String s;
        float r,g,b,a;
        e2 = e.getChild(name);
        if (e2 != null) {
            s = e2.getAttributeValue("r");
            r = Float.parseFloat(s);
            s = e2.getAttributeValue("g");
            g = Float.parseFloat(s);
            s = e2.getAttributeValue("b");
            b = Float.parseFloat(s);
            s = e2.getAttributeValue("a");
            a = Float.parseFloat(s);
            c=new Couleur(r,g,b,a);
        }
        return c;
    }

    private void initialise_mesh(Mesh mesh, Element tmp3) {
        Vecteur v;
        String s;
        //Element e1,e2;
        v=donne_vecteur(tmp3, "position");
        mesh.setPosition(v);
        s=tmp3.getAttributeValue("texture_name");
        if(s!=null)
        {
            mesh.setNom_texture(s);
        }
        s=tmp3.getAttributeValue("name");
        if(s!=null)
        {
            mesh.setNom(s);
        }
    }
}
