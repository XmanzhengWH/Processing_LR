package testCase;
import processing.core.PApplet;

public class colourTry extends PApplet{
    public static void main(String[] args)
    {
        PApplet.main("testCase.colourTry");
    }
    public void settings()
    {
        size(480,220);

    }
    public void setup()
    {
        smooth();
        background(165,120,120);
    }

    public void draw()
    {
        fill(255,0,0,160);
        ellipse(132,82,200,200);
//        noFill();
        fill(0,255,0,160);
        ellipse(228,-16,200,200);
        noStroke();
        fill(0,0,255,160);
        ellipse(268,118,200,200);
    }


}

