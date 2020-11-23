package co.edu.javeriana.bot.ast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import co.edu.javeriana.bot.utils.Context;


public class Lectura implements ASTNode {

	@Override
	public Object execute(Context contexto) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
