import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RPNStackerV3 {
    
    private double[] pilha;
    private int indiceAtual;

    public RPNStackerV3(int tamanhoPilha) {
        this.pilha = new double[tamanhoPilha];
        this.indiceAtual = -1;
    }

    /**
     * FUNÇÃO DE SCANNING 
     * 
     * A partir do arquivo de entrada, retorna uma lista de Tokens
     * */
    public List<Token> scan(String nomeArquivo) throws IOException, IllegalArgumentException {
        
        List<Token> tokens = new ArrayList<Token>();
        
        File arq = new File(nomeArquivo);
        BufferedReader reader = new BufferedReader(new FileReader(arq));
        
        for (String linha = reader.readLine(); linha != null; linha = reader.readLine()) {
                System.out.println("Lendo input: " + linha);
                tokens.add(parseToken(linha));
            
        }
        reader.close();
        tokens.add(new Token(TokenType.EOF, ""));
        return tokens; 
    }

    /**
     * FUNÇÃO DE SCANNING 
     * 
     * Recebe uma String e retorna o Token correspondente
     * */
    public Token parseToken(String token) throws IllegalArgumentException {
        TokenType tipo;
        token = token.trim();

        if (Regex.isNum(token)) {
            tipo = TokenType.NUM;
        } else if (Regex.isOP(token)) {
            if      (Regex.isPlus(token))  tipo = TokenType.PLUS;
            else if (Regex.isMinus(token)) tipo = TokenType.MINUS;
            else if (Regex.isStar(token))  tipo = TokenType.STAR;
            else if (Regex.isSlash(token)) tipo = TokenType.SLASH;
            else throw new IllegalArgumentException("Operador não reconhecido: " + token);
            
        } else {
            throw new IllegalArgumentException("Token não reconhecido: " + token);
        } 
        
        return new Token(tipo, token);

    }

    public double calcular(List<Token> tokens) throws IllegalArgumentException {
        limparPilha();
        double x, y;

        for (Token token : tokens) {
            switch (token.type) {
                case NUM:
                    push(Double.parseDouble(token.lexeme));
                    break;
                case STAR:
                    push(pop() * pop());
                    break;
                case PLUS:
                    push(pop() + pop());
                    break;
                case MINUS:
                    y = pop();
                    x = pop();
                    push(x - y);
                    break;
                case SLASH:
                    y = pop();
                    x = pop();
                    push(x / y);
                    break;
                case EOF:
                    // do nothing      
                    break;
                default:
                    throw new IllegalArgumentException("Token não reconhecido: " + token.toString());    
            }


        }
        return getResultado();
        

    }

    

    public void push(double valor) throws ArrayIndexOutOfBoundsException {
        indiceAtual++;
        pilha[indiceAtual] = valor;
    }

    public double pop() {
        if (indiceAtual >= 0) {
            double valor = pilha[indiceAtual];
            pilha[indiceAtual] = 0;
            indiceAtual--;
            return valor;
        } else {
            // tentativa de pop em uma pilha vazia
            // representa que o input não está correto no formato RPN
            throw new IllegalArgumentException ("O arquivo de entrada não contém um input RPN aceitável.");
        }
        
    }

    public double getResultado() throws ArithmeticException {
        // após os cálculos a pilha deve conter apenas 1 item, que será o resultado
        // Caso contrário, representa que o input não está correto no formato RPN
        if (indiceAtual == 0) return this.pilha[0];
        else throw new ArithmeticException("Cálculo inválido: O arquivo de entrada não contém um input RPN aceitável.");
    }

    public void limparPilha() {
        if (indiceAtual == 0) pilha[indiceAtual] = 0;
        indiceAtual = -1;

    }


    public static void main(String[] args) throws Exception {
        int tamanhoPilha = 30;
        String[] arquivos = {"./src/input01.txt", 
                             "./src/input02.txt", 
                             "./src/inputErro01.txt", 
                             "./src/inputErro02.txt"};
        List<Token> tokens;
        double resultado;
        
        System.out.println("\n###   CALCULADORA RPN   ###\n");
        System.out.format("Calculadora RPN: Criada uma pilha com %d posições.%n", tamanhoPilha);
        RPNStackerV3 calc = new RPNStackerV3(tamanhoPilha);

        for (String nomeArquivo : arquivos) {
            try {
                System.out.format("\nCalculadora RPN: scaneando tokens do arquivo \"%s\"%n", nomeArquivo);
                tokens = calc.scan(nomeArquivo);
                System.out.println("Calculadora RPN: tokens obtidos:");
                for (Token token : tokens) System.out.println(token);
                
                
                System.out.println("Calculadora RPN: iniciando cálculos");
                resultado = calc.calcular(tokens);
                
                System.out.format("Calculadora RPN: o resultado é %f%n", resultado);            
            
            } catch (IOException e){
                System.out.format("Calculadora RPN: ERRO - O arquivo \"%s\" não foi encontrado.%n", nomeArquivo);
            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println("Calculadora RPN: ERRO - " + e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.format("Calculadora RPN: pilha estourada!" + 
                                    "Para processar o arquivo %s é preciso uma pilha com tamanho maior que %d.%n",
                                    nomeArquivo, tamanhoPilha);
            } 
        }
    }
}