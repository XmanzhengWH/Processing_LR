package testCase;

import processing.core.PApplet;

public class shineUpon extends PApplet {
        public void settings(){
            size(240,120);
    }
        public void setup(){
            strokeWeight(12);
            smooth();
            background(204);
        }
        public void draw(){
            stroke(255);
            line(120,60,mouseX,mouseY);//whiteline
            stroke(0);
//            float mx = mouseX/2 + 60;
            float mx = map(mouseX,0,width,60,height);
            line(120,60,mx,mouseY);//backline
        }

    public static void main(String[] args) {
        PApplet.main("testCase.shineUpon");
    }
}
