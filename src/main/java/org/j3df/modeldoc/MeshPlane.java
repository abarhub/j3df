/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.j3df.modeldoc;

/**
 *
 * @author Alain
 */
public class MeshPlane extends Mesh {

	private int nb_x,nb_y;

    public MeshPlane() {
        super();

	    this.nb_x=10;
		this.nb_y=10;
    }

	public MeshPlane(int nb_x,int nb_y) {
        super();
		this.nb_x=nb_x;
		this.nb_y=nb_y;
    }

	public int getNb_x() {
		return nb_x;
	}

	public void setNb_x(int nb_x) {
		this.nb_x = nb_x;
	}

	public int getNb_y() {
		return nb_y;
	}

	public void setNb_y(int nb_y) {
		this.nb_y = nb_y;
	}
}
