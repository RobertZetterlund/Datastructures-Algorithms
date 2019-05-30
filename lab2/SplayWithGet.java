
public class SplayWithGet<E extends Comparable<? super E>>
        extends BinarySearchTree<E>
        implements CollectionWithGet<E>{

    /**
     *If the tree contains E e then it is splayed to the root of the tree.
     * @param e element that is searched for in the Tree
     * @return Element e from the tree, or null if not found
     */
    @Override
    public E get(E e) {
    try {
    Entry x = find(e, root);
    if (x == null) {
        return null;
    }

    return root.element;
    }catch(Exception es){
    System.out.println(es);
    es.printStackTrace();
        return null;
        }

    }

    /**
     * Splays element x to the root of the tree by a recursive call to this method.
     * @param x is the Entry that is to be splayed to the root
     */
    protected void splay(Entry x){


        if(x == null){
            return;
            }
        if(x == root || x.parent == null ){ // if x is root we are done
            return;
        }

        if(x.parent.parent == null){   //x does not have a grandparent
                                        // the node's parent is the root
            Entry parent = x.parent;
            if(parent.left != null && parent.left == x){
                zig(parent);
                return;
            }else if(parent.right != null && parent.right == x){
                zag(parent);
                return;
            }
            return;
        }

        //If we reach here: x has a grandparent
        Entry parent = x.parent;
        Entry grandParent = x.parent.parent;

        if(grandParent.left != null && grandParent.left == parent
                && parent.left != null &&parent.left == x){ // LEFT-LEFT SITUATION
            zigzig(grandParent);
        }else if(grandParent.left != null && grandParent.left == parent //LEFT-RIGHT Situation
                && parent.right != null && parent.right == x){//2
            zagzig(grandParent);
        }else if(grandParent.right != null && grandParent.right == parent   //RIGHT-RIGHT Situation
                && parent.right != null && parent.right == x){
            zagzag(grandParent);
        }else if(grandParent.right != null && grandParent.right == parent &&
                parent.left != null && parent.left == x){ // RIGHT LEFT SITUATION
            zigzag(grandParent);
        }

        splay(grandParent); //element x is now in the grandparent node so we make a recursive call
    }


    protected void zigzig(Entry g){
        Entry p = g.left;
        Entry x = p.left;
        Entry t4 = g.right;
        E t = x.element;

        //Algoritm
        //1
        g.left = x.left;
        if(x.left != null){
            x.left.parent = g;
        }
        //2
        p.left = x.right;
        if(p.left != null){
            p.left.parent = p;
        }
        //3
        g.right = p;
        //4
        x.left = p.right;
        if(p.right != null){
            p.right.parent = x;
        }
        //5
        x.right = t4;
        if(t4 != null){
            t4.parent = x;
        }
        //6
        p.right = x;

        //7
        x.element = g.element;
        g.element = t;

    }

    protected Entry find(E elem, Entry t){
        return  find(elem,t,null);
    }
    private Entry find( E elem, Entry t ,Entry previous) {
        if ( t == null ) {
            splay(previous);
            return null;
        }
            int jfr = elem.compareTo( t.element );
            if ( jfr  < 0 ) {
                previous = t;
                return find(elem, t.left,previous);
            } else if ( jfr > 0 ) {
                previous = t;
                return find(elem, t.right,previous);
            } else if(jfr == 0){
                splay(t);
                return t;
            }else{
                return null;
            }

    }  //   find

    /**
     * Makes a rotation that solves the RIGHT-RIGHT situtation.
     * @param g is the grandParent of the Entry that we want to rotate up
     */
    protected void zagzag(Entry g){
        Entry p = g.right;
        Entry x = p.right;
        Entry t4 = g.left;
        E t = x.element;

        //Algoritm
        //1
        g.right = x.right;
        if(x.right != null){
            x.right.parent = g;
        }
        //2
        p.right = x.left;
        if(p.right != null){
            p.right.parent = p;
        }
        //3
        g.left = p;
        //4
        x.right = p.left;
        if(p.left != null){
            p.left.parent = x;
        }
        //5
        x.left = t4;
        if(t4 != null){
            t4.parent = x;
        }
        //6
        p.left = x;

        //7
        x.element = g.element;
        g.element = t;

    }

    /* Rotera 1 steg i hogervarv, dvs
                 x'                 y'
                / \                / \
               y'  C   -->        A   x'
              / \                    / \
             A   B                  B   C
       */

    /**
     * Makes a Clockwise rotate of x so that it's left child is rotated up
     * @param x Parent of the Entry we want to rotate up
     */
    private void zig(Entry x ) {
        Entry   y = x.left;
        E    temp = x.element;
        x.element = y.element;
        y.element = temp;
        x.left    = y.left;
        if ( x.left != null )
            x.left.parent   = x;
        y.left    = y.right;
        y.right   = x.right;
        if ( y.right != null )
            y.right.parent  = y;
        x.right   = y;
    } //   zig
    // ========== ========== ========== ==========

    /* Rotera 1 steg i vanstervarv, dvs
              x'                 y'
             / \                / \
            A   y'  -->        x'  C
               / \            / \
              B   C          A   B
    */
    /**
     * Makes a counter-clockwise rotate of x so that it's right child is rotated up
     * @param x Parent of the Entry we want to rotate up
     */
    private void zag(Entry x ) {
        Entry  y  = x.right;
        E temp    = x.element;
        x.element = y.element;
        y.element = temp;
        x.right   = y.right;
        if ( x.right != null )
            x.right.parent  = x;
        y.right   = y.left;
        y.left    = x.left;
        if ( y.left != null )
            y.left.parent   = y;
        x.left    = y;
    } //   zag
    // ========== ========== ========== ==========

    /* Rotera 2 steg i hogervarv, dvs
              x'                  z'
             / \                /   \
            y'  D   -->        y'    x'
           / \                / \   / \
          A   z'             A   B C   D
             / \
            B   C
    */
    //ZAG-ZIG

    /**
     * Rotation that solves the LEFT-RIGHT SITUATION
     * @param x is the grandparent of the Entry that we want to rotate up.
     */
    private void zagzig( Entry x ) {
        Entry   y = x.left,
                z = x.left.right;
        E       e = x.element;
        x.element = z.element;
        z.element = e;
        y.right   = z.left;
        if ( y.right != null )
            y.right.parent = y;
        z.left    = z.right;
        z.right   = x.right;
        if ( z.right != null )
            z.right.parent = z;
        x.right   = z;
        z.parent  = x;
    }  //  doubleRotateRight
    // ========== ========== ========== ==========

    /* Rotera 2 steg i vanstervarv, dvs
               x'                  z'
              / \                /   \
             A   y'   -->       x'    y'
                / \            / \   / \
               z   D          A   B C   D
              / \
             B   C
     */


    /**
     * Rotation that solves the RIGHT-LEFT SITUATION
     * @param x is the grandparent of the Entry that we want to rotate up.
     */
    private void zigzag( Entry x ) {
        Entry  y  = x.right,
                z  = x.right.left;
        E      e  = x.element;
        x.element = z.element;
        z.element = e;
        y.left    = z.right;
        if ( y.left != null )
            y.left.parent = y;
        z.right   = z.left;
        z.left    = x.left;
        if ( z.left != null )
            z.left.parent = z;
        x.left    = z;
        z.parent  = x;
    } //  doubleRotateLeft


    //TODO Implement the zagzag
    //TODO implement the zigzig


}
