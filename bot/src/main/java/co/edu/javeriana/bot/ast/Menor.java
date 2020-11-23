package co.edu.javeriana.bot.ast;

import co.edu.javeriana.bot.utils.*;

public class Menor implements ASTNode {
	
	private ASTNode operando1;
	private ASTNode operando2;
	
	
	public Menor(ASTNode operando1, ASTNode operando2) {
		super();
		this.operando1 = operando1;
		this.operando2 = operando2;
	}



	@Override
	public Object execute(Context contexto) {
		return ((float)operando1.execute(contexto)) < ((float)operando2.execute(contexto));	
	}

}
