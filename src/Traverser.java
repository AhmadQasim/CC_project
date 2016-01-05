import java.util.LinkedList;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Traverser implements ParserSym{

	void TraverseAST(Program root, String path) throws IOException {
		
		//open or create file
		File output_file = new File(path);
		
		//if file does not exist
		if(!output_file.exists())
			output_file.createNewFile();
		
		//writing pre-processing
		FileWriter fw = new FileWriter(output_file);
		BufferedWriter bw = new BufferedWriter(fw);
		
		Statements Stmts = root.getmStmts();
		LinkedList<Statement> statement = Stmts.getmStatements();
		
		//start writing
		bw.write("begin");
		TraverseStatements(statement, "\t", bw);
		bw.newLine();
		bw.write("end");
		
		//write ended - closing buffer
		bw.close();
	}
	
	private static void TraverseStatements(LinkedList<Statement> s,String indent, BufferedWriter bw) throws IOException{
		for(int i=s.size()-1;i>=0;i--){
			bw.newLine();
			bw.write(indent);
			switch(s.get(i).getType()){
			case 5:
					bw.write("var ");
					bw.write(s.get(i).getVariable());
					bw.write(";");
					break;
			case 6:
				bw.write("input ");
				bw.write(String.valueOf(s.get(i).getVariable()));
				bw.write(";");
				break;
			case 7:
				bw.write("output ");
				TraverseExpr(s.get(i).getmExp(), bw);
				bw.write(";");
				break;
			case 4:
				bw.write("if (");
				Expression e = s.get(i).getmExp();
				TraverseExpr(e, bw);
				bw.write(") then");
				bw.newLine();
				TraverseBlock(s.get(i).getBlock_Spec1(),indent+"\t", bw);
				break;
			case 8:
				bw.write("if (");
				Expression ifExpr = s.get(i).getmExp();
				TraverseExpr(ifExpr, bw);
				bw.write(") then");
				bw.newLine();
				TraverseBlock(s.get(i).getBlock_Spec1(),indent+"\t", bw);
				bw.newLine();
				bw.write(indent+"else");
				bw.newLine();
				TraverseBlock(s.get(i).getBlock_Spec2(),indent+"\t", bw);
				break;
			case 11:
				bw.write("while (");
				Expression whileExpr = s.get(i).getmExp();
				TraverseExpr(whileExpr, bw);
				bw.write(") do");
				bw.newLine();
				TraverseBlock(s.get(i).getBlock_Spec1(),indent+"\t", bw);
				break;
			case 100:
				Expression Expr = s.get(i).getmExp();
				TraverseExpr(Expr, bw);
				bw.write(";");
				break;
			}
		}
		
		
	}
	private static void TraverseBlock(Block block,String indent, BufferedWriter bw) throws IOException {
		if(block.getmStmts()==null){
			Statement s = block.getmStmt();
			TraverseStatement(s,indent, bw);
		}else{
			Statements Stmts = block.getmStmts();
			LinkedList<Statement> statement = Stmts.getmStatements();
			bw.write(indent.substring(0, indent.length()-1)+"begin");
			TraverseStatements(statement,indent, bw);
			bw.newLine();
			bw.write(indent.substring(0, indent.length()-1)+"end");
		}
	}

	private static void TraverseStatement(Statement s,String indent, BufferedWriter bw) throws IOException {
		bw.write(indent);
		switch(s.getType()){
		case 5:
			bw.write("var " + s.getVariable() + ";");
			break;
		case 6:
			bw.write("input " + s.getVariable() + ";");
			break;
		case 7:
			bw.write("output ");
			TraverseExpr(s.getmExp(), bw);
			bw.write(";");
			break;
		case 4:
			bw.write("if (");
			Expression e = s.getmExp();
			TraverseExpr(e, bw);
			bw.write(") then");
			bw.newLine();
			TraverseBlock(s.getBlock_Spec1(),indent+"\t", bw);
			break;
		case 8:
			bw.write("if (");
			Expression expr = s.getmExp();
			TraverseExpr(expr, bw);
			bw.write(") then");
			bw.newLine();
			TraverseBlock(s.getBlock_Spec1(),indent+"\t", bw);
			bw.newLine();
			bw.write(indent+"else");
			bw.newLine();
			TraverseBlock(s.getBlock_Spec2(),indent+"\t", bw);
			break;
		case 11:
			bw.write("while (");
			Expression whileExpr = s.getmExp();
			TraverseExpr(whileExpr, bw);
			bw.write(") do");
			bw.newLine();
			TraverseBlock(s.getBlock_Spec1(),indent+"\t", bw);
			break;
		case 100:
			Expression Expr = s.getmExp();
			TraverseExpr(Expr, bw);
			bw.write(";");
			break;
		}
	}

	private static void TraverseExpr(Expression e, BufferedWriter bw) throws IOException{
		if(e.getType()==VARIABLE){
			bw.write(e.getVariable());
		}
		else if(e.getType()==NUMBER){
			bw.write(String.valueOf(e.getNumber()));
		}
		else if(e.getType()==UMINUS){
			Expression e1=e.getmExp1();
			bw.write("-");
			bw.write("(");
			TraverseExpr(e1, bw);
			bw.write(")");
		}
		else if(e.getType()==NOT){
			Expression e1=e.getmExp1();
			bw.write("not ");
			bw.write("(");
			TraverseExpr(e1, bw);
			bw.write(")");
		}
		else if(e.getType()==EE){
			Expression e1=e.getmExp1();
			Expression e2=e.getmExp2();
			TraverseExpr(e1, bw);
			bw.write(" == ");
			TraverseExpr(e2, bw);
		}//cater for other expr	
		else if(e.getType()==NE){
			Expression e1=e.getmExp1();
			Expression e2=e.getmExp2();
			TraverseExpr(e1, bw);
			bw.write(" != ");
			TraverseExpr(e2, bw);
		}
		else if(e.getType()==GE){
			Expression e1=e.getmExp1();
			Expression e2=e.getmExp2();
			TraverseExpr(e1, bw);
			bw.write(" >= ");
			TraverseExpr(e2, bw);
		}
		else if(e.getType()==LE){
			Expression e1=e.getmExp1();
			Expression e2=e.getmExp2();
			TraverseExpr(e1, bw);
			bw.write(" <= ");
			TraverseExpr(e2, bw);
		}
		else if(e.getType()==LT){
			Expression e1=e.getmExp1();
			Expression e2=e.getmExp2();
			TraverseExpr(e1, bw);
			bw.write(" < ");
			TraverseExpr(e2, bw);
		}
		else if(e.getType()==GT){
			Expression e1=e.getmExp1();
			Expression e2=e.getmExp2();
			TraverseExpr(e1, bw);
			bw.write(" > ");
			TraverseExpr(e2, bw);
		}
		else if(e.getType()==ASSIGN){
			bw.write(e.getVariable());
			bw.write(" := ");
			Expression e1=e.getmExp1();
			TraverseExpr(e1, bw);
		}
		else if(e.getType()==PLUS){
			Expression e1=e.getmExp1();
			Expression e2=e.getmExp2();
			bw.write("(");
			TraverseExpr(e1, bw);
			bw.write(" + ");
			TraverseExpr(e2, bw);
			bw.write(")");
		}
		else if(e.getType()==MINUS){
			Expression e1=e.getmExp1();
			Expression e2=e.getmExp2();
			bw.write("(");
			TraverseExpr(e1, bw);
			bw.write(" - ");
			TraverseExpr(e2, bw);
			bw.write(")");
		}else if(e.getType()==TIMES){
			Expression e1=e.getmExp1();
			Expression e2=e.getmExp2();
			bw.write("(");
			TraverseExpr(e1, bw);
			bw.write(" * ");
			TraverseExpr(e2, bw);
			bw.write(")");
		}else if(e.getType()==DIVIDE){
			Expression e1=e.getmExp1();
			Expression e2=e.getmExp2();
			bw.write("(");
			TraverseExpr(e1, bw);
			bw.write(" / ");
			TraverseExpr(e2, bw);
			bw.write(")");
		}else if(e.getType()==LPAREN){
			Expression e1=e.getmExp1();
			TraverseExpr(e1, bw);
		}
		else if(e.getType()==AND){
			Expression e1=e.getmExp1();
			Expression e2=e.getmExp2();
			bw.write("(");
			TraverseExpr(e1, bw);
			bw.write(" and ");
			TraverseExpr(e2, bw);
			bw.write(")");
		}
		else if(e.getType()==OR){
			Expression e1=e.getmExp1();
			Expression e2=e.getmExp2();
			bw.write("(");
			TraverseExpr(e1, bw);
			bw.write(" or ");
			TraverseExpr(e2, bw);
			bw.write(")");
		}
		else if(e.getType()==TRUE){
			bw.write("(true)");
		}else if(e.getType()==FALSE){
			bw.write("(false)");
		}
	}
}
