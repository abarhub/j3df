/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.j3df.modeldoc;

/**
 *
 * @author Alain
 */
public class Scene {

    private int width;
    private int height;
    private boolean affiche_fps;
    private String classe_script;
	private boolean affiche_grille;

    public Scene() {
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isAffiche_fps() {
        return affiche_fps;
    }

    public void setAffiche_fps(boolean affiche_fps) {
        this.affiche_fps = affiche_fps;
    }

    public String getClasse_script() {
        return classe_script;
    }

    public void setClasse_script(String classe_script) {
        this.classe_script = classe_script;
    }

	public boolean isAffiche_grille() {
		return affiche_grille;
	}

	public void setAffiche_grille(boolean affiche_grille) {
		this.affiche_grille = affiche_grille;
	}
}
