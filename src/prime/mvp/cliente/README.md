# Módulo Cliente - MVP

Esta pasta contém um piloto de refatoração do módulo Cliente usando o padrão MVP.

## Estrutura
- model: representações do domínio
- view: interface e contrato da tela
- presenter: coordenador entre view e service
- service: regras de negócio
- repository: acesso a dados

## Objetivo
Separar responsabilidades para que a tela não manipule diretamente regras e persistência.
