/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.j3df.moteur3d;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.j3df.modeldoc.*;
import org.j3df.lib3d.glapp.GLApp;
import org.j3df.lib3d.glapp.GLCam;
import org.j3df.lib3d.glapp.GLCamera;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

/**
 *
 * @author Alain
 */
public class MoteurRendu extends GLApp {

    private Racine racine;
    float lightPosition[] = {-2f, 2f, 2f, 0f};
    private GLCamera camera1 = new GLCamera();
    private GLCamera camera2 = new GLCamera();
    private GLCam cam = new GLCam(camera1);
    private ScriptUpdate script;

    //private int sphereTextureHandle;
    public MoteurRendu(Racine racine) {
	this.racine = racine;
	init_param();
    }

    public static void demarrage(Racine racine) {
	MoteurRendu moteur;
	moteur = new MoteurRendu(racine);
	//demo.VSyncEnabled = true;
	//demo.fullScreen = false;
	//demo.displayWidth = 800;
	//demo.displayHeight = 600;
	moteur.run();
    }

    private void init_param() {
	VSyncEnabled = false;
	fullScreen = false;
	displayWidth = racine.getScene().getWidth();
	displayHeight = racine.getScene().getHeight();
	//script=new ExempleScript();
	calcul_script(racine);
	if(racine.getScene().isAffiche_grille())
	{
		racine.ajouteMesh(new MeshPlane());
	}
    }

    /**
     * Initialize the scene.  Called by GLApp.run()
     */
    public void setup() {
	// setup and enable perspective
	setPerspective();

	// Create a light (diffuse light, ambient light, position)
	setLight(GL11.GL_LIGHT1,
		new float[]{1f, 1f, 1f, 1f},
		new float[]{0.5f, 0.5f, .53f, 1f},
		new float[]{1f, 1f, 1f, 1f},
		lightPosition);

	// Create a directional light (light green, to simulate reflection off grass)
	setLight(GL11.GL_LIGHT2,
		new float[]{0.15f, 0.4f, 0.1f, 1.0f}, // diffuse color
		new float[]{0.0f, 0.0f, 0.0f, 1.0f}, // ambient
		new float[]{0.0f, 0.0f, 0.0f, 1.0f}, // specular
		new float[]{0.0f, -10f, 0.0f, 0f});   // direction (pointing up)

	if (racine.getLumiere() != null && !racine.getLumiere().isEmpty()) {
	    for (Lumiere lum : racine.getLumiere()) {
		switch (lum.getModelLumiere()) {
		    case Omidirectionnel:
			//setAmbientLight(lum.);
			break;
		    case Directionnelle:
			break;
		}
	    }
	}
	// enable lighting and texture rendering
	GL11.glEnable(GL11.GL_LIGHTING);
	GL11.glEnable(GL11.GL_TEXTURE_2D);

	// Enable alpha transparency (so text will have transparent background)
	GL11.glEnable(GL11.GL_BLEND);
	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	// Create texture for spere
	//sphereTextureHandle = makeTexture("images/earth.gif");
	//sphereTextureHandle = makeTexture("doc/earth2.gif");
	//sphereTextureHandle = makeTexture("doc/EarthMap_2500x1250.jpg");
	//sphereTextureHandle = makeTexture("doc/earthmap3.png");
	if (racine.getListe_textures() != null) {
	    for (Texture t : racine.getListe_textures()) {
		int id_texture;
		id_texture = makeTexture(t.getNom_fichier());
		t.setId_texture(id_texture);
	    }
	}

	// Create texture for ground plane
	//groundTextureHandle = makeTexture("images/grass_1_512.jpg",true,true);

	if (true) {
	    Camera c;
	    Vecteur pos, dir, up;
	    c = racine.getCamere();
	    pos = c.getPosition();
	    dir = c.getDirection();
	    up = c.getUp();
		if(pos==null)
		{
			pos=Utils.conv(camera1.Position);
		}
		if(dir==null)
		{
			dir=Utils.conv(camera1.ViewDir);
		}
		if(up==null)
		{
			up=Utils.conv(camera1.UpVector);
		}
		assert(pos!=null);
		assert(dir!=null);
		assert(up!=null);
	    camera1.setCamera(pos.getX(), pos.getY(), pos.getZ(),
		    dir.getX(), dir.getY(), dir.getZ(),
		    up.getX(), up.getY(), up.getZ());
	} else {
	    // set camera 1 position
	    camera1.setCamera(0, 4, 15, 0, -.3f, -1, 0, 1, 0);
	}

	// load the airplane model and make it a display list
	//airplane = new GLModel("models/JetFire/JetFire.obj");
	//airplane.mesh.regenerateNormals();
	//airplane.makeDisplayList();

	// make a sphere display list
        /*earth = beginDisplayList(); {
	renderSphere();
	}
	endDisplayList();*/

	// make a shadow handler
	// params:
	//		the light position,
	//		the plane the shadow will fall on,
	//		the color of the shadow,
	// 		this application,
	// 		the function that draws all objects that cast shadows
	//airplaneShadow = new GLShadowOnPlane(lightPosition, new float[] {0f,1f,0f,3f}, null, this, method(this,"drawObjects"));
    }

