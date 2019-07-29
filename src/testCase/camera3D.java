package testCase;

import processing.core.PApplet;

public class camera3D extends PApplet {
    public void settings(){
        size(420,220,P3D);

    }
    public void setup(){
        noStroke();
    }
    public void draw(){
        lights();
        background(0);
        float camZ = (float) ((height/2.0) / tan((float) (PI*60.0/360.0)));
        camera(mouseX,mouseY,camZ,width/2.0,height/2.0,0,0,1,0);
        translate(width/2,height/2,-20);
        int dim = 18;
        for(int i = -height/2;i < height/2; i += dim*1.4){
            for(int j = -height/2;j < height/2;j += dim*1.4){
                pushMatrix();
                translate(i,j,-j);
                box(dim,dim,dim);
                popMatrix();
            }
        }
    }

    private void camera(int mouseX, int mouseY, float camZ, double v, double v1, int centerZ, int upX, int upY, int upZ) {
    }

    public static void main(String[] args) {
        PApplet.main("testCase.camera3D");
    }
}
