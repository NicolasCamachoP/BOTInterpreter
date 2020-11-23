package co.edu.javeriana.bot.ast;

import co.edu.javeriana.bot.utils.*;
import java.util.List;

public class While implements ASTNode {
	
	private ASTNode condicion;
	private List<ASTNode> body;
	
	public While(ASTNode condicion, List<ASTNode> body) {
		super();
		this.condicion = condicion;
		this.body = body;
	}
	@Override
	public Object execute(Context contexto) {
		Context nContext = new Context(contexto);
		while((boolean)condicion.execute(nContext)) {
			for(ASTNode n: body) {
				n.execute(nContext);
			}
		}
		return null;
	}

}
