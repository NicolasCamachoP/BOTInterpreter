package co.edu.javeriana.bot.ast;
import co.edu.javeriana.bot.utils.*;

public class Or implements ASTNode {
	
	private ASTNode operando1;
	private ASTNode operando2;
	
	
	public Or(ASTNode operando1, ASTNode operando2) {
		super();
		this.operando1 = operando1;
		this.operando2 = operando2;
	}



	@Override
	public Object execute(Context contexto) {
		boolean a = (boolean)operando1.execute(contexto);
		if (a) {
			return true;
		}else {
			return a || (boolean)operando2.execute(contexto);
		}	
	}

}
