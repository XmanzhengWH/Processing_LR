package works;


import delaunay_triangulation.Delaunay_Triangulation;
import processing.core.PApplet;
import processing.core.PVector;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FrozenBrush extends PApplet {

    int maxLevel = 5;
    boolean useFill = false;
    double data[];
    float level;
    int x, y;
    Particle XParticles;
    List <Particle> allParticles = new ArrayList();
    // Moves to a random direction and comes to a stop.
    // Spawns other particles within its lifetime.
    class Particle {
        float x, y, level;
        float life = 0 ;
        PVector pos, vel;

        public Particle(float x, float y, float level) {
            this.x =x;
            this.y =y;
            this.level=level;
            PVector pos = new PVector(x, y);
            PVector vel = PVector.random2D();
            vel.mult(map(level, 0, maxLevel, 5, 2));
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }

        public PVector getPos() {
            return pos;
        }

        public void move() {
            this.life++;
            //Add friction.
            vel.mult((float) 0.9);
            pos.add(vel);
            // Spawn a new particle if conditions are met.
            if (this.life % 10 == 0) {
                if (this.level > 0) {
                    this.level -= 1;
                    Particle XParticles = new Particle(pos.x, pos.y, level-1);
                    allParticles.add(XParticles);
                }
            }
        }
    }

    public  void settings(){
        size(width,height);
    }
    public void setup(){
        colorMode(HSB,360);
        textAlign(CENTER);
        background(0);
    }
    public void draw(){
        // Create fade effect.
        noStroke();
        fill(0,30);
        rect(0,0,width,height);
        // Move and spawn particles.
        // Remove any that is below the velocity threshold.
        for(int i = allParticles.size() -1;i>-1;i--){
            allParticles.get(i).move();
            if(allParticles.get(i).vel.mag()<0.01) {
                allParticles.add(i, XParticles);
            }
            }


        if(allParticles.size()>0){
            // Run script to get points to create triangles with.
            Particle Particles = new Particle(x,y,level);
            for(int i = allParticles.size() -1;i>-1;i--){
                Particles = allParticles.get(i);
                PVector pos = Particles.getPos();
            }
            data = Delaunay_Triangulation(allParticles.);
            strokeWeight((float)0.1);
        for(int i=0;i<data.length;i+=3){
            Delaunay_Triangulation p1 = allParticles[data[i]];
        }
        }




        }



