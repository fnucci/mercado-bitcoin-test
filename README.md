# mercado-bitcoin-test
Demo para @MB

------------Arquitetura-------------

A arquitetura dessa demo seguiu o padrão de micro-serviços REST, dada a baixa
complexidade exigida na proposta do projeto, que não justificava o uso da 
Clean Architecture. Utilizei o padrão Model-View-Controller (MVC) separando as 
camadas de visualização, orquestradores e regras de negócio da aplicação, alem
disso, apliquei os 5 principios do SOLID para proporcionar um código mais inde
pendente, com responsabilidades bem definicas, fácil de expandir e de manter.
Implementei também mensagens de erro customizadas, bem mais amigáveis que um 
stacktrace da JVM, alem de logs para facilitar no Troubleshooting.

De tecnologias, usamos algumas, são elas:

Java 17
Spring Framework (boot, web, jpa).
Lombok
Jakarta validations
H2 database
Docker
Maven
Github

Explicando o projeto:

Junto do código fonte há 2 arquivos importantes, o Dockerfile, necessário para o
build e run do projeto em container e uma postman collection, fornecida para agi
lizar os testes funcionais.

Há 2 maneiras de rodar o projeto, são elas:

1 - Build e run do projeto via Dockerfile
Se estiver usando o Windows, instale o wsl for windows digitando o comando
"wsl --install" na sua prompt de comando.
Importante: O comando acima irá requerer o nome de uma distribuição do Linux,
neste ambiente, utilizei a distribuição Ubuntu. Caso deseje outras distribuições
utilize o comando "wsl --list --online" para verificar todas as distribuições
disponíveis.

Após instalado, faça o download e instalação do Docker Desktop em:
"https://docs.docker.com/desktop/setup/install/windows-install/"

Com ambos os softwares instalados, navegue pelo prompt de comando até o diretório
raiz do projeto, onde há um arquivo chamado "Dockerfile" e execute o comando:

"docker build t mbtest/mercadobitcointest:1.0 ."

Esse comando fará o download das imagens da jdk / jre descritos no Dockerfile,
assim como as demais dependencias que serão necesárias para a construção do pro
jeto.
Importante: Esse processo pode demorar um tempo devido ao tempo de download das
imagens do dockerhub e de resolução das dependencias do Maven.

Agora, com tudo baixado e todas as layers executadas, é hora de efetetivar a su
bida do projeto, execute o comando a seguir para conseguir testa-lo no navegador, no
postman, ou em qualquer outra ferramenta de mesma finalidade (curl, Inmsonia,
Bruno).

"docker run -p 8080:8080 mbtest/mercadobitcointest:1.0 ."

2- Build e run via IDE Java (Eclipse, InteliJ, VSCode, etc...)
Em sua ide favorita, faça um clone do projeto pelo Github na url a seguir:

"https://github.com/fnucci/mercado-bitcoin-test.git"

Depois, aguarde o maven instalar todas as suas dependencias, caso o Maven não
inicie automaticamente, abra o terminal da sua IDE ou mesmo o prompt de comando
e altere o diretório para o diretório onde voce baixou o projeto e digite o co
mando:

"mvn -U clean install"

Se as dependencias nao forem baixadas, force usando o comando:

"mvn dependency::resolve"

Após resolver todas as dependencias, é só apertar o botão "RUN" da sua IDE.

Assim que a aplicação rodar totalmente, você verá a mensagem na consolde de que
o Toncat subiu e está no ar, agora, utilize a colçao fornecida nesse projeto
chamar os endpoints.

Requisições:

Para contas:

GET - Recupera a conta fornecida no Request Param.
POST - Cria um conta nova, todos os atributos do Request Body são obrigatórios
e são validados nas Use Cases.
PUT - Há duas requisições de HTTP PUT, uma responsável por creditar saldo a uma
conta e outra debitar o saldo, tomei o cuidado de não permitir que o crédito e o
débito do saldo da conta fossem manuseados diretamente. Há validações de valores
nestas requisições HTTP, são aceitos apenas valores maiores que 0 (zero), outro
ponto de atenção, para a função de débito, obrigatoriamente a conta deve ter
saldo para que a operação seja efetivada.

Para Ativos:

GET - Recupera o ativo fornecido no Request Param.
POST - Cria um novo ativo, todos os atributos do Request Body são obrigatórios
e são validados nas Use Cases. É necessário possuir uma conta já criada para
vincular este ativo àquela conta informada no "Request Body".
PUT - É permitido alterar as propriedades de nome do ativo e valores neste mó
dulo, não sendo possível alterar seu "owner", "dono", a conta a qual esse ativo
é vinculada.

Para Livros de ordens:

GET - Busca todos os ativos disponíveis para compra. Esses ativos seguem a se
guinte regra:
Um ativo é disponível para compra se está vinculado a uma conta diferente da
conta a qual queremos comprar o ativo.
eg. Conta A visualiza todos os ativos vinculados a Conta B, Conta C, etc...
Um ativo só está disponível para compra se e somente seu atributo de flag
"isDisponivelVenda" for igual a "True", caso esse atributo seja "False" esse
ativo não é visualizado na listagem.

PUT - Aqui é o lugar em que toda a lógica explicada no requisito é executada,
é neste "endpoint" que ocorrem:
Débito do valor do ativo da conta do comprador.
Crédito do valor do ativo na conta do vendedor.
Transferência do ativo entre as duas contas.
Atualização dos dois saldos, o Saldo geral da Conta, e o Saldo de Ativos da conta.

Importante:
O Saldo geral da conta pode ser manipulado pelos PUTs de crédito e débito.
O Saldo de ativos da conta é atualizado toda vez que um ativo é criado, sendo
vinculado a conta.
Ou quando o valor desse ativo é atualizado no PUT de ativos.
Ou ainda, quando uma transferencia de ativos é realizada no livro de ordens.

Sugestão de teste:

"Em Contas", crie ao menos duas contas, não foi implementada nenhuma validação
de CPF, digite qualquer coisa que o campo aceita.
Credite um saldo nessas contas.
Debite um saldo dessas contas.
Utilize o Get entre cada uma dessas interações para visualizar as diferenças.
Se quiser, insira valores nulos, negativos ou inválidos para visualizar as men-
sagens de erro que preparei para a API.

"Em Ativos", assim como em Contas, crie ao menos 1 ativo em cada conta,
brinque com o campo "isDisponivelVenda" para visualizar melhor a lógica usada
na inserção do ativo no "Livro de Ordens", atualize os campos de valores e nome
dos ativos e vincule-os as contas.

"Em Livro", no "Request Param" forneça o id de uma das contas criadas, você
verá que os ativos que são dessa conta não serão mostrados, troque de "contaId"
e veja os resultados.
Em comprar ativo, se atente ao "Request Param" ele é o id da conta compradora.
No "Request Body" há apenas um atributo, chamado ativoId, nele deve ser forneci
do o id do ativo que quer efetivar a transferencia, ao término da operação, uma
Response será enviada ao usuário contendo a Conta do Comprador atualizada com
o saldo, saldo de ativos e lista de ativos.

Possivelmente a aplicação tenha algum bug que não foi tratado por mim, faça uns
bons testes e depois me contem, porém, fiz tudo em aproximadamente 16 horas,
pois tive uma semana corrida, espero que gostem.