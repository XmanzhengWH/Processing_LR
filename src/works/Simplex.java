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

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * A Simplex is an immutable set of vertices (usually Pnts).
 *
 * @author Paul Chew
 *
 * Created July 2005. Derived from an earlier, messier version.
 */
class Simplex<V> extends AbstractSet<V> implements Set<V> {

    private List<V> vertices;               // The simplex's vertices
    private long idNumber;                  // The id number
    private static long idGenerator = 0;    // Used to create id numbers
    public static boolean moreInfo = false; // True iff more info in toString

    /**
     * Constructor.
     * @param collection a Collection holding the Simplex vertices
     * @throws IllegalArgumentException if there are duplicate vertices
     */
    public Simplex (Collection<? extends V> collection) {
        this.vertices = Collections.unmodifiableList(new ArrayList<V>(collection));
        this.idNumber = idGenerator++;
        Set<V> noDups = new HashSet<V>(this);
        if (noDups.size() != this.vertices.size())
            throw new IllegalArgumentException("Duplicate vertices in Simplex");
    }

    /**
     * Constructor.
     * @param vertices the vertices of the Simplex.
     * @throws IllegalArgumentException if there are duplicate vertices
     */
    public Simplex (V... vertices) {
        this(Arrays.asList(vertices));
    }

    /**
     * String representation.
     * @return the String representation of this Simplex
     */
    public String toString () {
        if (!moreInfo) return "Simplex" + idNumber;
        return "Simplex" + idNumber + super.toString();
    }

    /**
     * Dimension of the Simplex.
     * @return dimension of Simplex (one less than number of vertices)
     */
    public int dimension () {
        return this.vertices.size() - 1;
    }

    /**
     * True if simplices are neighbors.
     * Two simplices are neighbors if they are the same dimension and they share
     * a facet.
     * @param simplex the other Simplex
     * @return true iff this Simplex is a neighbor of simplex
     */
    public boolean isNeighbor (Simplex<V> simplex) {
        HashSet<V> h = new HashSet<V>(this);
        h.removeAll(simplex);
        return (this.size() == simplex.size()) && (h.size() == 1);
    }

    /**
     * Report the facets of this Simplex.
     * Each facet is a set of vertices <V>.
     * @return an Iterable for the facets of this Simplex
     */
    public List<Set<V>> facets () {
        List<Set<V>> theFacets = new LinkedList<Set<V>>();
        for (V v: this) {
            Set<V> facet = new HashSet<V>(this);
            facet.remove(v);
            theFacets.add(facet);
        }
        return theFacets;
    }

    /**
     * Report the boundary of a Set of Simplices.
     * The boundary is a Set of facets where each facet is a Set of vertices <V>.
     * @return an Iterator for the facets that make up the boundary
     */
    public static <V> Set<Set<V>> boundary (Set<? extends Simplex<V>> simplexSet) {
        Set<Set<V>> theBoundary = new HashSet<Set<V>>();
        for (Simplex<V> simplex: simplexSet) {
            for (Set<V> facet: simplex.facets()) {
                if (theBoundary.contains(facet)) theBoundary.remove(facet);
                else theBoundary.add(facet);
            }
        }
        return theBoundary;
    }

    /* Remaining methods are those required by AbstractSet */

    /**
     * @return Iterator for Simplex's vertices.
     */
    public Iterator<V> iterator () {
        return this.vertices.iterator();
    }

    /**
     * @return the size (# of vertices) of this Simplex
     */
    public int size () {
        return this.vertices.size();
    }

    /**
     * @return the hashCode of this Simplex
     */
    public int hashCode () {
        return (int)(idNumber^(idNumber>>>32));
    }

    /**
     * We want to allow for different simplices that share the same vertex set.
     * @return true for equal Simplices
     */
    public boolean equals (Object o) {
        return (this == o);
    }
}
