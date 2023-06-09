[CIn UFPE]
[Compiladores 2022.2 CC]

## [Task 01 - RPNStacker Adhoc]

Roteiro:
- rever a ultima aula sobre notacao posfixa [reverse polish notation]
- Implementar uma linguagem RPN stacker em Java/python [ou qualquer linguagem] usando uma pilha como estrutura de dados
- Programa le um arquivo com a expressao em RPN e avaliar

Exemplo de entrada:  
10  
10  
\+  


## Inputs (arquivos): 
input01.txt  
input02.txt  


## Saída:

Calculadora RPN: Criada uma pilha com 30 posições.  
Calculadora RPN: lendo input do arquivo "./src/input01.txt"  
Lendo input: 10  
Lendo input: 5  
Lendo input: +  
Lendo input: 2  
Lendo input: *  
Calculadora RPN: o resultado é 30,000000  
Calculadora RPN: lendo input do arquivo "./src/input02.txt"  
Lendo input: 10  
Lendo input: 2  
Lendo input: ^  
Lendo input: 2  
Lendo input: /  
Lendo input: 5  
Lendo input: 5  
Lendo input: *  
Lendo input: -  
Calculadora RPN: o resultado é 25,000000  


## [Task 02 - RPNStacker Manual/Simple Scanning]

- evoluir o projeto da Task 01 para implementar uma feature de scanning:
   -- No geral, nosso Programa le um arquivo com a expressao em RPN e devolve a expressao avalliada  
   -- a feature de scanning deve retornar uma lista de tokens  
   -- a partir dessa lista de tokens é realizada a interpretacao das expressões com uma pilha  
   -- a feature de scanning deve retornar um erro caso nao reconheca um "num" [numero] ou "op" [operator]  

Exemplo de entrada [com sucesso]:  
10  
10  
\+  

// a lista de tokens reconhecida [caso a imprima]  
Token [type=NUM, lexeme=10]  
Token [type=NUM, lexeme=10]  
Token [type=PLUS, lexeme=+]  

## Inputs (arquivos):
input01.txt  
input02.txt  
inputErro01.txt  
inputErro02.txt  

## Saída:

Calculadora RPN: Criada uma pilha com 30 posições.  
Calculadora RPN: scaneando tokens do arquivo "./src/input01.txt"  
Lendo input: 10  
Lendo input: 5  
Lendo input: +  
Lendo input: 2  
Lendo input: *  
Calculadora RPN: tokens obtidos:  
[Token [type=NUM, lexeme=10], Token [type=NUM, lexeme=5], Token [type=PLUS, lexeme=+], Token [type=NUM, lexeme=2], Token [type=STAR, lexeme=*], Token [type=EOF, lexeme=]]  
Calculadora RPN: iniciando cálculos  
Calculadora RPN: o resultado é 30,000000  

Calculadora RPN: scaneando tokens do arquivo "./src/input02.txt"  
Lendo input: 10  
Lendo input: 2  
Lendo input: *  
Lendo input: 2  
Lendo input: /  
Lendo input: 5  
Lendo input: 5  
Lendo input: *  
Lendo input: -  
Calculadora RPN: tokens obtidos:  
[Token [type=NUM, lexeme=10], Token [type=NUM, lexeme=2], Token [type=STAR, lexeme=*], Token [type=NUM, lexeme=2], Token [type=SLASH, lexeme=/], Token [type=NUM, lexeme=5], Token [type=NUM, lexeme=5], Token [type=STAR, lexeme=*], Token [type=MINUS, lexeme=-], Token [type=EOF, lexeme=]]  
Calculadora RPN: iniciando cálculos  
Calculadora RPN: o resultado é -15,000000  

Calculadora RPN: scaneando tokens do arquivo "./src/inputErro01.txt"  
Lendo input: 10  
Lendo input: 2  
Lendo input: *  
Lendo input: 2  
Lendo input: s  
Calculadora RPN: ERRO - Token não reconhecido: s  

Calculadora RPN: scaneando tokens do arquivo "./src/inputErro02.txt"  
Lendo input: 10  
Lendo input: 2  
Lendo input: *  
Lendo input: 2  
Lendo input: /  
Lendo input: 5  
Lendo input: 5  
Lendo input: *  
Lendo input: -  
Lendo input: 5  
Calculadora RPN: tokens obtidos:  
[Token [type=NUM, lexeme=10], Token [type=NUM, lexeme=2], Token [type=STAR, lexeme=*], Token [type=NUM, lexeme=2], Token [type=SLASH, lexeme=/], Token [type=NUM, lexeme=5], Token [type=NUM, lexeme=5], Token [type=STAR, lexeme=*], Token [type=MINUS, lexeme=-], Token [type=NUM, lexeme=5], Token [type=EOF, lexeme=]]  
Calculadora RPN: iniciando cálculos  
Calculadora RPN: ERRO - Cálculo inválido.  



