package co.edu.javeriana.bot.ast;

import co.edu.javeriana.bot.utils.*;

public class Constante implements ASTNode {
	private Object value;
	
	
	public Constante(Object value) {
		super();
		this.value = value;
	}


	@Override
	public Object execute(Context contexto) {
		if (value.getClass().getSimpleName().equals("String")) {
			value = ((String)value).replace("\\","");
			if (((String)value).startsWith("\"") && ((String)value).endsWith("\"")) {
				value = ((String)value).substring(1, ((String)value).length() - 1);
			}
		}
		return value;
	}

}
