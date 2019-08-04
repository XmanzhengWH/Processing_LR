package testCase;
import processing.core.*;
import processing.data.*;
import processing.event.*;
import processing.opengl.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class Random_color_circle extends PApplet {

    class Drop {

        int x, y;     // Coordinate (center of circle)
        int diameter;     // Diameter of circle (unused == 0).


        public void init( int ix, int iy ) {
            x = ix;
            y = iy;
            diameter = 1;
        }

        public void spread() {
            if (diameter > 0) diameter += 1;
        }

        public void show() {

            if (diameter > 0) {
                int r = PApplet.parseInt(random(255));
                int g = PApplet.parseInt(random(255));
                int b = PApplet.parseInt(random(255));
                int a = PApplet.parseInt(random(255));
                int k = PApplet.parseInt(random(15));
                strokeWeight(k);
                stroke(r,g,b,a);
                ellipse( x, y, diameter, diameter );
                if (diameter > 500) diameter = 0;
            }
        }

    }
    Drop[] drops;
    int numDrops = 30;
    int curDrop = 0;


    public void setup() {

        ellipseMode(CENTER);
        noFill();

        drops = new Drop[numDrops];
        for (int i = 0 ; i < numDrops ; i++) {
            drops[i] = new Drop();
            drops[i].diameter = 0;
        }
    }

    public void draw() {
        background(0);

        for (int i = 0 ; i < numDrops ; i++) {
            drops[i].spread();
            drops[i].show();
        }
    }

    public void mousePressed() {
        drops[curDrop].init( mouseX, mouseY );
        if (++curDrop == numDrops) curDrop = 0;
    }
    public void settings() {  size(600, 400);  smooth(); }
    static public void main(String[] passedArgs) {
        String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "testCase.Random_color_circle" };
        if (passedArgs != null) {
            PApplet.main(concat(appletArgs, passedArgs));
        } else {
            PApplet.main(appletArgs);
        }
    }
}
