# Refatoração MVP do módulo Cliente

## Objetivo
Este módulo foi refatorado para demonstrar o padrão MVP em uma parte do sistema legado, separando responsabilidades de interface, lógica de negócio e acesso a dados.

## O que foi ajustado
- A tela foi transformada em uma interface mais próxima de um CRUD funcional.
- Agora a view exibe campos para cadastro e uma tabela para listar clientes.
- O presenter passou a coordenar as ações entre a tela e a lógica de negócio.
- O service encapsula as regras de negócio para salvar, listar e remover clientes.
- O repository abstrai o armazenamento, inicialmente com uma implementação em memória.

## Estrutura atual
- Model: ClienteModel
  - representa os dados do cliente.
- View: ClienteSwingView e ClienteConsoleView
  - trata a interação com o usuário.
- Presenter: ClientePresenter
  - controla o fluxo da tela.
- Service: ClienteService
  - concentra a lógica de negócio.
- Repository: InMemoryClienteRepository
  - fornece os dados usados pelo fluxo.

## Fluxo de uso
1. A tela carrega os clientes existentes.
2. O usuário pode selecionar um cliente na tabela.
3. O formulário é preenchido automaticamente com os dados do cliente selecionado.
4. O usuário pode clicar em Novo para limpar o formulário.
5. O usuário pode salvar ou remover registros.

## Benefícios observados
- menor acoplamento entre interface e regras de negócio;
- organização mais clara do código;
- base preparada para evolução futura;
- facilidade para expandir o padrão para outros módulos.
