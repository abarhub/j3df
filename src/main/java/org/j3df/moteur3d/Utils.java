/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.j3df.moteur3d;

import org.j3df.modeldoc.Vecteur;
import org.j3df.lib3d.glmodel.GL_Matrix;
import org.j3df.lib3d.glmodel.GL_Vector;

/**
 *
 * @author Alain
 */
public class Utils {

    public static Vecteur rotate(Vecteur v,float angle,Vecteur axe)
    {
        Vecteur res;
        GL_Vector v1,v2,v3;
        GL_Matrix m;
        float c,s;
        v1=conv(v);
        v2=conv(axe);
        c=(float) Math.cos(angle);
        s=(float)Math.sin(angle);
        m=new GL_Matrix();

        m.m00=c+(1-c)*v2.x*v2.x;
        m.m01=(1-c)*v2.x*v2.y+s*v2.z;
        m.m02=(1-c)*v2.x*v2.z-s*v2.y;

        m.m10=(1-c)*v2.x*v2.y-s*v2.z;
        m.m11=c+(1-c)*v2.y*v2.y;
        m.m12=(1-c)*v2.y*v2.z+s*v2.x;

        m.m20=(1-c)*v2.x*v2.z+s*v2.y;
        m.m21=(1-c)*v2.y*v2.z-s*v2.x;
        m.m22=c+(1-c)*v2.z*v2.z;

        v3=new GL_Vector(m.m00*v1.x+m.m10*v1.y+m.m20*v1.z,
                m.m01*v1.x+m.m11*v1.y+m.m21*v1.z,
                m.m02*v1.x+m.m12*v1.y+m.m22*v1.z);

        res=conv(v3);

        return res;
    }

    public static Vecteur rotate(Vecteur v,Vecteur centre_rotation,float angle,Vecteur axe)
    {
        Vecteur res,tmp;
        res=Vecteur.sub(v, centre_rotation);
        tmp=Utils.rotate(res, angle, axe);
        res=Vecteur.add(tmp, centre_rotation);
        return res;
    }

    public static Vecteur conv(GL_Vector v)
    {
        return new Vecteur(v.x,v.y,v.z);
    }

    public static GL_Vector conv(Vecteur v)
    {
        return new GL_Vector(v.getX(),v.getY(),v.getZ());
    }
}
