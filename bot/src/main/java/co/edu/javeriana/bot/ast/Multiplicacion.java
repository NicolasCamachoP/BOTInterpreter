package co.edu.javeriana.bot.ast;

import co.edu.javeriana.bot.utils.*;

public class Multiplicacion implements ASTNode {
	
	private ASTNode operando1;
	private ASTNode operando2;
	
	
	public Multiplicacion(ASTNode operando1, ASTNode operando2) {
		super();
		this.operando1 = operando1;
		this.operando2 = operando2;
	}

	@Override
	public Object execute(Context contexto) {
		float a = (float)operando1.execute(contexto);
		if (a == 0) {
			return 0;
		}else {
			return a * (float)operando2.execute(contexto);
		}		
	}
}
