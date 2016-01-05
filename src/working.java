import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class working{
	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			FileReader file = null;
			try {
				file = new FileReader(args[i]);
				System.out.println("File Opened.");
				scanner lexer = new scanner(file);
				System.out.println("File Scanned.");
				ParserCup parser = new ParserCup(lexer);
				Program root = (Program)parser.parse().value;
				System.out.println("File Parsed. Tree formed. ");
				//traverse the ast - write to output file
				Traverser visitor = new Traverser();
				String path = ".\\output_file.txt";
				visitor.TraverseAST(root, path);
				System.out.println("Output File Created.");
				
			} catch (FileNotFoundException ex) {
				System.out.println("Could not open input file " + args[i]);
			} catch (IOException ex) {
				System.out.println("Unexpected exception in lexer");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
