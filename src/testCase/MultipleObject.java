package testCase;

import processing.core.PApplet;

public class MultipleObject extends PApplet{
    JitterBug jit;
    JitterBug bug;

    public void settings(){
        size(480,120);
    }
    public  void setup(){
        smooth();
        jit = new JitterBug(width*(float)0.33,height/2,50);
        bug = new JitterBug(width*(float)0.66,height/2,10);
    }
    public void draw(){
        jit.move();
        jit.display();
        bug.move();
        bug.display();
    }

    public static void main(String[] args) {
        PApplet.main("testCase.MultipleObject");
    }

}
