## Aplicação dos Padrões de Projeto

### Visitor

No contexto do projeto Calorie Service, o padrão Visitor foi adotado para promover uma abordagem flexível e extensível no processamento de operações sobre os registros de calorias. Inicialmente, definimos a interface `CalorieVisitor`, que declara métodos de visita para cada tipo de elemento que pode ser visitado. Em seguida, implementamos visitantes concretos, como o `DailyCalorieVisitor`, encapsulando lógicas específicas, como o cálculo das calorias diárias com base em diferentes estratégias de ganho ou perda de peso. Na classe `CalorieRecord`, foi adicionado o método `accept`, permitindo que os objetos `CalorieRecord` aceitem visitantes e deleguem a eles a execução das operações adequadas. Por fim, no serviço `CalorieServiceImpl`, integramos o visitante, realizando operações nos registros de calorias conforme necessário, mantendo a separação de responsabilidades e facilitando futuras extensões ou modificações no sistema. Essa abordagem promove uma maior coesão e extensibilidade no código, mantendo a simplicidade e clareza na estruturação do projeto.

### Observer

O padrão Observer foi implementado no projeto para permitir que componentes interessados recebam notificações sobre mudanças nos registros de calorias. Nesta implementação, o controlador atua como o observador, sendo registrado no serviço `CalorieServiceImpl`, responsável por manipular os registros de calorias e notificar os observadores quando ocorrem mudanças relevantes. Isso facilita uma comunicação dinâmica entre os componentes, permitindo que o controlador responda adequadamente às atualizações nos registros de calorias.

### Strategy

No contexto do projeto, o padrão Strategy foi aplicado para permitir a seleção dinâmica de uma estratégia de cálculo de calorias diárias com base no objetivo do usuário, como "engordar" ou "emagrecer". As estratégias de cálculo são encapsuladas em classes concretas que implementam a interface `CalorieStrategy`, como `GainCaloriesStrategy` e `LoseCaloriesStrategy`. No serviço `CalorieServiceImpl`, a estratégia apropriada é selecionada com base no objetivo do usuário ao salvar um novo registro de calorias. Essa abordagem promove uma fácil extensibilidade do sistema e uma melhor organização do código, mantendo a coesão e a clareza na estruturação do projeto.
