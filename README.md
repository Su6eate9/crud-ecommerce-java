# crud-ecommerce-java

#Desenvolvimento de aplicação e-commerce com interface gráfica utilizando java.swing, efetuando cadastro atualização, listagem e exclusão de registros. Aplicando os conceitos deherança e polimorfismo.

1. Estrutura do Projeto:

- Pacote models: Contém as classes de domínio (Produto e ProdutoEletronico)
- Pacote dao: Contém a classe ProdutoDAO para gerenciar os dados
- Pacote gui: Contém a interface gráfica (MainFrame)

2. Herança e Polimorfismo:

- A classe ProdutoEletronico herda de Produto
- O polimorfismo é usado ao tratar produtos eletrônicos como produtos comuns na lista
- Campos específicos são mostrados/ocultados dependendo do tipo de produto

3. Uso de Collections:

- ArrayList é usado no ProdutoDAO para armazenar os produtos
- Os produtos são gerenciados em memória usando uma List
- Operações de CRUD (Create, Read, Update, Delete) são implementadas usando métodos de Collections

4. Interface Gráfica (Swing):

- Layout responsivo usando GridBagLayout
- Formulário para entrada de dados
- Tabela para visualização dos produtos
- Botões para operações CRUD
- Campos dinâmicos que aparecem/desaparecem conforme o tipo de produto

5. Funcionalidades:

- Cadastro de produtos comuns e eletrônicos
- Edição de produtos existentes
- Exclusão de produtos
- Listagem em formato de tabela
- Validação de campos numéricos
- Feedback ao usuário através de mensagens

6. Recursos de Usabilidade:

- Limpeza automática do formulário após operações
- Seleção de produtos na tabela para edição
- Mensagens de confirmação e erro
- Interface intuitiva e organizada

Para executar o programa:

- Compile todas as classes mantendo a estrutura de pacotes: javac src/models/_.java src/dao/_.java src/gui/\*.java -d bin
- Execute a classe MainFrame que contém o método main: java -cp bin gui.MainFrame
- A interface gráfica será exibida e você poderá começar a gerenciar os produtos
