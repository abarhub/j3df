/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.j3df.modeldoc;

/**
 *
 * @author Alain
 */
public abstract class Mesh {

    protected Vecteur position;
    protected Couleur couleur;
    protected String nom_texture;
    protected Texture texture;
    protected String nom;

    public Mesh() {
	    position=new Vecteur(0,0,0);
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public Vecteur getPosition() {
        return position;
    }

    public void setPosition(Vecteur position) {
        this.position = position;
    }

    public String getNom_texture() {
        return nom_texture;
    }

    public void setNom_texture(String nom_texture) {
        this.nom_texture = nom_texture;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public boolean isTexture()
    {
        return nom_texture!=null;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
}
