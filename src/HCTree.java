import java.io.*;
import java.util.Stack;

/**
 * The Huffman Coding Tree
 */
public class HCTree {

    private static final int NUM_CHARS = 256; // alphabet size of extended ASCII
    private static final int BYTE_BITS = 8; // number of bits in a byte

    private HCNode root; // the root of HCTree
    private HCNode[] leaves = new HCNode[NUM_CHARS]; // the leaves of HCTree that contain all the symbols

    /**
     * The Huffman Coding Node
     */
    protected class HCNode implements Comparable<HCNode> {

        byte symbol; // the symbol contained in this HCNode
        int freq; // the frequency of this symbol
        HCNode c0, c1, parent; // c0 is the '0' child, c1 is the '1' child

        /**
         * Initialize a HCNode with given parameters
         *
         * @param symbol the symbol contained in this HCNode
         * @param freq   the frequency of this symbol
         */
        HCNode(byte symbol, int freq) {
            this.symbol = symbol;
            this.freq = freq;
        }

        /**
         * Getter for symbol
         *
         * @return the symbol contained in this HCNode
         */
        byte getSymbol() {
            return this.symbol;
        }

        /**
         * Setter for symbol
         *
         * @param symbol the given symbol
         */
        void setSymbol(byte symbol) {
            this.symbol = symbol;
        }

        /**
         * Getter for freq
         *
         * @return the frequency of this symbol
         */
        int getFreq() {
            return this.freq;
        }

        /**
         * Setter for freq
         *
         * @param freq the given frequency
         */
        void setFreq(int freq) {
            this.freq = freq;
        }

        /**
         * Getter for '0' child of this HCNode
         *
         * @return '0' child of this HCNode
         */
        HCNode getC0() {
            return c0;
        }

        /**
         * Setter for '0' child of this HCNode
         *
         * @param c0 the given '0' child HCNode
         */
        void setC0(HCNode c0) {
            this.c0 = c0;
        }

        /**
         * Getter for '1' child of this HCNode
         *
         * @return '1' child of this HCNode
         */
        HCNode getC1() {
            return c1;
        }

        /**
         * Setter for '1' child of this HCNode
         *
         * @param c1 the given '1' child HCNode
         */
        void setC1(HCNode c1) {
            this.c1 = c1;
        }

        /**
         * Getter for parent of this HCNode
         *
         * @return parent of this HCNode
         */
        HCNode getParent() {
            return parent;
        }

        /**
         * Setter for parent of this HCNode
         *
         * @param parent the given parent HCNode
         */
        void setParent(HCNode parent) {
            this.parent = parent;
        }

        /**
         * Check if the HCNode is leaf (has no children)
         *
         * @return if it's leaf, return true. Otherwise, return false.
         */
        boolean isLeaf() {
            if(this.getC0() == null && this.getC1() == null){
                return true;
            }
            return false;
        }

        /**
         * String representation
         *
         * @return string representation
         */
        public String toString() {
            return "Symbol: " + this.symbol + "; Freq: " + this.freq;
        }

        /**
         * Compare two nodes
         *
         * @param o node to compare
         * @return int positive if this node is greater
         */
        public int compareTo(HCNode o) {
            if(this.getFreq() > o.getFreq()){
                return 1;
            }
            if(this.getFreq() < o.getFreq()){
                return -1;
            }
            else {
                if (this.getSymbol() > o.getSymbol()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
    }

    /**
     * Returns the root node
     *
     * @return root node
     */
    public HCNode getRoot() {
        return root;
    }

    /**
     * Sets the root node
     *
     * @param root node to set
     */
    public void setRoot(HCNode root) {
        this.root = root;
    }

    /**
     * TODO
     *
     * @param freq
     */
    public void buildTree(int[] freq) {
        MyPriorityQueue<HCNode> pq = new MyPriorityQueue(NUM_CHARS);
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) {
                HCNode n = new HCNode((byte) i, freq[i]);
                pq.offer(n);
                leaves[n.getSymbol() & 0xff] = n;
            }
        }
        while (pq.size() > 1) {
            HCNode c0 = pq.poll();
            HCNode c1 = pq.poll();
            HCNode node = new HCNode(c0.getSymbol(), c0.getFreq() + c1.getFreq());
            node.setC0(c0);
            node.setC1(c1);
            c0.setParent(node);
            c1.setParent(node);
            pq.offer(node);
            if (c0.isLeaf()) {
                leaves[c0.getSymbol() & 0xff] = c0;
            }
            if (c1.isLeaf()) {
                leaves[c1.getSymbol() & 0xff] = c1;
            }
        }
        this.setRoot(pq.poll());
    }

    /**
     * TODO
     *
     * @param symbol
     * @param out
     * @throws IOException
     */
    public void encode(byte symbol, BitOutputStream out) throws IOException {
        Stack<Integer> s = new Stack<Integer>();
        int ascii = symbol & 0xff;
        HCNode n = leaves[ascii];
        while (n.getParent() != null) {
            if (n.getParent().getC1() == n) {
                s.add(1);
            } else {
                s.add(0);
            }
            n = n.getParent();
        }
        while(s.size() > 0){
            out.writeBit(s.pop());
        }
    }

    /**
     * TODO
     *
     * @param in
     * @return
     * @throws IOException
     */
    public byte decode(BitInputStream in) throws IOException {
        HCNode n = this.getRoot();
        while (!n.isLeaf()) {
            int bits = in.readBit();
            if (bits == 1) {
                n=n.getC1();
            }
            else {
                n=n.getC0();
            }
        }
        return n.getSymbol();
    }

    public void inorder(HCNode root) {
        if(root == null){
            System.out.print("null");
            return;
        }
        if (root.isLeaf() == false && root.getC0() != null) {
            inorder(root.getC0());
        }
        System.out.println(root.toString() + ", ");
        if (root.isLeaf() == false && root.getC1() != null) {
            inorder(root.getC1());
        }



    }

    /**
     * TODO
     *
     * @param node
     * @param out
     * @throws IOException
     */
    public void encodeHCTree(HCNode node, BitOutputStream out) throws IOException {
        if (node.isLeaf()) {
            Byte symbol = node.getSymbol();
            System.out.println(symbol);
            out.writeBit(1);
            out.writeByte(symbol);
        } else {
            out.writeBit(0);
            encodeHCTree(node.getC0(), out);
            encodeHCTree(node.getC1(), out);
        }
    }

    /**
     * TODO
     *
     * @param in
     * @return
     * @throws IOException
     */
    public HCNode decodeHCTree(BitInputStream in) throws IOException {
        int bit = in.readBit();
        if(bit == 1){
            Byte symbol = in.readByte();
            System.out.println(symbol);
            HCNode n = new HCNode(symbol, 1);
            int ascii = symbol & 0xff;
            leaves[ascii] = n;
            return n;
        }
        HCNode n = new HCNode((byte) bit, 0);
        n.setC0(decodeHCTree(in));
        n.setC1(decodeHCTree(in));
        n.setSymbol(n.getC0().getSymbol());
        this.setRoot(n);
        return n;
    }
}