package co.edu.javeriana.bot.ast;

import org.jpavlich.bot.*;
import co.edu.javeriana.bot.utils.*;

public class MoveLeft implements ASTNode {
	
	private ASTNode cantidad;
	private Bot bot;
	
	
	
	public MoveLeft(ASTNode cantidad, Bot bot) {
		super();
		this.cantidad = cantidad;
		this.bot = bot;
	}

	@Override
	public Object execute(Context contexto) {
		float cant = (Float)cantidad.execute(contexto);
		return (float)bot.left((int)cant);
	}

}
