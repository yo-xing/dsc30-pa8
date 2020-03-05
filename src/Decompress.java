import java.io.*;

/**
 * Decompress the first given file to the second given file using Huffman coding
 */
public class Decompress {
    private static final int EXP_ARG = 2; // number of expected arguments

    public static void main(String[] args) throws IOException {

        // Check if the number of arguments is correct
        if (args.length != EXP_ARG) {
            System.out.println("Invalid number of arguments.\n" +
                    "Usage: ./decompress <infile outfile>.\n");
            return;
        }

        FileInputStream inFile = new FileInputStream(args[0]);
        DataInputStream in = new DataInputStream(inFile);
        BitInputStream bitIn = new BitInputStream(in);

        FileOutputStream outFile = new FileOutputStream(args[1]);
        DataOutputStream out = new DataOutputStream(outFile);

        /* START OF TODO */

        // read the number of byte from the file
        int numBytes = in.readInt();
        int distinct = bitIn.readBit();
        // decode and build the tree from the "header"
        HCTree t = new HCTree();
        t.decodeHCTree(bitIn);
        System.out.println(t.getRoot());
        t.inorder(t.getRoot());
        // decode the file and write the results
        for (int i = 0; i < numBytes; i++) {
            Byte symbol = t.decode(bitIn);
            out.write(symbol);
        }
        /* END OF TODO */

        inFile.close();
        in.close();
        outFile.close();
        out.close();
        return;
    }
}