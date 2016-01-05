
public class Statement {
	private int type;
	private Expression mExp;
	private Block block_Spec1;
	private Block block_Spec2;
	private String variable;
	
	public Statement ifstmt(Expression mExp, Block block_Spec1, int type){
		this.mExp = mExp;
		this.block_Spec1 = block_Spec1;
		this.type = type;
		return this;
	}
	
	public Statement ifelsestmt(Expression mExp, Block block_Spec1, Block block_Spec2, int type){
		this.mExp = mExp;
		this.block_Spec1 = block_Spec1;
		this.block_Spec2 = block_Spec2;
		this.type= type;
		return this;
	}
	
	public Statement whilestmt(Expression mExp, Block block_Spec1, int type){
		this.mExp = mExp;
		this.block_Spec1 = block_Spec1;
		this.type= type;
		return this;
	}
	
	public Statement outputstmt(Expression mExp, int type){
		this.mExp = mExp;
		this.type= type;
		return this;
	}
	
	public Statement inputstmt(String v, int type){
		this.variable = v;
		this.type= type;
		return this;
	}
	
	public Statement varstmt(String variable, int type){
		this.variable = variable;
		this.type= type;
		return this;
	}
	
	public Statement exprstmt(Expression mExp, int type){
		this.mExp = mExp;
		this.type= type;
		return this;
	}
	
	public Expression getmExp() {
		return mExp;
	}
	public void setmExp(Expression mExp) {
		this.mExp = mExp;
	}
	public Block getBlock_Spec1() {
		return block_Spec1;
	}
	public void setBlock_Spec1(Block block_Spec1) {
		this.block_Spec1 = block_Spec1;
	}
	public Block getBlock_Spec2() {
		return block_Spec2;
	}
	public void setBlock_Spec2(Block block_Spec2) {
		this.block_Spec2 = block_Spec2;
	}
	public String getVariable() {
		return variable;
	}
	public void setVariable(String variable) {
		this.variable = variable;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