    /**
     * set the field of view and view depth.
     */
    public static void setPerspective() {
	// select projection matrix (controls perspective)
	GL11.glMatrixMode(GL11.GL_PROJECTION);
	GL11.glLoadIdentity();
	// fovy, aspect ratio, zNear, zFar
	GLU.gluPerspective(50f, // zoom in or out of view
		aspectRatio, // shape of viewport rectangle
		.1f, // Min Z: how far from eye position does view start
		500f);       // max Z: how far from eye position does view extend
	// return to modelview matrix
	GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }

    /**
     * Render one frame.  Called by GLApp.run().
     */
    @Override
    public void draw() {
	//degrees += 30f * GLApp.getSecondsPerFrame();

	// place airplane in orbit around ball, and place camera slightly above airplane
	//airplanePos = GL_Vector.rotationVector(degrees).mult(8);
	//camera2.MoveTo(airplanePos.x, airplanePos.y+.53f, airplanePos.z);

	// align airplane and camera2 (perpendicular to the radius and up vector)
	//GL_Vector airplaneDirection = GL_Vector.crossProduct(UP,airplanePos);
	//camera2.viewDir( airplaneDirection );  // point camera in direction of airplane motion

	if (racine.getCamere() != null && racine.getCamere().getNavigation() == TypeNavigation.Clavier) {
	    // user keystrokes adjust camera position
	    cam.handleNavKeys((float) GLApp.getSecondsPerFrame());
	}

	// combine user camera motion with current camera position (so user can look around while on the airplane)
	//float apRot = camera2.RotatedY;  // how much is camera rotated?
	//camera2.RotatedY = 0;            // zero out rotation
	//camera2.RotateY(apRot);          // set rotation again (camera will add rotation to its current view direction)

	// clear depth buffer and color
	GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

	// select model view for subsequent transforms
	GL11.glMatrixMode(GL11.GL_MODELVIEW);
	GL11.glLoadIdentity();

	// do gluLookAt() with camera position, direction, orientation
	cam.render();

	// draw the ground plane
        /*GL11.glPushMatrix();
	{
	GL11.glTranslatef(0f, -3f, 0f); // down a bit
	GL11.glScalef(15f, .01f, 15f);
	GL11.glBindTexture(GL11.GL_TEXTURE_2D, groundTextureHandle);
	renderCube();
	}
	GL11.glPopMatrix();*/

	// invokes the drawObjects() method to create shadows for objects in the scene
	//airplaneShadow.drawShadow();

	// draw sphere at center (rotate 10 degrees per second)
	rotation += 10f * getSecondsPerFrame();

	// draw the scene (after we draw the shadows, so everything layers correctly)
	drawObjects();

	// Place the light.  Light will move with the rest of the scene
	setLightPosition(GL11.GL_LIGHT1, lightPosition);

	// render some text using texture-mapped font
	/*print( 30, viewportH- 45, "Use arrow keys to navigate:");
	print( 30, viewportH- 80, "Left-Right arrows rotate camera", 1);
	print( 30, viewportH-100, "Up-Down arrows move camera forward and back", 1);
	print( 30, viewportH-120, "PageUp-PageDown move vertically", 1);
	print( 30, viewportH-140, "SPACE key switches cameras", 1);*/
	if (racine.getScene().isAffiche_fps()) {
	    float fps = 0.0f;
	    double d;
	    if (true) {
		d = GLApp.getSecondsPerFrame();
		if (d <= 0.00001) {
		    fps = 0.0f;
		} else {
		    fps = (float) (1.f / d);
		}
	    } else {
		float tmp;
		long n2, n3;
		//tmp=getTimeInMillis();
		//fps=(float) tmp;
		if (n == -1) {
		    n = System.currentTimeMillis();
		}
		n2 = System.currentTimeMillis();
		n3 = n2 - n;
		n = n2;
		d = n3 / 1000.0f;
		if (d <= 0.00001) {
		    fps = 0.0f;
		} else {
		    fps = (float) (1.f / d);
		}
	    }
	    print(30, viewportH - 45, "FPS:" + fps);
	}
    }
    private long n = -1;

