package testCase;

import processing.core.PApplet;

public class LoopArray extends PApplet {
    float[] gray;
    float[] redhight;
    public void settings(){
        size(240,120);
    }
    public void setup(){
        gray = new float[width];
        redhight = new  float[height];
        for(int i = 0;i < gray.length;i++){
            gray[i] = random(0,255);
        }
//        for(int j = 0;j < redhight.length;j++){
//            redhight[j] = random(10,height);
//        }

    }
    public void draw(){
        for(int i = 0;i < gray.length;i++){
            stroke(gray[i]);
            line(i,0,i,height);

        }
    }

    public static void main(String[] args) {
        PApplet.main("testCase.LoopArray");
    }
}
