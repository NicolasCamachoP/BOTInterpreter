package co.edu.javeriana.bot.ast;

import co.edu.javeriana.bot.utils.*;

public class VarDecl implements ASTNode {

	private String name;
	private ASTNode valor;
	
	public VarDecl(String name, ASTNode valor) {
		super();
		this.name = name;
		this.valor = valor;
	}

	@Override
	public Object execute(Context contexto) {
		if (valor != null) {
			contexto.put(name, valor.execute(contexto));
		} else {
			contexto.put(name, new Object());
		}
		return null;
	}

}
