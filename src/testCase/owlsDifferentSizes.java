package testCase;

import processing.core.PApplet;

public class owlsDifferentSizes  extends PApplet{
    public void settings(){
        size(480,120);
        smooth();
    }
    public void draw(){
        background(204);
        randomSeed(0);
        for (int i = 35;i < width + 40; i += 40){
            int gray = (int)(random(0,102));
            float scalar = random((float)0.25,(float)1.0);
            owl(i,110,gray,scalar);

        }
    }
    public void owl(int x, int y, int g, float s){
        pushMatrix();
        translate(x,y);
        scale(s);//set size
        stroke(g);//set Gray value
        strokeWeight(70);
        line(0,-35,0,-65);//body
        noStroke();
        fill(255-g);
        ellipse((float) 17.5,-65,35,35);//left Orbital cavity
        ellipse((float) -17.5,-65,35,35);//right Orbital cavity
        arc(0,-65,70,70,0,PI);//chin
        fill(g);
        ellipse(-14,-65,8,8);//left eyes
        ellipse(14,-65,8,8);//right eyes
        quad(0,-58,4,-51,0,-44,-4,-51);//Peck
        popMatrix();

    }

    public static void main(String[] args) {
        PApplet.main("testCase.owlsDifferentSizes");
    }
}
