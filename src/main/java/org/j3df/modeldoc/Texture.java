/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.j3df.modeldoc;

/**
 *
 * @author Alain
 */
public class Texture {

    private String nom_fichier;
    private String nom;
    private int id_texture;

    public Texture() {
    }

    public int getId_texture() {
        return id_texture;
    }

    public void setId_texture(int id_texture) {
        this.id_texture = id_texture;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom_fichier() {
        return nom_fichier;
    }

    public void setNom_fichier(String nom_fichier) {
        this.nom_fichier = nom_fichier;
    }

    
}
