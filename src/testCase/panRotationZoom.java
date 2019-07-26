package testCase;

import processing.core.PApplet;

public class panRotationZoom extends PApplet {
    float angle = (float)0.0;
    public void settings(){
        size(120,120);
    }
    public void draw(){
        //Pan
//        pushMatrix();
//        translate(mouseX,mouseY);
//        rect(0,0,30,30);
//        popMatrix();
//        translate(35,10);
//        rect(0,0,15,15);
        //Rotation
//        translate(mouseX,mouseY);
//        rotate(angle);
//        rect(-15,-15,30,30);
//        angle += 0.1;
        //ZOOM
        translate(mouseX,mouseY);
        scale(cos(angle)+3);
        rect(-15,-15,30,30);
        angle += 0.1;
    }

    public static void main(String[] args) {
        PApplet.main("testCase.panRotationZoom");
    }

}
