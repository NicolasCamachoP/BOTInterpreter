package co.edu.javeriana.bot.ast;

import co.edu.javeriana.bot.utils.*;

public class Suma implements ASTNode {
	
	private ASTNode operando1;
	private ASTNode operando2;
	
	
	public Suma(ASTNode operando1, ASTNode operando2) {
		super();
		this.operando1 = operando1;
		this.operando2 = operando2;
	}



	@Override
	public Object execute(Context contexto) {
		Object exeResult1 = operando1.execute(contexto);
		Object exeResult2 = operando2.execute(contexto);
		if (exeResult1.getClass().getSimpleName().equals("String") || exeResult1.getClass().getSimpleName().equals("String")) {
			return exeResult1.toString() + exeResult2.toString();
		}
		return (float)operando1.execute(contexto) + (float)operando2.execute(contexto);	
	}

}
