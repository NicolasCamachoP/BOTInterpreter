package co.edu.javeriana.bot.ast;

import co.edu.javeriana.bot.utils.*;

public class Impresion implements ASTNode {
	
	private ASTNode datos;
	
	
	
	public Impresion(ASTNode datos) {
		super();
		this.datos = datos;
	}

	@Override
	public Object execute(Context contexto) {
		System.out.println(datos.execute(contexto));
		return null;
	}

}
