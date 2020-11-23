package co.edu.javeriana.bot.ast;
import co.edu.javeriana.bot.utils.*;

public class Not implements ASTNode {
	
	private ASTNode operando;
	
	
	public Not(ASTNode operando) {
		super();
		this.operando = operando;
	}



	@Override
	public Object execute(Context contexto) {
		return !(boolean)operando.execute(contexto);	
	}

}
