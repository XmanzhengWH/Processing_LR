package testCase;

import processing.core.PApplet;
import processing.opengl.*;

public class threeDlight extends PApplet {
    public void settings(){
        size(420,220,P3D);
    }
    public void setup(){
        noStroke();
        fill(255);
    }
    public void draw(){
//        lights();
//        ambientLight(102,102,102);
//        directionalLight(255,255,255,-1,0,0);
//        pointLight(255,255,255,mouseX,110,50);
        spotLight(255,255,255,mouseX,0,200,0,0,-1,PI,2);
        rotateY(PI/24);
        background(0);
        translate(width/2,height/2,-20);
        int dim = 18;
        for(int i = -height/2;i < height/2;i +=dim*1.4){
            for(int j = -height/2;j < height/2;j += dim*1.4){
                pushMatrix();
                translate(i,j,-j);
                box(dim,dim,dim);
                popMatrix();
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main("testCase.threeDlight");
    }
}
