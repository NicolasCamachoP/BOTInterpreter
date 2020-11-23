grammar Bot;

@header {

import org.jpavlich.bot.*;
import co.edu.javeriana.bot.ast.*;
import java.util.HashMap;
import java.util.Map;
import co.edu.javeriana.bot.utils.*;


}

@parser::members {

private Bot bot;

public BotParser(TokenStream input, Bot bot) {
    this(input);
    this.bot = bot;
}

}


start: 
	{
		List<ASTNode> body = new ArrayList<ASTNode>();
		Map<String, Object> symbolTable = new HashMap<String, Object>();
		Context contexto = new Context();
	}
	(sentencias{body.add($sentencias.node);})*
	{
		for(ASTNode n: body){
			n.execute(contexto);
		}
	};

sentencias returns [ASTNode node]: (move_right{$node = $move_right.node;} 
								| move_up{$node = $move_up.node;} 
								| move_down{$node = $move_down.node;}
								| move_left{$node = $move_left.node;} 
								| pick{$node = $pick.node;} 
								| drop{$node = $drop.node;}
								| sentencia_if{$node = $sentencia_if.node;} 
								| sentencia_while{$node = $sentencia_while.node;}
								| sentencia_impresion{$node = $sentencia_impresion.node;} 
								| var_decl{$node = $var_decl.node;}
								| var_asig{$node = $var_asig.node;}
								| sentencia_lectura{$node = $sentencia_lectura.node;}
								| func_call{$node = $func_call.node;}
								| define{$node = $define.node;})
								SEMICOLON;

move_right returns [ASTNode node]: MRIGHT expression {$node = new MoveRight($expression.node, bot);};
move_up returns [ASTNode node]: MUP expression {$node = new MoveUp($expression.node, bot);};
move_down returns [ASTNode node]: MDOWN expression {$node = new MoveDown($expression.node, bot);};
move_left returns [ASTNode node]: MLEFT expression {$node = new MoveLeft($expression.node, bot);};
pick returns [ASTNode node]: PICK {$node = new Pick(bot);};
drop returns [ASTNode node]: DROP {$node = new Drop(bot);};


//TODO
var_decl returns [ASTNode node]: DECLARACION ID{
									ASTNode valor = null;
								} (ASIGNACION t1=expresion_logica{
									valor = $t1.node;
								}
								)? {
									$node = new VarDecl($ID.text, valor);
								};
var_asig returns [ASTNode node]: ID ASIGNACION expresion_logica {$node = new VarAsig($ID.text, $expresion_logica.node);};

expresion_logica returns [ASTNode node]:t1=comparacion{$node = $t1.node;} 
										(AND t2=comparacion{$node = new And($node, $t2.node);}
										| OR t2=comparacion{$node = new Or($node, $t2.node);})*;
comparacion returns [ASTNode node]:t1=expression{$node = $t1.node;} (EQ t2=expression{$node = new Igual($node, $t2.node);}
									| MRIGHT t2=expression{$node = new Mayor($node, $t2.node);}
									| MLEFT t2=expression{$node = new Menor($node, $t2.node);}
									| GEQ t2=expression{$node = new MayorIgual($node, $t2.node);}
									| LEQ t2=expression{$node = new MenorIgual($node, $t2.node);}
									| NEQ t2=expression{$node = new Diff($node, $t2.node);})?;
expression returns [ASTNode node]:t1=factor{$node = $t1.node;} (SUMA t2=factor{$node = new Suma($node, $t2.node);}
									|RESTA t2=factor{$node = new Resta($node, $t2.node);})*;
factor returns [ASTNode node]: t1=valor{$node = $t1.node;} (MULT t2=valor{$node = new Multiplicacion($node, $t2.node);}
									|DIV t2=valor{$node = new Division($node, $t2.node);})*;
