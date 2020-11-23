package co.edu.javeriana.bot.ast;

import org.jpavlich.bot.*;
import co.edu.javeriana.bot.utils.*;

public class Pick implements ASTNode {

	private Bot bot;
	
	public Pick(Bot bot) {
		super();
		this.bot = bot;
	}


	@Override
	public Object execute(Context contexto) {
		return bot.pick();
	}

}
