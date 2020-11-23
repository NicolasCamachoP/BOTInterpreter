package co.edu.javeriana.bot.ast;

import org.jpavlich.bot.*;
import co.edu.javeriana.bot.utils.*;

public class MoveDown implements ASTNode {
	
	private ASTNode cantidad;
	private Bot bot;
	
	
	
	public MoveDown(ASTNode cantidad, Bot bot) {
		super();
		this.cantidad = cantidad;
		this.bot = bot;
	}

	@Override
	public Object execute(Context contexto) {
		float cant = (Float)cantidad.execute(contexto);
		return (float)bot.down((int)cant);
	}

}
