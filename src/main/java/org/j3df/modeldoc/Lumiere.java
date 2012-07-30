/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.j3df.modeldoc;

/**
 *
 * @author Alain
 */
public class Lumiere {

    private ModelLumiere modelLumiere;
    private Vecteur position;
    private Vecteur direction;
    private Couleur ambient;
    private Couleur diffuse;
    private Couleur specular;
    private String nom;

    public Lumiere() {
    }

    public Vecteur getDirection() {
        return direction;
    }

    public void setDirection(Vecteur direction) {
        this.direction = direction;
    }

    public ModelLumiere getModelLumiere() {
        return modelLumiere;
    }

    public void setModelLumiere(ModelLumiere modelLumiere) {
        this.modelLumiere = modelLumiere;
    }

    public Vecteur getPosition() {
        return position;
    }

    public void setPosition(Vecteur position) {
        this.position = position;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Couleur getAmbient() {
        return ambient;
    }

    public void setAmbient(Couleur ambient) {
        this.ambient = ambient;
    }

    public Couleur getDiffuse() {
        return diffuse;
    }

    public void setDiffuse(Couleur diffuse) {
        this.diffuse = diffuse;
    }

    public Couleur getSpecular() {
        return specular;
    }

    public void setSpecular(Couleur specular) {
        this.specular = specular;
    }

    
}