    @Override
    public void mouseMove(int x, int y) {
    }

    @Override
    public void mouseDown(int x, int y) {
    }

    @Override
    public void mouseUp(int x, int y) {
    }

    @Override
    public void keyDown(int keycode) {
	//if (Keyboard.KEY_SPACE == keycode) {
	//	cam.setCamera((cam.camera == camera1)? camera2 : camera1);
	//}
    }

    public void keyUp(int keycode) {
    }

    private void drawObjects() {
	if (racine.getMeshs() != null && !racine.getMeshs().isEmpty()) {
	    //b=false;
	    for (Mesh m : racine.getMeshs()) {
		if (m instanceof MeshBox) {
		    MeshBox box;
		    Vecteur pos;
		    box = (MeshBox) m;
		    if (box.getPosition() != null && box.getNbSegments() > 0 && box.getTaille_cote() > 0.0001) {
			//pos=box.getPosition();
			//GL11.glTranslatef(pos.getX(), pos.getY(), pos.getZ());
			//GL11.glMatrixMode(GL11.GL_MODELVIEW);
			//GL11.glLoadIdentity();
			GL11.glPushMatrix();
			prepare_rendu_mesh(box);
			renderCube(box.getTaille_cote(), box.getNbSegments());
			//GL11.glFlush();
			GL11.glPopMatrix();
		    }
		} else if (m instanceof MeshSphere) {
		    MeshSphere sphere;
		    sphere = (MeshSphere) m;
		    if (sphere.getPosition() != null && sphere.getRayon() > 0.0 && sphere.getSlice() > 0 && sphere.getStack() > 0) {
			Sphere s;
			Vecteur pos;
			//GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glPushMatrix();
			s = new Sphere();
			//pos=sphere.getPosition();
			//GL11.glTranslatef(pos.getX(), pos.getY(), pos.getZ());
                        /*if(!b)
			{
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_REPLACE);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.sphereTextureHandle);
			s.setTextureFlag(true);
			//s.setDrawStyle(GLU.GLU_LINE);
			b=true;
			}
			else
			{
			s.setTextureFlag(false);
			}*/
			//GL11.glLoadIdentity();
			GL11.glRotatef(-90f, 1, 0, 0);
			if (prepare_rendu_mesh(sphere)) {
			    s.setTextureFlag(true);
			} else {
			    s.setTextureFlag(false);
			}
			s.draw(sphere.getRayon(), sphere.getSlice(), sphere.getStack());
			//GL11.glFlush();
			GL11.glPopMatrix();
		    }
		} else if (m instanceof MeshPlane) {
		    MeshPlane box;
		    Vecteur pos;
		    box = (MeshPlane) m;
		    //if (box.getPosition() != null && box.getNbSegments() > 0 && box.getTaille_cote() > 0.0001) {
			//pos=box.getPosition();
			//GL11.glTranslatef(pos.getX(), pos.getY(), pos.getZ());
			//GL11.glMatrixMode(GL11.GL_MODELVIEW);
			//GL11.glLoadIdentity();
			GL11.glPushMatrix();
			prepare_rendu_mesh(box);
			//renderPlane(10,10);
			renderPlaneORIG2(50,80,10,10);
			//renderCube(box.getTaille_cote(), box.getNbSegments());
			//GL11.glFlush();
			GL11.glPopMatrix();
		    //}
	    }
	    }
	}
    }

