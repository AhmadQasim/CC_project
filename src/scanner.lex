import java_cup.runtime.Symbol;
import java.io.IOException;

%%

%unicode
%line
%column

%public
%class scanner
%final

%implements java_cup.runtime.Scanner, ParserSym
%function next_token
%type java_cup.runtime.Symbol

%init{
	// TODO: code that goes to constructor
%init}

%eofval{
  return sym(EOF);
%eofval}

%{ 
	private Symbol sym(int type)
	{
		return sym(type, yytext());
	}

	private Symbol sym(int type, Object value)
	{
		return new Symbol(type,yyline,yycolumn,value);
	}
%}

BLANKCHAR = (" "|\t|\n|\r)
WHITESPACE = {BLANKCHAR}+

%xstate COMMENTS
%xstate LITERAL

%%
//white spaces and comments getting removed
{WHITESPACE} {}
"//"	{yybegin(COMMENTS);}
<COMMENTS>{
"\n"	{yybegin(YYINITIAL);}
.	{}
}	
//the literals should be detected and instead of being removed a LITERAL type should be returned.



"begin"			{return sym(BEGIN);}
"var"			{return sym(VAR);}
"end"			{return sym(END);}
"and"			{return sym(AND);}
"not"			{return sym(NOT);}
"or"			{return sym(OR);}
"("  			{return sym(LPAREN);}
")"				{return sym(RPAREN);}
"{"				{/*return sym(SQLPAREN);*/}
"}"				{/*return sym(SQRPAREN);*/}
//";"				{return sym(SEMI);}
[0-9]+   		{return sym(NUMBER);}
"while"   		{return sym(WHILE);}
"do"      		{return sym(DO);}
"true" 	{return sym(TRUE);}
"false" 	{return sym(FALSE);}
"-"   			{return sym(MINUS);}
"+"    			{return sym(PLUS);}
"*"				{return sym(TIMES);}
"%"     		{return sym(MOD);}
"/"  			{return sym(DIVIDE);}
"if"      		{return sym(IF);}
"else"    		{return sym(ELSE);}
"while"			{return sym(WHILE);}
"do"			{return sym(DO);}
"var"     		{return sym(VAR);}
"input"   		{return sym(INPUT);}
"output"		{return sym(OUTPUT);}
">"				{return sym(GT);}
"<"				{return sym(LT);}
">="				{return sym(GE);}
"<="				{return sym(LE);}
"=="				{return sym(EE);}
"!="				{return sym(NE);}
"then"    		{return sym(THEN);}
":="				{return sym(ASSIGN);}
"&&"			{return sym(AND);}
"||"			{return sym(OR);}
[a-zA-Z_]([a-zA-Z0-9_])*           	{return sym(VARIABLE);}
.				{/*System.out.println("Symbol not found.");*/}

//we need to find a clever way to find the varaibles and return it to parser or is it going to be handled by parser, to detect varaibles?