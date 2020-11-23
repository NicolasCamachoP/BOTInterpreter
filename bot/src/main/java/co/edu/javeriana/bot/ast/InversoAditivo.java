package co.edu.javeriana.bot.ast;

import co.edu.javeriana.bot.utils.*;

public class InversoAditivo implements ASTNode {
	private ASTNode value;
	
	
	public InversoAditivo(ASTNode value) {
		super();
		this.value = value;
	}


	@Override
	public Object execute(Context contexto) {
		return -((float)value.execute(contexto));
	}

}
