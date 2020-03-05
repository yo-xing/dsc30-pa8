
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class HCTreeTester{

    @Test
    public void buildTreeTester() throws IOException {
        byte[] input = Files.readAllBytes(Paths.get("src/io/check1.txt"));
        int num_chars = 256;
        int[] freq = new int[num_chars];
        for (Byte b: input) {
            int ascii = b & 0xff;
            ++ freq[ascii];
        }
        /*for (int  i= 0; i < 256; i++) {
            System.out.print("index: ");
            System.out.print(i);
            System.out.print("  val: ");
            System.out.println(freq[i]);
        }
         */
        HCTree tree = new HCTree();
        tree.buildTree(freq);
        tree.inorder(tree.getRoot());
    }
}