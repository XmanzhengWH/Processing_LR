package testCase;

import processing.core.PApplet;
import processing.opengl.*;

public class DDDcase extends PApplet{
    public void settings(){
        size(440,220,P3D);

    }
    public void setup(){
        noStroke();
        fill(255,190);
    }
    public void draw(){
        background(0);
        translate(width/2,height/2,0);
        rotateX(mouseX/(float)200.0);
        rotateY(mouseY/(float)100.0);
        int dim = 18;
        for (int i = -height/2;i < height/2; i +=dim*1.2){
            for(int j = -height/2;j < height/2;j += dim*1.2){
                beginShape();
                vertex(i,j,0);
                vertex(i+dim,j,0);
                vertex(i+dim,j+dim,-dim);
                vertex(i,j+dim,-dim);
                endShape();
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main("testCase.DDDcase");
    }
}
