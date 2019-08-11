package works;

/*
 * Copyright (c) 2005 by L. Paul Chew.
 *
 * Permission is hereby granted, without written agreement and without
 * license or royalty fees, to use, copy, modify, and distribute this
 * software and its documentation for any purpose, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Set;

/**
 * The Delauany applet.
 * Creates and displays a Delaunay Triangulation (DT) or a Voronoi Diagram (VoD).
 * Has a main program so it is an application as well as an applet.
 *
 * @author Paul Chew
 *
 * Created July 2005.  Derived from an earlier, messier version.
 */
public class DelaunayAp extends javax.swing.JApplet implements Runnable {

    /**
     * Initialize the applet.
     * As recommended, the actual use of Swing components takes place in the
     * event-dispatching thread.
     */
    public void init () {
        try {SwingUtilities.invokeAndWait(this);}
        catch (Exception e) {System.err.println("Initialization failure");}
    }

    /**
     * Set up the applet's GUI.
     * As recommended, the init method executes this in the event-dispatching
     * thread.
     */
    public void run () {
        setLayout(new BorderLayout());
        // Build the button controls
        JRadioButton voronoiButton = new JRadioButton("Voronoi Diagram");
        voronoiButton.setActionCommand("voronoi");
        JRadioButton delaunayButton = new JRadioButton("Delaunay Triangulation");
        delaunayButton.setActionCommand("delaunay");
        JButton clearButton = new JButton("Clear");
        clearButton.setActionCommand("clear");
        ButtonGroup group = new ButtonGroup();
        group.add(voronoiButton);
        group.add(delaunayButton);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(voronoiButton);
        buttonPanel.add(delaunayButton);
        buttonPanel.add(clearButton);
        this.add(buttonPanel, "North");

        // Build the mouse-entry switches
        JLabel circleLabel = new JLabel("Show Empty Circles");
        circleLabel.setName("circles");
        JLabel delaunayLabel = new JLabel("Show Delaunay Edges");
        delaunayLabel.setName("delaunay");
        JLabel voronoiLabel = new JLabel("Show Voronoi Edges");
        voronoiLabel.setName("voronoi");
        JPanel switchPanel = new JPanel();
        switchPanel.add(circleLabel);
        switchPanel.add(new Label("     "));
        switchPanel.add(delaunayLabel);
        switchPanel.add(new Label("     "));
        switchPanel.add(voronoiLabel);
        this.add(switchPanel, "South");

        // Build the graphics panel
        DelaunayPanel graphicsPanel = new DelaunayPanel();
        graphicsPanel.setBackground(Color.gray);
        this.add(graphicsPanel, "Center");
        // Register the listeners
        voronoiButton.addActionListener(graphicsPanel);
        delaunayButton.addActionListener(graphicsPanel);
        clearButton.addActionListener(graphicsPanel);
        graphicsPanel.addMouseListener(graphicsPanel);
        circleLabel.addMouseListener(graphicsPanel);
        delaunayLabel.addMouseListener(graphicsPanel);
        voronoiLabel.addMouseListener(graphicsPanel);

        // Initialize the radio buttons
        voronoiButton.doClick();
    }

    /**
     * Main program (used when run as application instead of applet).
     */
    public static void main (String[] args) {
        DelaunayAp applet = new DelaunayAp();  // Create applet
        applet.init();                         // Perform applet initialization
        JFrame dWindow = new JFrame();         // Create window
        dWindow.setSize(800, 500);             // Set window size
        dWindow.setTitle("Voronoi/Delaunay Window");
        // Set window title
        dWindow.setLayout(new BorderLayout()); // Specify layout manager
        dWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Specify closing behavior
        dWindow.add(applet, "Center");         // Place applet into window
        dWindow.setVisible(true);              // Show the window
    }
}

/**
 * Graphics Panel for DelaunayAp.
 */
class DelaunayPanel extends JPanel implements ActionListener, MouseListener {

    /**
     *
     */
    private static final long serialVersionUID = 495368670854974132L;
    private DelaunayTriangulation dt;     // The Delaunay triangulation
    private Simplex<Pnt> initialTriangle; // The large initial triangle
    private int initialSize = 10000;      // Controls size of initial triangle
    private boolean isVoronoi;            // True iff VoD instead of DT
    private boolean showCircles = false;  // True iff showing empty circles
    private boolean showDelaunay = false; // True iff showing Delaunay edges
    private boolean showVoronoi= false;   // True iff showing Voronoi edges

    public boolean debug = false;         // True iff printing info for debugging

    public Color voronoiColor = Color.orange;
    public Color delaunayColor = Color.green;
    public int pointRadius = 3;
    private Graphics g;                   // Stored graphics context

