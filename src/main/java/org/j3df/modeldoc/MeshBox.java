/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.j3df.modeldoc;

/**
 *
 * @author Alain
 */
public class MeshBox extends Mesh {

    private float taille_cote;
    private int nbSegments;

    public MeshBox() {
        super();
    }

    public MeshBox(float taille_cote, int nbSegments) {
        super();
        this.taille_cote = taille_cote;
        this.nbSegments = nbSegments;
    }

    public int getNbSegments() {
        return nbSegments;
    }

    public void setNbSegments(int nbSegments) {
        this.nbSegments = nbSegments;
    }

    public float getTaille_cote() {
        return taille_cote;
    }

    public void setTaille_cote(float taille_cote) {
        this.taille_cote = taille_cote;
    }

    
    
}
