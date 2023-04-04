import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class RPNStacker {
    
    private double[] pilha;
    private int indiceAtual;

    public RPNStacker(int tamanhoPilha) {
        this.pilha = new double[tamanhoPilha];
        this.indiceAtual = -1;
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

    public void input(String nomeArquivo) throws IOException, IllegalArgumentException {
        limparPilha();
        
        File arq = new File(nomeArquivo);
        BufferedReader reader = new BufferedReader(new FileReader(arq));
        
        for (String linha = reader.readLine(); 
            linha != null; 
            linha = reader.readLine()) {
                System.out.println("Lendo input: " + linha);
                parseSymbol(linha);
            
        }
        reader.close();        

    }


    public void parseSymbol(String simbolo) throws IllegalArgumentException {
        Double valor;
        Double x;
        Double y;
        try {
            valor = Double.parseDouble(simbolo);
            push(valor);
        } catch (NumberFormatException e) {
            switch (simbolo) {
                case "*":
                    valor = pop() * pop();
                    break;
                case "+":
                    valor = pop() + pop();
                    break;
                case "-":
                    y = pop();
                    x = pop();
                    valor = x - y;
                    break;
                case "/":
                    y = pop();
                    x = pop();
                    valor = x / y;
                    break;
                case "^":
                    y = pop();
                    x = pop();
                    valor = Math.pow(x,y);
                    break;
                default:
                    throw new IllegalArgumentException("O arquivo de entrada não contém um input RPN aceitável.");
            }
            push(valor);
        } 

    }

    public double getResultado() throws ArithmeticException {
        if (indiceAtual == 0) return this.pilha[0];
        else throw new ArithmeticException("Não há um resultado calculado.");
    }

    public void limparPilha() {
        if (indiceAtual == 0) pilha[indiceAtual] = 0;
        indiceAtual = -1;

    }


    public static void main(String[] args) throws Exception {
        int tamanhoPilha = 30;
        String nomeArquivo = "./src/input01.txt";
        
        System.out.format("Calculadora RPN: Criada uma pilha com %d posições.%n", tamanhoPilha);
        RPNStacker calc = new RPNStacker(tamanhoPilha);

        try {
            System.out.format("Calculadora RPN: lendo input do arquivo \"%s\"%n", nomeArquivo);
            calc.input(nomeArquivo);
            System.out.format("Calculadora RPN: o resultado é %f%n", calc.getResultado());
            
            nomeArquivo = "./src/input02.txt";
            System.out.format("Calculadora RPN: lendo input do arquivo \"%s\"%n", nomeArquivo);
            calc.input(nomeArquivo);
            System.out.format("Calculadora RPN: o resultado é %f%n", calc.getResultado());
        
        
        } catch (IOException e){
            System.out.format("Calculadora RPN: O arquivo \"%s\" não foi encontrado.%n", nomeArquivo);
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException | ArithmeticException e) {
            System.out.println("Calculadora RPN: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.format("Calculadora RPN: pilha estourada!" + 
                                "Para processar o arquivo %s é preciso uma pilha com tamanho maior que %d.%n",
                                nomeArquivo, tamanhoPilha);
        } 
    }
}