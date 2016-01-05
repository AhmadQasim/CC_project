
public class Expression {
	private int type;
	private int number;
	private Boolean bool;
	private String variable;
	private Expression mExp1;
	private Expression mExp2;
	
	public Expression num(int number, int type){
		this.number = number;
		this.type= type;
		return this;
	}
	public Expression var(String variable, int type){
		this.variable = variable;
		this.type = type;
		return this;
	}
	public Expression bool(Boolean bool, int type){
		this.bool = bool;
		this.type = type;
		return this;
	}
	public Expression setExpr(Expression mExp1, Expression mExp2, int type){
		this.mExp1 = mExp1;
		this.mExp2 = mExp2;
		this.type = type;
		return this;
	}
	public Expression AssignExpr(String variable, Expression mExp1, int type){
		this.variable = variable;
		this.mExp1 = mExp1;
		this.type = type;
		return this;
	}
	public Expression uni(Expression mExp, int type){
		this.mExp1 = mExp;
		this.type = type;
		return this;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Boolean getBool() {
		return bool;
	}
	public void setBool(Boolean bool) {
		this.bool = bool;
	}
	public String getVariable() {
		return variable;
	}
	public void setVariable(String variable) {
		this.variable = variable;
	}
	public Expression getmExp1() {
		return mExp1;
	}
	public void setmExp1(Expression mExp1) {
		this.mExp1 = mExp1;
	}
	public Expression getmExp2() {
		return mExp2;
	}
	public void setmExp2(Expression mExp2) {
		this.mExp2 = mExp2;
	}
	
}
