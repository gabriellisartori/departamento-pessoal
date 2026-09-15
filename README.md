# Departamento Pessoal

Sistema de departamento pessoal em Java de uma empresa.

## Funcionalidades

O programa demonstra as seguintes operações sobre uma lista de funcionários:

- **Listagem de funcionários** — Nome, data de nascimento, salário e função
- **Remoção de funcionário** — Remove o funcionário "João" da lista
- **Aumento de salário** — Aplica um reajuste de 10% a todos os salários
- **Agrupamento por função** — Agrupa os funcionários de acordo com sua função
- **Aniversariantes** — Lista os funcionários que fazem aniversário em outubro e dezembro
- **Maior idade** — Identifica o funcionário mais velho
- **Ordem alfabética** — Imprime os funcionários em ordem alfabética
- **Total da folha de pagamento** — Soma de todos os salários
- **Salários mínimos** — Quantidade de salários mínimos que cada funcionário recebe

## Estrutura do projeto

```
departamento-pessoal/
├── src/
│   └── main/
│       └── java/
│           ├── Principal.java                  
│           └── model/
│               ├── Pessoa.java            
│               └── Funcionario.java       
```

## Requisitos

- JDK 8 ou superior (o código utiliza `LocalDate`, `Stream`, `BigDecimal` e lambdas)

## Como executar

### Pelo terminal

```bash
javac -d out src/main/java/model/*.java src/main/java/Principal.java
java -cp out Principal
```

### Pelo IntelliJ IDEA

1. Abra o projeto no IntelliJ IDEA
2. Execute a classe `Principal`

## Saída esperada

A execução imprime seções correspondentes a cada funcionalidade do exercício:

```
3.3 - LISTA INICIAL DE FUNCIONÁRIOS
... (funcionários)
após remover 'João'
3.4 - APÓS AUMENTO DE 10%
3.6 - FUNCIONÁRIOS AGRUPADOS POR FUNÇÃO
3.8 - ANIVERSARIANTES DOS MESES 10 E 12
3.9 - FUNCIONÁRIO COM MAIOR IDADE
3.10 - FUNCIONÁRIOS EM ORDEM ALFABÉTICA
3.11 - TOTAL DOS SALÁRIOS
3.12 - QUANTIDADE DE SALÁRIOS MÍNIMOS POR FUNCIONÁRIO
```