    /**
     * Create and initialize the DT.
     */
    public DelaunayPanel () {
        initialTriangle = new Simplex<Pnt>(new Pnt(-initialSize, -initialSize),
                new Pnt( initialSize, -initialSize),
                new Pnt(           0,  initialSize));
        dt = new DelaunayTriangulation(initialTriangle);
    }

    /* Events */

    /**
     * Actions for button presses.
     * @param e the ActionEvent
     */
    public void actionPerformed (ActionEvent e) {
        String command = e.getActionCommand();
        if (debug) System.out.println(command);
        if (command.equals("voronoi")) isVoronoi = true;
        else if (command.equals("delaunay")) isVoronoi = false;
        else if (command.equals("clear"))
            dt = new DelaunayTriangulation(initialTriangle);
        repaint();
    }

    /**
     * Mouse press.
     * @param e the MouseEvent
     */
    public void mousePressed (MouseEvent e) {
        if (e.getComponent() != this) return;
        String content = readFileByChars("d:\\data1.txt");
        //�����ļ����������ݣ�һ��һ���ķ���dt��
        try {
            JSONArray array = new JSONArray(content);
            for(int i=0;i<array.length();i++)
            {
                JSONObject json = array.getJSONObject(i);
                double x = json.getDouble("x");
                double y = json.getDouble("y");
                double sort = json.getDouble("sort");
                Pnt point = new Pnt(x,y,sort);
                //Pnt point = new Pnt(x,y);
                dt.delaunayPlace(point);
                repaint();
            }
            System.out.println(dt);
            calculateNeighbors();
            //System.out.println(array);
        } catch (JSONException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }



        //      System.out.print(e.getX()+",");
        //      System.out.println(e.getY());
        //      Pnt point = new Pnt(e.getX(),e.getY());
        //      dt.delaunayPlace(point);
        //      repaint();

    }

    private void calculateNeighbors()
    {
        java.util.List<Pnt> Pnts = new ArrayList<Pnt>();
        java.util.List<Pnt> neighborPnts = null;
        //������ĳ����Ϊ�����������ε����е�
        for(Simplex<Pnt> triangle:dt)
        {
            for(Pnt point :triangle)//����ÿһ����
            {
                if(!Pnts.contains(point))
                {
                    Pnts.add(point);
                    neighborPnts = new ArrayList<Pnt>();

                    System.out.print("�õ�"+point.toString()+"���ٽ���������");

                    for(Simplex<Pnt> otherTriangle:dt)
                    {
                        if(otherTriangle.contains(point))
                        {
                            for(Pnt other:otherTriangle)
                            {
                                if(!other.equals(point))
                                {
                                    if(!neighborPnts.contains(other))
                                    {
                                        neighborPnts.add(other);
                                        System.out.print(other.toString()+"��");
                                    }
                                }
                            }
                        }
                    }
                    System.out.println();
                }
            }
        }
    }



    /**
     * ��ȡ�ļ�����
     * @param fileName
     * @return
     */
    public String readFileByChars(String fileName) {
        File file = new File(fileName);
        String content = "";
        Reader reader = null;

        try {
            System.out.println("���ַ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ������ֽڣ�");
            // һ�ζ������ַ�
            char[] tempchars = new char[30];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName));
            // ���������ַ����ַ������У�charreadΪһ�ζ�ȡ�ַ���
            while ((charread = reader.read(tempchars)) != -1) {
                // ͬ�����ε�\r����ʾ
                if ((charread == tempchars.length)
                        && (tempchars[tempchars.length - 1] != '\r')) {
                    content += String.valueOf(tempchars);
                    System.out.print(tempchars);
                } else {
                    for (int i = 0; i < charread; i++) {
                        if (tempchars[i] == '\r') {
                            continue;
                        } else {
                            content+=tempchars[i];
                            System.out.print(tempchars[i]);
                        }
                    }
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
            return content;
        }
    }


    /**
     * MouseEnter events.
     * @param e the MouseEvent
     */
    public void mouseEntered (MouseEvent e) {
        if (e.getComponent() == this) return;
        String name = e.getComponent().getName();
        if (debug) System.out.println("Entering " + name);
        showCircles = (name == "circles");
        showDelaunay = (name == "delaunay");
        showVoronoi = (name == "voronoi");
        repaint();
    }

    /**
     * MouseExit events.
     * @param e the MouseEvent
     */
    public void mouseExited (MouseEvent e) {
        if (e.getComponent() == this) return;
        if (debug) System.out.println("Exiting");
        showCircles = false;
        showDelaunay = false;
        showVoronoi = false;
        repaint();
    }

    /**
     * MouseClick event (not used, but needed for MouseListener).
     */
    public void mouseClicked (MouseEvent e) {}

