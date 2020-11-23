package co.edu.javeriana.bot.ast;

import org.jpavlich.bot.*;
import co.edu.javeriana.bot.utils.*;

public class Drop implements ASTNode {

	private Bot bot;
	
	public Drop(Bot bot) {
		super();
		this.bot = bot;
	}


	@Override
	public Object execute(Context contexto) {
		return bot.drop();
	}

}
