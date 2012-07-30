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
public class ExempleScript2 implements ScriptUpdate {

    public void update(Racine racine) {
        float angle;
        Vecteur v,axe,pos,pos2,pos3;
        MeshSphere sphere,sphere2;
        sphere=(MeshSphere) racine.getMeshs("lune");
        sphere2=(MeshSphere) racine.getMeshs("terre");
        rotation_globale(racine);
        if(sphere!=null&&sphere2!=null&&false)
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
                rotation(sphere,sphere2,angle,axe);
            }
            else if(true)
            {
                axe=new Vecteur(0,0,1);
                pos=Utils.rotate(pos,pos2, angle, axe);
                sphere.setPosition(pos);
            }
            else if(true)
            {
                pos=Vecteur.sub(pos, pos2);
                //axe=axe.add(pos2);
                axe=new Vecteur(0,0,1);
                v=Utils.rotate(pos, angle, axe);
                pos=Vecteur.add(v, pos2);
                sphere.setPosition(pos);
            }
            else
            {
                v=Utils.rotate(pos, angle, axe);
                pos=v;
                sphere.setPosition(pos);
            }
            //sphere.setPosition(pos);
        }
        else
        {
            //System.out.println("sphere inconnue");
        }
    }

    private void rotation(MeshSphere sphere1,MeshSphere sphere2,float angle,Vecteur axe)
    {
        Vecteur v,pos,pos2;
        pos=sphere1.getPosition();
        pos2=sphere2.getPosition();
        pos=Utils.rotate(pos,pos2, angle, axe);
        sphere1.setPosition(pos);
    }

    private void rotation_globale(Racine racine) {
        MeshSphere terre,lune,soleil,mercure,venus,mars;
        float angle;
        Vecteur v,axe,pos,pos2,pos3;
        soleil=(MeshSphere) racine.getMeshs("soleil");
        lune=(MeshSphere) racine.getMeshs("lune");
        terre=(MeshSphere) racine.getMeshs("terre");
        mercure=(MeshSphere) racine.getMeshs("mercure");
        venus=(MeshSphere) racine.getMeshs("venus");
        mars=(MeshSphere) racine.getMeshs("mars");
        if(soleil!=null&&mercure!=null)
        {
            angle=0.005f;
            axe=new Vecteur(0,0,1);
            rotation(mercure,soleil,angle,axe);
        }
        if(soleil!=null&&venus!=null)
        {
            angle=0.004f;
            axe=new Vecteur(0,0,1);
            rotation(venus,soleil,angle,axe);
        }
        if(soleil!=null&&terre!=null)
        {
            angle=0.001f;
            axe=new Vecteur(0,0,1);
            rotation(terre,soleil,angle,axe);
        }
        if(lune!=null&&terre!=null)
        {
            angle=0.01f;
            axe=new Vecteur(0,0,1);
            rotation(lune,terre,angle,axe);
        }
        if(soleil!=null&&mars!=null)
        {
            angle=0.003f;
            axe=new Vecteur(0,0,1);
            rotation(mars,soleil,angle,axe);
        }
    }

}
