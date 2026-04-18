# Teste Prático - Projedata

Solução desenvolvida para o teste prático de programação da empresa **Projedata**, como parte do processo seletivo para a vaga de Desenvolvedor Java Júnior.

## Sobre o projeto

O projeto gerencia uma lista de funcionários de uma indústria, aplicando conceitos de Orientação a Objetos e estruturas de dados em Java. Os dados dos funcionários são armazenados em um banco de dados SQLite e carregados automaticamente ao executar o programa.

## Funcionalidades

- Listagem de todos os funcionários com data e salário formatados (padrão brasileiro)
- Remoção de funcionário específico
- Aplicação de aumento salarial de 10%
- Agrupamento de funcionários por função
- Filtro de aniversariantes por mês
- Identificação do funcionário mais velho
- Ordenação alfabética
- Cálculo do total de salários
- Cálculo de quantos salários mínimos cada funcionário recebe

## Tecnologias utilizadas

- Java
- SQLite (via sqlite-jdbc)

## Estrutura do projeto

```
Projeto-projeData-java/
├── lib/
│   └── sqlite-jdbc.jar
├── src/
│   ├── Pessoa.java          # Classe base com nome e data de nascimento
│   ├── Funcionario.java     # Herda de Pessoa, adiciona salário e função
│   ├── DatabaseHelper.java  # Cria o banco, a tabela e busca os dados
│   └── Principal.java       # Executa todas as funcionalidades do teste
└── funcionarios.db          # Gerado automaticamente na primeira execução
```

## Como executar

**1. Compilar:**
```bash
javac -cp lib/sqlite-jdbc.jar src/*.java -d out
```

**2. Executar:**
```bash
java -cp out:lib/sqlite-jdbc.jar Principal
```

> O banco de dados `funcionarios.db` é criado automaticamente na primeira execução com todos os funcionários já inseridos.

## Conceitos aplicados

- Herança e encapsulamento (Orientação a Objetos)
- `ArrayList` e `HashMap` para coleções
- `BigDecimal` para cálculos monetários precisos
- `LocalDate` e `DateTimeFormatter` para manipulação de datas
- `Collections.sort()` para ordenação
- JDBC com SQLite para persistência de dados