	public static void renderPlaneORIG2(float length, float height, int length_segments, int height_segments) {
    	float xpos = - length/2f;
    	float ypos = - height/2f;
    	float segsizeL = length/(float)length_segments;
    	float segsizeH = height/(float)height_segments;
        float maxDimension = (length > height)? length : height;
    	float uvsegsizeL = (length/maxDimension) / (float)length_segments;
    	float uvsegsizeH = (height/maxDimension) / (float)height_segments;
		//GL11.glEnable(GL11.GL_TEXTURE_2D);
		//GL11.glDisable(GL11.GL_TEXTURE_2D);
    	//GL11.glBegin(GL11.GL_QUADS); {
		GL11.glBegin(GL11.GL_LINES); {

		GL11.glDisable(GL11.GL_TEXTURE_2D);
    		GL11.glNormal3f(0f, 0f, 1f);   // plane is facing up the Z axis
    		for (int x=0; x < length_segments; x++, xpos+=segsizeL) {
    			for (int y=0; y < height_segments; y++, ypos+=segsizeH) {
    				// bottom left
    				//GL11.glTexCoord2f(x*uvsegsizeL, y*uvsegsizeH);
    				GL11.glVertex3f( xpos, 0f,ypos);
    				// bottom rite
    				//GL11.glTexCoord2f((x*uvsegsizeL)+uvsegsizeL, y*uvsegsizeH);
    				GL11.glVertex3f( xpos+segsizeL, 0f,ypos);

				    GL11.glVertex3f( xpos+segsizeL, 0f,ypos);
				    GL11.glVertex3f( xpos+segsizeL,  0f,ypos+segsizeH);
    				// top rite
    				//GL11.glTexCoord2f((x*uvsegsizeL)+uvsegsizeL, (y*uvsegsizeH)+uvsegsizeH);
    				GL11.glVertex3f( xpos+segsizeL,  0f,ypos+segsizeH);
    				// top left
    				//GL11.glTexCoord2f(x*uvsegsizeL, (y*uvsegsizeH)+uvsegsizeH);
    				GL11.glVertex3f( xpos,  0f,ypos+segsizeH);

				    GL11.glVertex3f( xpos,  0f,ypos+segsizeH);
				    GL11.glVertex3f( xpos, 0f,ypos);
    			}
    			ypos = - height/2f; // reset column position
    		}
    	}
    	GL11.glEnd();
    }

    //boolean b=false;
    @Override
    public void update() {
	if (script != null) {
	    script.update(racine);
	}
	/*float angle;
	Vecteur v,axe,pos,pos2;
	MeshSphere sphere,sphere2;
	sphere=(MeshSphere) racine.getMeshs("lune");
	sphere2=(MeshSphere) racine.getMeshs("terre");
	if(sphere!=null&&sphere2!=null)
	{
	//System.out.println("pos="+sphere.getPosition());
	angle=0.01f;
	axe=new Vecteur(0,1,0);
	pos=sphere.getPosition();
	pos2=sphere2.getPosition();
	//axe=axe.add(pos2);
	v=Utils.rotate(pos, angle, axe);
	sphere.setPosition(v);
	}
	else
	{
	System.out.println("sphere inconnue");
	}*/
    }

    private boolean prepare_rendu_mesh(Mesh mesh) {
	Vecteur pos;
	Texture texture;
	boolean affiche_texture;
	int id_texture = 0;
	pos = mesh.getPosition();
	GL11.glTranslatef(pos.getX(), pos.getY(), pos.getZ());
	if (mesh.isTexture()) {
	    texture = racine.getTexture(mesh.getNom_texture());
	    if (texture != null && texture.getId_texture() > 0) {
		affiche_texture = true;
		id_texture = texture.getId_texture();
	    } else {
		affiche_texture = false;
	    }
	} else {
	    affiche_texture = false;
	}
	if (affiche_texture) {
	    GL11.glEnable(GL11.GL_TEXTURE_2D);
	    GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_REPLACE);
	    GL11.glBindTexture(GL11.GL_TEXTURE_2D, id_texture);
	}
	return affiche_texture;
    }

    private void calcul_script(Racine racine) {
	this.script = null;
	if (racine != null && racine.getScene() != null && racine.getScene().getClasse_script() != null) {
	    String nom_script;
	    nom_script = racine.getScene().getClasse_script();
	    if (nom_script != null && !nom_script.trim().equals("")) {
		Class c;
		ScriptUpdate tmp;
		nom_script = nom_script.trim();
		try {
		    c = Class.forName(nom_script);
		    if (c != null) {
			tmp = (ScriptUpdate) c.newInstance();
			if (tmp != null) {
			    System.out.println("Création de la classe " + nom_script + " réussi !");
			    this.script = tmp;
			}
		    }
		} catch (InstantiationException ex) {
		    Logger.getLogger(MoteurRendu.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
		    Logger.getLogger(MoteurRendu.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
		    Logger.getLogger(MoteurRendu.class.getName()).log(Level.SEVERE, null, ex);
		}
		if (script == null) {
		    System.out.println("Erreur pour créer la classe " + nom_script + " !");
		}
	    }
	}
    }
}
