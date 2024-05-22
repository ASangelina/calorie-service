# ADR: Escolha da Arquitetura

## Contexto
Precisamos escolher uma arquitetura para o nosso microserviço que calcula a quantidade de calorias a serem consumidas diariamente com base no objetivo da pessoa (engordar ou emagrecer).

## Decisão
Optamos por usar a arquitetura Layered (Camadas) devido à sua simplicidade, clareza e boa separação de responsabilidades. Isso facilitará a manutenção e a escalabilidade do serviço.

## Consequências
- **Positivas**:
    - Fácil de entender e manter.
    - Boa separação de responsabilidades.
    - Adequada para um serviço de pequena a média complexidade.
- **Negativas**:
    - Pode se tornar complexa se o serviço crescer muito e adicionar muitas camadas ou responsabilidades.
