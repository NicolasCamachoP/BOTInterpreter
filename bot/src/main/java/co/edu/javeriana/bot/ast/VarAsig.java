package co.edu.javeriana.bot.ast;

import co.edu.javeriana.bot.utils.*;

public class VarAsig implements ASTNode {

	private String name;
	private ASTNode expression;
	public VarAsig(String name, ASTNode expression) {
		super();
		this.name = name;
		this.expression = expression;
	}

	@Override
	public Object execute(Context contexto) {
			contexto.replace(name, expression.execute(contexto));
		return null;
	}

}
