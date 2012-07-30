/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.j3df.modeldoc;

/**
 *
 * @author Alain
 */
public class Couleur {

    private float rouge;
    private float vert;
    private float bleu;
    private float alpha;

    public Couleur() {
    }

    public Couleur(float rouge, float vert, float bleu, float alpha) {
        this.rouge = rouge;
        this.vert = vert;
        this.bleu = bleu;
        this.alpha = alpha;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public float getBleu() {
        return bleu;
    }

    public void setBleu(float bleu) {
        this.bleu = bleu;
    }

    public float getRouge() {
        return rouge;
    }

    public void setRouge(float rouge) {
        this.rouge = rouge;
    }

    public float getVert() {
        return vert;
    }

    public void setVert(float vert) {
        this.vert = vert;
    }

    
}