valor returns [ASTNode node]: NUMERO {$node = new Constante(Float.parseFloat($NUMERO.text));} 
							| STRING{$node = new Constante($STRING.text);}
							| (PAR_OPEN t1=comparacion{$node = $t1.node;} PAR_CLOSE) 
							| (RESTA t2=valor{$node = new InversoAditivo($t2.node);})
							| (NOT t3=comparacion{$node = new Not($t3.node);})
							| BOOLEAN {$node =new Constante(($BOOLEAN.text).equals("@T"));}
							| ID {$node = new VarRef($ID.text);}
							| pick{$node = $pick.node;}
							| drop{$node = $drop.node;}
							| move_right{$node = $move_right.node;}
							| move_left{$node = $move_left.node;}
							| move_down{$node = $move_down.node;}
							| move_up{$node = $move_up.node;};

					
//
define returns [ASTNode node]: DEFINE s1=ID {
			List<String> parametros = new ArrayList<String>();
			List<ASTNode> body = new ArrayList<ASTNode>();
			String nombre = $s1.text;
		} PAR_OPEN ((DECLARACION s2=ID{parametros.add($s2.text);}) (COMMA (DECLARACION s3=ID{parametros.add($s3.text);}))*)? 
		PAR_CLOSE THEN (sentencias{body.add($sentencias.node);})* END{
			$node = new FunctionDecl(nombre, parametros, body);};						
func_call returns [ASTNode node]: ID {
			String nombre = $ID.text;
			List<ASTNode> parametros = new ArrayList<ASTNode>();
		} PAR_OPEN (s1=expression{parametros.add($s1.node);} (COMMA s2=expression{parametros.add($s2.node);})*)? PAR_CLOSE
		{$node = new FunctionCall(nombre, parametros);};

sentencia_if returns [ASTNode node]: IF expresion_logica{
			List<ASTNode> body = new ArrayList<ASTNode>();
			List<ASTNode> elseBody = null;
		} THEN (s1=sentencias{body.add($s1.node);})* (ELSE {
			elseBody = new ArrayList<ASTNode>();
		}(s2 = sentencias{ elseBody.add($s2.node);})*)? END{
			$node = new If($expresion_logica.node, body, elseBody);
		};
		
sentencia_while returns [ASTNode node]: WHILE expresion_logica {
			List<ASTNode> body = new ArrayList<ASTNode>();
		}THEN (s1=sentencias{body.add($s1.node);})* END{
			$node = new While($expresion_logica.node, body);
		};
		
sentencia_impresion returns [ASTNode node]: IMPRESION expresion_logica {$node = new Impresion($expresion_logica.node);};
sentencia_lectura returns [ASTNode node]: LECTURA ID{$node = new VarAsig($ID.text, new Lectura());};





// Los tokens se escriben a continuación de estos comentarios.
// Todo lo que esté en líneas previas a lo modificaremos cuando hayamos visto Análisis Sintáctico

//Palabras Clave
WS
:
	[ \t\r\n]+ -> skip
;


MUP: '^';
MRIGHT: '>';
MDOWN: 'V';
MLEFT: '<';
PICK: 'P';
DROP: 'D';

DECLARACION: '\'';
ASIGNACION: '<-';

NUMERO: [0-9]+('.'[0-9]+)?;
BOOLEAN: '@T' | '@F';
STRING: '"'( '\\"' | . )*?'"';

IF: 'if';
ELSE: 'else';
THEN: '->';
END: 'end';

WHILE: 'while';

LECTURA: '?';
IMPRESION: '$';

SUMA: '+';
RESTA: '-';
MULT: '*';
DIV: '/';


AND: '&';
OR: '|';
NOT: '!';

GEQ:'>=';
LEQ:'<=';
EQ:'=';
NEQ:'<>';

DEFINE: 'define';

PAR_OPEN: '(';
PAR_CLOSE: ')';

SEMICOLON: ';';
COMMA: ',';


ID: [A-Za-z][a-zA-Z0-9_]*;
