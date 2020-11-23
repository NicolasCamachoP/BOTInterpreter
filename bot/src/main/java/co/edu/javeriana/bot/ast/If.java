package co.edu.javeriana.bot.ast;

import java.util.List;
import co.edu.javeriana.bot.utils.*;

public class If implements ASTNode {
	
	private ASTNode condicion;
	private List<ASTNode> body;
	private List<ASTNode> elseBody;
	
	public If(ASTNode condicion, List<ASTNode> body, List<ASTNode> elseBody) {
		super();
		this.condicion = condicion;
		this.body = body;
		this.elseBody = elseBody;
	}
	@Override
	public Object execute(Context contexto) {
		Context nContext = new Context(contexto);
		if ((boolean)condicion.execute(nContext)) {
			for(ASTNode n: body) {
				n.execute(nContext);
			}
		} else if (elseBody != null) {
			for(ASTNode n: elseBody) {
				n.execute(nContext);
			}
		}
		return null;
	}

}
