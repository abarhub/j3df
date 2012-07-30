/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.j3df.modeldoc;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alain
 */
public class Racine {

    private String version;
    private Scene scene;
    private List<Camera> liste_camera;
    private List<Lumiere> lumiere;
    private List<Mesh> meshs;
    private List<Texture> liste_textures;

    public Racine(String version)
    {
        this.version=version;
        meshs=new ArrayList<Mesh>();
        liste_camera=new ArrayList<Camera>();
        liste_textures=new ArrayList<Texture>();
    }

    public Camera getCamere() {
        if(liste_camera==null||liste_camera.isEmpty())
            return null;
        return liste_camera.get(0);
    }

    public void addCamere(Camera camera) {
        liste_camera.add(camera);
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Lumiere> getLumiere() {
        return lumiere;
    }

    public void setLumiere(List<Lumiere> lumiere) {
        this.lumiere = lumiere;
    }

    public void ajouteLumiere(Lumiere lum)
    {
        if(lum!=null)
        {
            if(lumiere==null)
                lumiere=new ArrayList<Lumiere>();
            lumiere.add(lum);
        }
    }

    public List<Mesh> getMeshs() {
        return meshs;
    }

    public void setMeshs(List<Mesh> meshs) {
        this.meshs = meshs;
    }

    public void ajouteMesh(Mesh m)
    {
        meshs.add(m);
    }

    public List<Camera> getListe_camera() {
        return liste_camera;
    }

    public void setListe_camera(List<Camera> liste_camera) {
        this.liste_camera = liste_camera;
    }

    public List<Texture> getListe_textures() {
        return liste_textures;
    }

    public void setListe_textures(List<Texture> liste_textures) {
        this.liste_textures = liste_textures;
    }

    public void ajouteTexture(Texture texture)
    {
        liste_textures.add(texture);
    }

    public Texture getTexture(String nom_texture) {
        String s;
        if(nom_texture!=null&&!nom_texture.isEmpty())
        {
            if(liste_textures!=null&&!liste_textures.isEmpty())
            {
                for(Texture t:liste_textures)
                {
                    s=t.getNom();
                    if(s!=null&&s.equals(nom_texture))
                    {
                        return t;
                    }
                }
            }
        }
        return null;
    }

    public Mesh getMeshs(String nom) {
        if(nom!=null&&!nom.isEmpty())
        {
            if(meshs!=null&&!meshs.isEmpty())
            {
                String s;
                for(Mesh m:meshs)
                {
                    s=m.getNom();
                    if(s!=null&&s.equals(nom))
                    {
                        return m;
                    }
                }
            }
        }
        return null;
    }
}
