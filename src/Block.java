
public class Block {
	private Statement mStmt;
	private Statements mStmts;
	
	public Statement getmStmt() {
		return mStmt;
	}
	public Block setmStmt(Statement mStmt) {
		this.mStmt = mStmt;
		this.mStmts = null;
		return this;
	}
	public Statements getmStmts() {
		return mStmts;
	}
	public Block setmStmts(Statements mStmts) {
		this.mStmts = mStmts;
		this.mStmt = null;
		return this;
	}
}