## [Task 03 - RPNStacker Automatic/Regex Scanning]

Roteiro:
- evoluir o projeto da Task 02 para implementar uma feature de scanning [com Java Regex]:
   -- No geral, nosso Programa le um arquivo com a expressao em RPN e devolve a expressao avalliada
   -- a feature de scanning deve continuar a retornar uma lista de tokens [como ja realizada na Task 01], 
      porém, agora, utilizando regex [regular expressions com Java ou Python]
   -- a partir dessa lista de tokens a interpretacao das expressoes eh realizada com uma pilha
   -- a feature de scanning deve continuar retornar um erro caso nao reconheca um "num" [numero] ou "op" [operator]
   -- implementar as features de regex em uma classe chamada Regex 

Exemplo de entrada [com sucesso]:  
10  
10  
\+  
// a lista de tokens reconhecida [caso a imprima]  
Token [type=NUM, lexeme=10]  
Token [type=NUM, lexeme=10]  
Token [type=PLUS, lexeme=+]  

Saida: 20  

Exemplo de entrada [com falha]:  
10  
s  
\+  
Error: Unexpected character: s  

*Obs: para implementacao da feature de Java regex a classe Regex em anexo devera servir de suporte para o [regex] scanning; note, entretanto, que devera implementar os dois metodos de suporte para o scanning com regex: um para identificar numeros e outro para operacoes.

## Inputs (arquivos):
input01.txt  
input02.txt  
inputErro01.txt  
inputErro02.txt  

## Saída:

\###   CALCULADORA RPN   ###

Calculadora RPN: Criada uma pilha com 30 posições.  

Calculadora RPN: scaneando tokens do arquivo "./src/input01.txt"  
Lendo input: 10  
Lendo input: 5  
Lendo input: +  
Lendo input: 2  
Lendo input: *  
Calculadora RPN: tokens obtidos:  
Token [type=NUM, lexeme=10]  
Token [type=NUM, lexeme=5]  
Token [type=PLUS, lexeme=+]  
Token [type=NUM, lexeme=2]  
Token [type=STAR, lexeme=*]  
Token [type=EOF, lexeme=]  
Calculadora RPN: iniciando cálculos  
Calculadora RPN: o resultado é 30,000000  

Calculadora RPN: scaneando tokens do arquivo "./src/input02.txt"  
Lendo input: 10  
Lendo input: 2  
Lendo input: *  
Lendo input: 2  
Lendo input: /  
Lendo input: 5  
Lendo input: 5  
Lendo input: *  
Lendo input: -  
Calculadora RPN: tokens obtidos:  
Token [type=NUM, lexeme=10]  
Token [type=NUM, lexeme=2]  
Token [type=STAR, lexeme=*]  
Token [type=NUM, lexeme=2]  
Token [type=SLASH, lexeme=/]  
Token [type=NUM, lexeme=5]  
Token [type=NUM, lexeme=5]  
Token [type=STAR, lexeme=*]  
Token [type=MINUS, lexeme=-]  
Token [type=EOF, lexeme=]  
Calculadora RPN: iniciando cálculos  
Calculadora RPN: o resultado é -15,000000  

Calculadora RPN: scaneando tokens do arquivo "./src/inputErro01.txt"  
Lendo input: 10  
Lendo input: 2  
Lendo input: *  
Lendo input: 2  
Lendo input: s  
Calculadora RPN: ERRO - Token não reconhecido: s  

Calculadora RPN: scaneando tokens do arquivo "./src/inputErro02.txt"  
Lendo input: 10  
Lendo input: 2  
Lendo input: *  
Lendo input: 2  
Lendo input: /  
Lendo input: 5  
Lendo input: 5  
Lendo input: *  
Lendo input: -  
Lendo input: 5  
Calculadora RPN: tokens obtidos:  
Token [type=NUM, lexeme=10]  
Token [type=NUM, lexeme=2]  
Token [type=STAR, lexeme=*]  
Token [type=NUM, lexeme=2]  
Token [type=SLASH, lexeme=/]  
Token [type=NUM, lexeme=5]  
Token [type=NUM, lexeme=5]  
Token [type=STAR, lexeme=*]  
Token [type=MINUS, lexeme=-]  
Token [type=NUM, lexeme=5]  
Token [type=EOF, lexeme=]  
Calculadora RPN: iniciando cálculos  
Calculadora RPN: ERRO - Cálculo inválido: O arquivo de entrada não contém um input RPN aceitável.  


