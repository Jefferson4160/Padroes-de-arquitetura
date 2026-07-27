# Módulo Fornecedor - MVP

Este módulo implementa uma primeira versão do padrão MVP para o domínio de Fornecedor dentro do sistema comercial.

## Objetivo

Separar responsabilidades em:
- Model: representa os dados do fornecedor
- View: exibe e captura informações do usuário
- Presenter: orquestra a interação entre view e serviço
- Service: aplica regras básicas de negócio
- Repository: encapsula o armazenamento em memória

## Estrutura

- model/FornecedorModel.java
- repository/FornecedorRepository.java
- repository/InMemoryFornecedorRepository.java
- service/FornecedorService.java
- presenter/FornecedorPresenter.java
- view/FornecedorViewContract.java
- view/FornecedorSwingView.java
- view/FornecedorConsoleView.java

## Funcionalidades atuais

- Listagem de fornecedores
- Cadastro e edição simples
- Remoção de fornecedores
- Interface Swing e validação em console

## Como executar

A aplicação principal foi ajustada para iniciar o fluxo MVP do módulo Fornecedor.

### Execução em modo headless

```bash
javac -d out (Get-ChildItem -Path src\prime\mvp -Recurse -Filter *.java | ForEach-Object { $_.FullName })
javac -cp out -d out src\prime\Main.java
java "-Djava.awt.headless=true" -cp out prime.Main
```

## Observação

Esta é uma implementação piloto do padrão MVP, criada para demonstrar a refatoração de um módulo real do sistema de forma incremental.
