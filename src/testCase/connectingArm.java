package testCase;

import processing.core.PApplet;

public class connectingArm extends PApplet {
    float angle = (float)0.0;
    float angleDirection = 1;
    float speed = (float)0.005;

    public void settings(){
        size(120,120);
        smooth();
    }
    public void draw(){
        background(204);
        translate(20,25);//move start location
        rotate(angle);
        strokeWeight(12);
        line(0,0,40,0);
        translate(40,0);//move next joint
        rotate(angle * (float)2.0);
        strokeWeight(6);
        line(0,0,30,0);
        translate(30,0);//move next joint
        rotate(angle * (float)2.5);
        strokeWeight(3);
        line(0,0,20,0);

        angle += speed * angleDirection;
        if ((angle > QUARTER_PI) || (angle < 0)){
            angleDirection *= -1;
        }
    }

    public static void main(String[] args) {
        PApplet.main("testCase.connectingArm");
    }
}
