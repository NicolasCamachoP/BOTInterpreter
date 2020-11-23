package co.edu.javeriana.bot.ast;

import co.edu.javeriana.bot.utils.*;
import java.util.List;

public class FunctionDecl implements ASTNode {
	
	private String name;
	private List<String> parametros;
	private List<ASTNode> body;
	
	public FunctionDecl(String name, List<String> parametros, List<ASTNode> body) {
		super();
		this.name = name;
		this.parametros = parametros;
		this.body = body;
	}
	@Override
	public Object execute(Context contexto) {
		contexto.put(name, this);
		return null;
	}
	public List<String> getParametros() {
		return parametros;
	}
	public List<ASTNode> getBody() {
		return body;
	}
	
}
