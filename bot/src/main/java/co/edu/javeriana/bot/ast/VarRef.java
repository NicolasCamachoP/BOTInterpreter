package co.edu.javeriana.bot.ast;

import co.edu.javeriana.bot.utils.*;

public class VarRef implements ASTNode {

	private String name;
	
	
	
	public VarRef(String name) {
		super();
		this.name = name;
	}



	@Override
	public Object execute(Context contexto) {
		return contexto.get(name);
	}

}
