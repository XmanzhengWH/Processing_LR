package others;

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

import works.Pnt;
import works.Simplex;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Delaynay������
 * A 2D Delaunay Triangulation (DT) with incremental site insertion.
 * This is not the fastest way to build a DT, but it's a reasonable way
 * to build the DT incrementally and it makes a nice interactive display.
 * There are several O(n log n) methods, but they require that either (1)
 * the sites are all known initially or (2) the sites are inserted in random
 * order.
 *
 * @author Paul Chew
 *
 * Created July 2005.  Derived from an earlier, messier version.
 */
public class DelaunayTriangulation extends Triangulation<Pnt> {

    private Simplex<Pnt> mostRecent = null;  // Most recently inserted triangle
    public boolean debug = false;            // Used for debugging

    /**
     * Constructor.
     * All sites must fall within the initial triangle.
     * @param triangle the initial triangle
     */
    public DelaunayTriangulation (Class<?> triangle) {
        super(triangle);
        mostRecent = triangle;
    }

    /**
     * Locate the triangle with point (a Pnt) inside (or on) it.
     * @param point the Pnt to locate
     * @return triangle (Simplex<Pnt>) that holds the point; null if no such triangle
     */
    public Simplex<Pnt> locate (Pnt point) {
        Simplex<Pnt> triangle = mostRecent;
        if (!this.contains(triangle)) triangle = null;
        System.out.println("22222222222222");
        // Try a directed walk (this works fine in 2D, but can fail in 3D)
        Set<Simplex<Pnt>> visited = new HashSet<Simplex<Pnt>>();
        while (triangle != null) {
            if (visited.contains(triangle)) { // This should never happen
                System.out.println("Warning: Caught in a locate loop");
                break;
            }
            visited.add(triangle);
            // Corner opposite point
            Pnt corner = point.isOutside(triangle.toArray(new Pnt[0]));//�����������棬return null
            if (corner == null)
                return triangle;
            triangle = this.neighborOpposite(corner, triangle);
        }
        // No luck; try brute force
        System.out.println("Warning: Checking all triangles for " + point);
        for (Simplex<Pnt> tri: this) {
            if (point.isOutside(tri.toArray(new Pnt[0])) == null)
                return tri;
        }
        // No such triangle
        System.out.println("Warning: No triangle holds " + point);
        return null;
    }

    /**
     * Place a new point site into the DT.��һ�����Ž���������
     * @param site the new Pnt
     * @return set of all new triangles created
     */
    public Set<Simplex<Pnt>> delaunayPlace (Pnt site) {
        Set<Simplex<Pnt>> newTriangles = new HashSet<Simplex<Pnt>>();//�������μ�
        Set<Simplex<Pnt>> oldTriangles = new HashSet<Simplex<Pnt>>();//�������μ�
        Set<Simplex<Pnt>> doneSet = new HashSet<Simplex<Pnt>>();
        Queue<Simplex<Pnt>> waitingQ = new LinkedList<Simplex<Pnt>>();
        System.out.println("1111111111111111");
        // Locate containing triangle
        if (debug)
            System.out.println("Locate");
        Pnt point = new Pnt(site.coord(0),site.coord(1));
        Simplex<Pnt> triangle = locate(point);//���������������ڵ�������
        // Give up if no containing triangle or if site is already in DT
        if (triangle == null || triangle.contains(point))
            return newTriangles;

        // Find Delaunay cavity (those triangles with site in their circumcircles)
        if (debug)
            System.out.println("Cavity");
        waitingQ.add(triangle);
        while (!waitingQ.isEmpty()) {
            triangle = waitingQ.remove();
            if (point.vsCircumcircle(triangle.toArray(new Pnt[0])) == 1)
                continue;
            oldTriangles.add(triangle);
            for (Simplex<Pnt> tri: this.neighbors(triangle)) {
                if (doneSet.contains(tri))
                    continue;
                doneSet.add(tri);
                waitingQ.add(tri);
            }
        }
        // Create the new triangles
        if (debug)
            System.out.println("Create");
        for (Set<Pnt> facet: Simplex.boundary(oldTriangles)) {
            facet.add(point);
            newTriangles.add(new Simplex<Pnt>(facet));
        }
        // Replace old triangles with new triangles
        if (debug)
            System.out.println("Update");
        this.update(oldTriangles, newTriangles);

        // Update mostRecent triangle
        if (!newTriangles.isEmpty()) mostRecent = newTriangles.iterator().next();
        return newTriangles;
    }

    /**
     * Main program; used for testing.
     */
    public static void main (String[] args) {
        Simplex<Pnt> tri = new Simplex<Pnt>(new Pnt(-10,10), new Pnt(10,10), new Pnt(0,-10));//�����㼯
        System.out.println("Triangle created: " + tri);
        DelaunayTriangulation dt = new DelaunayTriangulation(tri);//����������
        System.out.println("DelaunayTriangulation created: " + dt);
        dt.delaunayPlace(new Pnt(0,0));
        dt.delaunayPlace(new Pnt(1,0));
        dt.delaunayPlace(new Pnt(0,1));
        System.out.println("After adding 3 points, the DelaunayTriangulation is a " + dt);
        dt.printStuff();
    }
}
