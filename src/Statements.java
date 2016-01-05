import java.util.LinkedList;

public class Statements {
	public LinkedList<Statement> mStatements;
	
	Statements(){
		this.mStatements = new LinkedList<Statement>();
	}

	public LinkedList<Statement> getmStatements() {
		return this.mStatements;
	}

	public Statements setmStatements(LinkedList<Statement> mStatements) {
		this.mStatements = mStatements;
		return this;
	}  

}
