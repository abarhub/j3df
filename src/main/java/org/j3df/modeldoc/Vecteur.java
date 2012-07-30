/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.j3df.modeldoc;

/**
 *
 * @author Alain
 */
public class Vecteur {

    private float x;
    private float y;
    private float z;

    public Vecteur()
    {

    }

    public Vecteur(Vecteur v)
    {
        x=v.getX();
        y=v.getY();
        z=v.getZ();
    }

    public Vecteur(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void add(Vecteur v)
    {
        x=x+v.getX();
        y=y+v.getY();
        z=z+v.getZ();
    }

    public static Vecteur add(Vecteur v1,Vecteur v2)
    {
        Vecteur v3;
        v3=new Vecteur(v1.getX()+v2.getX(),v1.getY()+v2.getY(),v1.getZ()+v2.getZ());
        return v3;
    }

    public void sub(Vecteur v)
    {
        x=x-v.getX();
        y=y-v.getY();
        z=z-v.getZ();
    }

    public static Vecteur sub(Vecteur v1,Vecteur v2)
    {
        Vecteur v3;
        v3=new Vecteur(v1.getX()-v2.getX(),v1.getY()-v2.getY(),v1.getZ()-v2.getZ());
        return v3;
    }

    public void neg()
    {
        x=-x;
        y=-y;
        z=-z;
    }

    public static Vecteur neg(Vecteur v)
    {
        return new Vecteur(-v.getX(),-v.getY(),-v.getZ());
    }
}
