package testCase;

import processing.core.PApplet;

public class closeGap extends PApplet {


    public void settings() {
        size(480, 120);
    }

    public void setup() {
        fill(0,102);
        smooth();//平滑模式
        background(160, 145, 145);
//        noStroke();

    }

    public void draw() {
        fill(126, 250, 78, 5);//填充
        beginShape();
        vertex(180,82);
        vertex(207,36);
        vertex(214,63);
        vertex(407,11);
        vertex(412,30);
        vertex(219,82);
        vertex(226,109);
        endShape(CLOSE);
    }


    public static void main(String[] args) {
        PApplet.main("testCase.closeGap");
    }
}
