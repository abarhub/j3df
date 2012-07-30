/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.j3df.modeldoc;

/**
 *
 * @author Alain
 */
public class Camera {

    private Vecteur position;
    private Vecteur direction;
    private Vecteur up;
    private String nom;
    private TypeNavigation navigation;

    public Camera()
    {

    }

    public Vecteur getPosition() {
        return position;
    }

    public void setPosition(Vecteur position) {
        this.position = position;
    }

    public Vecteur getDirection() {
        return direction;
    }

    public void setDirection(Vecteur direction) {
        this.direction = direction;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Vecteur getUp() {
        return up;
    }

    public void setUp(Vecteur up) {
        this.up = up;
    }

    public TypeNavigation getNavigation() {
        return navigation;
    }

    public void setNavigation(TypeNavigation navigation) {
        this.navigation = navigation;
    }

    
}
