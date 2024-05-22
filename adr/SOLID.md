# Aplicação dos Princípios SOLID

## Resumo
Este documento descreve como os princípios SOLID estão sendo aplicados no projeto de Calorie Service.

## Single Responsibility Principle (Princípio da Responsabilidade Única)
O projeto segue o princípio da responsabilidade única, garantindo que cada classe e componente tenha uma única razão para mudar. Por exemplo:
- A classe `CalorieService` é responsável por calcular as calorias diárias, salvar registros de calorias e fornecer acesso aos registros de calorias. Ela mantém o foco em uma única responsabilidade relacionada ao gerenciamento de calorias.

## Open/Closed Principle (Princípio Aberto/Fechado)
O projeto segue o princípio aberto/fechado, garantindo que as entidades de software estejam abertas para extensão, mas fechadas para modificação. Por exemplo:
- O `CalorieService` está aberto para extensão, permitindo a adição de novas estratégias de cálculo de calorias sem modificar o código existente. As estratégias de cálculo (`CalorieStrategy`) podem ser estendidas sem afetar o serviço principal.

## Liskov Substitution Principle (Princípio da Substituição de Liskov)
O projeto segue o princípio da substituição de Liskov, garantindo que os objetos de um programa sejam substituíveis por instâncias de seus subtipos sem alterar a correção do programa. Por exemplo:
- Os objetos `CalorieStrategy` (e suas implementações) podem ser substituídos uns pelos outros sem afetar o comportamento do `CalorieService`. Novas estratégias de cálculo podem ser introduzidas sem alterar o serviço existente.

## Interface Segregation Principle (Princípio da Segregação de Interfaces)
O projeto segue o princípio da segregação de interfaces, garantindo que uma classe não seja forçada a depender de interfaces que ela não usa. Por exemplo:
- O `CalorieService` depende apenas da interface `CalorieStrategy`, que define o contrato para as estratégias de cálculo de calorias. Ele não depende de métodos desnecessários ou não utilizados.

## Dependency Inversion Principle (Princípio da Inversão de Dependência)
O projeto segue o princípio da inversão de dependência, garantindo que as classes de alto nível não dependam das classes de baixo nível. Por exemplo:
- O `CalorieService` depende apenas da interface `CalorieStrategy`, uma abstração, e não das implementações concretas `GainCaloriesStrategy` e `LoseCaloriesStrategy`. Isso permite que diferentes estratégias sejam injetadas sem modificar o código do serviço.

