/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.j3df.moteur3d;

import org.j3df.modeldoc.MeshSphere;
import org.j3df.modeldoc.Racine;
import org.j3df.modeldoc.ScriptUpdate;
import org.j3df.modeldoc.Vecteur;

/**
 *
 * @author Alain
 */
public class ExempleScript implements ScriptUpdate {

    public void update(Racine racine) {
        float angle;
        Vecteur v,axe,pos,pos2,pos3;
        MeshSphere sphere,sphere2;
        sphere=(MeshSphere) racine.getMeshs("lune");
        sphere2=(MeshSphere) racine.getMeshs("terre");
        if(sphere!=null&&sphere2!=null)
        {
            //System.out.println("pos="+sphere.getPosition());
            angle=0.01f;
            //axe=new Vecteur(0,1,0);
            pos=sphere.getPosition();
            pos2=sphere2.getPosition();
            /*if(true)
            {
                pos3=Vecteur.sub(pos2, pos);
                pos3=Vecteur.neg(pos3);
                //pos=Vecteur.sub(pos, pos3);
                //axe=axe.add(pos2);
                v=Utils.rotate(pos3, angle, axe);
                //pos=Vecteur.add(v, pos3);
                pos=Vecteur.add(v, pos2);
            }
            else if(true)
            {
                pos3=Vecteur.sub(pos, pos2);
                pos=Vecteur.sub(pos, pos3);
                //axe=axe.add(pos2);
                v=Utils.rotate(pos3, angle, axe);
                pos=Vecteur.add(v, pos3);
            }
            else*/
            if(true)
            {
                axe=new Vecteur(0,0,1);
                pos=Utils.rotate(pos,pos2, angle, axe);
            }
            else if(true)
            {
                pos=Vecteur.sub(pos, pos2);
                //axe=axe.add(pos2);
                axe=new Vecteur(0,0,1);
                v=Utils.rotate(pos, angle, axe);
                pos=Vecteur.add(v, pos2);
            }
            else
            {
                v=Utils.rotate(pos, angle, axe);
                pos=v;
            }
            sphere.setPosition(pos);
        }
        else
        {
            System.out.println("sphere inconnue");
        }
    }

}
