package co.edu.javeriana.bot.ast;

import co.edu.javeriana.bot.utils.*;
import java.util.List;

public class FunctionCall implements ASTNode {
	
	private String name;
	private List<ASTNode> parametros;
	private List<ASTNode> body;
	
	public FunctionCall(String name, List<ASTNode> parametros) {
		super();
		this.name = name;
		this.parametros = parametros;
	}
	@Override
	public Object execute(Context contexto){
		FunctionDecl funcion= (FunctionDecl)contexto.get(name);
		if (funcion != null && parametros.size() == funcion.getParametros().size()) {
			Context nContext = new Context(contexto);
			//Guardar los valores de los parámetros en el nuevo contexto
			for (int i = 0; i < parametros.size(); i++) {
				nContext.put(funcion.getParametros().get(i), parametros.get(i).execute(contexto));
			}
			for (ASTNode n: funcion.getBody()) {
				n.execute(nContext);
			}
		}
		else {
			System.err.print("Cantidad de parámetros incorrecta o función " + name + " no declarada ");
			System.exit(1);
		}
		return null;
	}

}
