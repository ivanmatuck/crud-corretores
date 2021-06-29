hdi-seguros

Para o processo de geração de uma nova oferta de seguros, é necessário verificar se o corretor
existe e se ele está ativo. Ao gerar uma nova oferta o corretor informa o número do documento.
Atualmente temos um serviço que faz a busca de corretor por documento, e retorna todos os
seus dados pessoais e um segundo serviço que retorna os dados da situação cadastral do
corretor. Precisamos construir uma única API que faça a busca desses dados e faça a validação
da situação do corretor (ativo ou inativo)

Atividades a serem implementadas:

Criar uma nova API que:
1. Criar serviço que valide se ele está ativo;
2. Cria um serviço que atualize o status do corretor (opcional).

Resposta da API
Caso o corretor esteja ativo, retornar os seguintes dados:
• nome
• documento
• código
• data de criação
• Taxa de comissão
• Flag ativo
Caso não esteja ativo, retornar uma mensagem de erro de negócio

Iniciar a aplicação localmente.
http://localhost:8080/broker/comstatus/

Exemplos para teste:

Get corretor com status ativo:
http://localhost:8080/broker/comstatus/11725034000

Get corretor com status inativo:
http://localhost:8080/broker/comstatus/31784583014

Get corretor não cadastrado:
http://localhost:8080/broker/comstatus/11725034000888