    /**
     * MouseRelease event (not used, but needed for MouseListener).
     */
    public void mouseReleased (MouseEvent e) {}

    /* Basic Drawing Methods */

    /**
     * Draw a point.
     * @param point the Pnt to draw
     */
    public void draw (Pnt point) {
        int r = pointRadius;
        int x = (int) point.coord(0);
        int y = (int) point.coord(1);
        int sort = 5;
        if(point.dimension()==3)
        {
            sort = (int)point.coord(2);
        }
        Color c = null;
        switch(sort)
        {
            case 0:c = Color.red;
                break;
            case 1:c=Color.green;
                break;
            case 2:c=Color.blue;
                break;
            case 3:c = Color.BLACK;
                break;
            case 4:c=Color.gray;
                break;
            default:
                break;
        }
        g.setColor(c);
        g.fillOval(x-r, y-r, r+r, r+r);
    }

    /**
     * Draw a line segment.
     * @param endA one endpoint
     * @param endB the other endpoint
     */
    public void draw (Pnt endA, Pnt endB) {
        g.drawLine((int)endA.coord(0), (int)endA.coord(1),
                (int)endB.coord(0), (int)endB.coord(1));
    }

    /**
     * Draw a circle.
     * @param center the center of the circle
     * @param radius the circle's radius
     * @param fillColor; null implies no fill
     */
    public void draw (Pnt center, double radius, Color fillColor) {
        int x = (int) center.coord(0);
        int y = (int) center.coord(1);
        int r = (int) radius;
        if (fillColor != null) {
            Color temp = g.getColor();
            g.setColor(fillColor);
            g.fillOval(x-r, y-r, r+r, r+r);
            g.setColor(temp);
        }
        g.drawOval(x-r, y-r, r+r, r+r);
    }

    /* Higher Level Drawing Methods */

    /**
     * Handles painting entire contents of DelaunayPanel.
     * Called automatically; requested via call to repaint().
     * @param g the Graphics context
     */
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        this.g = g;

        // Flood the drawing area with a "background" color
        Color temp = g.getColor();
        if (!isVoronoi) g.setColor(delaunayColor);//��������̩ɭ�����Σ���������������ɫ
        else if (dt.contains(initialTriangle)) g.setColor(this.getBackground());
        else g.setColor(voronoiColor);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(temp);

        // Draw the appropriate picture
        if (isVoronoi) {
            drawAllVoronoi();
            drawAllSites();
        }
        else drawAllDelaunay();

        // Draw any extra info due to the mouse-entry switches
        temp = g.getColor();
        if (isVoronoi) g.setColor(delaunayColor);
        else g.setColor(voronoiColor);
        if (showCircles) drawAllCircles();
        if (showDelaunay) drawAllDelaunay();
        if (showVoronoi) drawAllVoronoi();
        g.setColor(temp);
    }

    /**
     * Draw all the Delaunay edges.
     */
    public void drawAllDelaunay () {
        // Loop through all the edges of the DT (each is done twice)
        for (Simplex<Pnt> triangle: dt)
            for (Set<Pnt> edge: triangle.facets()) {
                Pnt[] endpoint = edge.toArray(new Pnt[2]);
                draw(endpoint[0], endpoint[1]);
            }
    }

    /**
     * Draw all the Voronoi edges.
     */
    public void drawAllVoronoi () {
        // Loop through all the edges of the DT (each is done twice)
        for (Simplex<Pnt> triangle: dt)
            for (Simplex<Pnt> other: dt.neighbors(triangle)) {
                Pnt p = Pnt.circumcenter(triangle.toArray(new Pnt[0]));
                Pnt q = Pnt.circumcenter(other.toArray(new Pnt[0]));
                draw(p,q);
            }
    }

    /**
     * Draw all the sites (i.e., the input points) of the DT.
     */
    public void drawAllSites () {
        // Loop through all sites of the DT
        // Each is done several times, about 6 times each on average; this
        // can be proved via Euler's formula.
        for (Simplex<Pnt> triangle: dt)
            for (Pnt site: triangle)
                draw(site);
    }

    /**
     * Draw all the empty circles (one for each triangle) of the DT.
     */
    public void drawAllCircles () {
        // Loop through all triangles of the DT
        loop: for (Simplex<Pnt> triangle: dt) {
            for (Pnt p: initialTriangle)  // Skip circles invoving initialTriangle
                if (triangle.contains(p)) continue loop;
            Pnt c = Pnt.circumcenter(triangle.toArray(new Pnt[0]));
            double radius = c.subtract(triangle.iterator().next()).magnitude();
            draw(c, radius, null);
        }
    }
}