import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RPNStackerV2 {
    
    private double[] pilha;
    private int indiceAtual;

    public RPNStackerV2(int tamanhoPilha) {
        this.pilha = new double[tamanhoPilha];
        this.indiceAtual = -1;
    }

    public List<Token> scan(String nomeArquivo) throws IOException, IllegalArgumentException {
        
        List<Token> tokens = new ArrayList<Token>();
        
        File arq = new File(nomeArquivo);
        BufferedReader reader = new BufferedReader(new FileReader(arq));
        
        for (String linha = reader.readLine(); 
            linha != null; 
            linha = reader.readLine()) {
                System.out.println("Lendo input: " + linha);
                tokens.add(parseToken(linha));
            
        }
        reader.close();
        tokens.add(new Token(TokenType.EOF, ""));
        return tokens; 
    }

    public double calcular(List<Token> tokens) throws IllegalArgumentException {
        limparPilha();

        //double valor = 0;
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
            //push(valor);

        }
        return getResultado();
        

    }

    public Token parseToken(String linha) throws IllegalArgumentException {
        Token resposta;
        
        try {
            // lança exceção NumberFormatException se não for número
            Double.parseDouble(linha); 
            resposta = new Token(TokenType.NUM, linha);
            
        } catch (NumberFormatException e) {
            switch (linha) {
                case "*":
                    resposta = new Token(TokenType.STAR, linha);
                    break;
                case "+":
                    resposta = new Token(TokenType.PLUS, linha);
                    break;
                case "-":
                    resposta = new Token(TokenType.MINUS, linha);
                    break;
                case "/":
                    resposta = new Token(TokenType.SLASH, linha);
                    break;
                default:
                    throw new IllegalArgumentException("Token não reconhecido: " + linha);
            }
        }
        return resposta;

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
        if (indiceAtual == 0) return this.pilha[0];
        else throw new ArithmeticException("Cálculo inválido.");
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
        
        System.out.format("Calculadora RPN: Criada uma pilha com %d posições.%n", tamanhoPilha);
        RPNStackerV2 calc = new RPNStackerV2(tamanhoPilha);

        for (String nomeArquivo : arquivos) {
            try {
                System.out.format("Calculadora RPN: scaneando tokens do arquivo \"%s\"%n", nomeArquivo);
                tokens = calc.scan(nomeArquivo);
                System.out.println("Calculadora RPN: tokens obtidos:\n" + tokens);
                
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