	CREATE TABLE `caixa` (
	  `id_caixa` int NOT NULL AUTO_INCREMENT,
	  `valor_inicial_caixa` decimal(20,2) NOT NULL,
	  `valor_final_caixa` decimal(20,2) NOT NULL,
	  `datahora_abertura_caixa` date NOT NULL,
	  `datahora_fechamento_caixa` date DEFAULT NULL,
	  `situacao` tinyint(1) NOT NULL,
	  PRIMARY KEY (`id_caixa`)
	) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
create database mustache;
use mustache;

create table Cargo ( 
	id_cargo int not null auto_increment primary key,
	nome_cargo varchar(100) not null,
    perm_pag_contas boolean,
    perm_rec_contas boolean,
    perm_cad_marca boolean,
    perm_comprar boolean,
    perm_depositar boolean,
    perm_abrir_caixa boolean,
    perm_sangria boolean,
    perm_cad_recebimento boolean,
    perm_cad_pessoa boolean,
    perm_cad_forn boolean,
    perm_cad_prod boolean,
    perm_receber boolean,
    perm_cad_cargo boolean,
    perm_vender boolean,
    perm_cad_serv  boolean   
);

CREATE TABLE Sexo (
    id_sexo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome_sexo VARCHAR(20)
);


CREATE TABLE Pessoa (
	id_pessoa int not null auto_increment primary key, 
    id_sexo int not null,
    nome_pessoa varchar(100) not null, 
    data_nascimento_pessoa date not null,
    cpf_pessoa varchar(14),
    telefone_pessoa varchar(20),
    e_cliente_pessoa boolean,
    e_funcionario_pessoa boolean,
	foreign key (id_sexo) references Sexo (id_sexo)
);

CREATE TABLE Cliente (
	id_cliente int not null auto_increment primary key,
    id_pessoa int not null,
	foreign key (id_pessoa) references Pessoa (id_pessoa)
);

/*
SELECT 
  Tabela1.Campo1,
  Tabela1.Campo2,
  Tabela2.Campo1,
  Tabela2.Campo2,
  ...
FROM Tabela1
LEFT JOIN Tabela2 ON Tabela2.ChaveEstrangeira = Tabela1.ChavePrimaria
WHERE Tabela1.Campo2 LIKE '%A%'
ORDER BY Tabela2.Campo2*/

/*Usamos left: 
Ao usar left retornamos todas as pessoas (Mais precisamente todos os nomes de pessoas)
e, caso seja um funcionário, mostre além do nome da pessoa, o nome da mãe desse funcionário

Ao usar apenas join: Retornamos apenas as linhas da tabela pessoa que contém precisamente
as filtrações colocadas no 'on'. Pegue o nome da pessoa e o nome da mãe do funcionário caso exista na tabela de funcionário, um id de pessoa que existe ao mesmo tempo 
na tabela de pessoa
*/
/*select
  pessoa.nome_pessoa,
  funcionario.nome_mae_funcionario
from pessoa
 left join funcionario on funcionario.id_pessoa = pessoa.id_pessoa*/




insert into Sexo values (null, "Masculino");
insert into Sexo values (null, "Feminino");

insert into pessoa values (null, 1, "Amadeu Alves Souza", str_to_date("17/06/2020", '%d/%m/%Y') , "651465156", "69992658745", false, true);

insert into cargo values (null, "Gerente", true, true, true, true, true, true, true, true, true);


create table Funcionario (
	id_funcionario int not null auto_increment primary key,
    id_pessoa int not null,
	id_cargo_funcionario int not null,
    salario_funcionario decimal(10,2) not null,
    estado_civil_funcionario varchar(40) not null,
    grau_instrucao_funcionario varchar(100) not null,
    carteira_de_trabalho_funcionario varchar(100) not null,
    nome_mae_funcionario varchar(150) not null,
    nome_pai_funcionario varchar(150) not null,
    email_funcionario varchar(50) not null,
	foreign key (id_pessoa) references Pessoa (id_pessoa),
    foreign key (id_cargo_funcionario) references Cargo(id_cargo)
);

insert into Funcionario values (null, 1, 1, 2000, "Solteiro", "Senior", "abs25638", "Alberta Alves", "Adalberto Souza", "felipe@gmail.com");

CREATE TABLE Fornecedor (
	id_fornecedor int not null auto_increment primary key,
    razao_social_fornecedor varchar(200) not null,
    nome_fantasia_fornecedor varchar(200) not null,
    telefone varchar(30) not null,
    cnpj varchar(30) not null,
    email varchar(100) not null
);


CREATE TABLE Endereco (
	id_endereco int not null auto_increment primary key,
    id_pessoa_endereco int,
    id_fornecedor_endereco int,
    cep varchar(100) not null,
    rua varchar(300) not null,
    numero int not null,
    bairro varchar(300) not null,
    estado varchar(300) not null,
    cidade varchar(300) not null,
    complemento varchar(300),
    FOREIGN KEY (id_pessoa_endereco) REFERENCES Pessoa (id_pessoa),
    FOREIGN KEY (id_fornecedor_endereco) REFERENCES Fornecedor (id_fornecedor)
);

select * from pessoa;
create table Usuario (
	id_usuario int not null auto_increment primary key,
	id_funcionario_usuario int not null,
	nome_usuario varchar(50) not null,
	senha_usuario varchar(300) not null,
	foreign key (id_funcionario_usuario) references Funcionario (id_funcionario)
);


insert into usuario values (null, 1, "usuarioifro", "03a613e2c98773763f2658b5aff2e03d");



CREATE TABLE Marca (
	id_marca int not null auto_increment primary key,
    nome_marca varchar(100) not null
);



CREATE TABLE Produto (
	id_produto int not null auto_increment primary key,
    id_marca int not null,
	nome_produto varchar(60) not null,
    preco_produto decimal(10,2) not null,
    qtd_estoque int not null,
    foreign key (id_marca) references Marca (id_marca)
);


CREATE TABLE Servico (
	id_servico int not null auto_increment primary key,
    nome_servico varchar(50) not null,
    descricao_servico varchar(255) not null,
    duracao_servico int not null,
    preco_servico float not null
);

CREATE TABLE Caixa (
	id_caixa int not null auto_increment primary key,
    valor_inicial_caixa decimal(20,2) not null,
    valor_final_caixa decimal(20,2) not null,
    data_caixa date not null
);


/*Uma compra tem vários produtos e um produto pode estar relacionado a mais de uma compra*/
CREATE TABLE Compra (
	id_compra int not null auto_increment primary key,
    #Tem apenas 1 fornecedor
    id_fornecedor_compra int not null,
    forma_pagamento_compra varchar(100) not null,
	valor_compra decimal(10,2) not null,
    data_emissao_compra date not null,
    #se quantidade de parcelas for 1, então é a vista
    qtd_parcelas_compra int not null,
    foreign key (id_fornecedor_compra) references Fornecedor (id_fornecedor)
);

/*É uma conta que o estabelecimento precisa pagar, se a conta a pagar for avista, o atributo QUITADA*/
/*No caso de ser pagamento de salário, o id de funcionário será colocado*/
CREATE TABLE Conta_Pagar (
	id_conta_pagar int not null auto_increment primary key,
    id_compra_conta_pagar int, 
    id_funcionario_conta_pagar int,
    descricao_conta_pagar varchar(200) not null,
	valor_pago_conta_pagar decimal(10,2) not null,
    valor_total_conta_pagar decimal(10,2) not null,
    data_emissao_conta_pagar date not null,
    quitada_conta_pagar boolean not null,
    #tipo_conta_pagar varchar(100) not null,
    foreign key (id_compra_conta_pagar) references Compra (id_compra),
    foreign key (id_funcionario_conta_pagar) references Funcionario (id_funcionario)
);

/*Seleciona uma conta a pagar e clica em realizar pagamento, coloca o valor do pagamento e pronto, é retirado do valor restante da conta a pagar*/
CREATE TABLE Pagamento (
	id_pagamento int not null auto_increment primary key,
    id_conta_pagar int not null,
	id_caixa_pagamento int not null,
	valor_pagamento decimal(14,2),
    data_pagamento date not null,
    foreign key (id_caixa_pagamento) references Caixa (id_caixa),
    foreign key (id_conta_pagar) references Conta_Pagar (id_conta_pagar)
);

CREATE TABLE Compra_Produto (
	id_compra_produto int not null auto_increment primary key,
    id_compra_cp int not null,
    id_produto_cp int not null,
    foreign key (id_compra_cp) references Compra (id_compra),
    foreign key (id_produto_cp) references Produto (id_produto)
);


# ---------------------------
#Rever isso por causa de uma venda de produto
CREATE TABLE Venda (
	id_venda int not null auto_increment primary key, 
    id_cliente_venda int not null,
    #id funcionario que fez a venda
    id_funcionario_venda int not null,
    #Se for 1 é avista
    quantidade_parcelas int not null,
    forma_pagamento varchar(30) not null,
    #Soma de todos os produtos e serviços
    valor_venda decimal(10,2) not null,
    data_venda date not null,
    foreign key (id_cliente_venda) references Cliente(id_cliente),
    foreign key (id_funcionario_venda) references Funcionario (id_funcionario)
);

CREATE TABLE Venda_Produto (
	id_venda_produto_vp int not null auto_increment primary key,
    id_venda_vp int not null,
    id_produto_vp int not null,
    foreign key (id_venda_vp) references Venda (id_venda),
    foreign key (id_produto_vp) references Produto (id_produto)
);

CREATE TABLE Venda_Servico (
	id_venda_servico_vs int not null auto_increment primary key,
    id_venda_vs int not null, 
    id_servico_vs int not null,
    id_funcionario_vs int not null,
    foreign key (id_venda_vs) references Venda (id_venda),
    foreign key  (id_servico_vs) references Servico (id_servico),
    foreign key (id_funcionario_vs) references Funcionario (id_funcionario)
);
select * from venda order by id_venda desc limit 1;

insert into marca values (null, "Avon");
insert into produto values (null, 1, "Condicionador Avonex", 23.99, 100);
select * from produto;
/*Conta receber
- Toda venda gera uma conta a receber, porém se a venda for avista, a conta receber é criada passando 
a data de validade com a mesma data de emissao, além de que o atributo QUITADA vai ser TRUE
- Se o id do cliente e o id da venda for NULO, então 
a conta a receber não é de uma venda, é apenas de algum outro local que o proprietário do 
estabelecimento vai receber dinheiro. Se o proprietário apenas vai inserir 1000 reais no caixa da empresa, a conta a receber
vai ter valor de 1000 e a data de validade vai ser a mesma data da emissão, além de que o atributo QUITADA vai ser TRUE
*/
select * from funcionario;
#Provavelmente teremos que tirar id_cliente_conta_receber, pois já tem na venda ->Mas pode ser um recebimento sem ser venda
#Rever se vai receber o id da venda produto, pois venda tem id de servicos, ou então, fazer uma tabela venda_servicos e outra venda_produto
CREATE TABLE Conta_Receber (
	id_conta_receber int not null auto_increment primary key,
    #Se a conta receber não for relacionada com venda
    id_cliente_conta_receber int,
    # Se a conta receber for relacionada com venda
	id_venda_conta_receber int, 
	data_emissao_conta_receber date not null,
	data_vencimento_conta_receber date not null,
    descricao_conta_receber varchar(200) not null,
    #tipo_conta_receber varchar(100) not null, 
	valor_conta_receber decimal (10,2) not null,
	valor_recebido_conta_receber decimal (10,2) not null,
	quitada_conta_receber boolean not null,
    foreign key (id_cliente_conta_receber) references Cliente (id_cliente),
    foreign key (id_venda_conta_receber) references Venda (id_venda)
);
#Se a venda for avista, já criamos a venda, criamos uma conta receber e depois já fazemos um recebimento com o valor total da venda, quitando ela
#Caso a venda tenha mais de uma parcela, criamos a conta receber com valor recebido sendo igual a 0, cabendo ao usuário adicionar o valor recebido na conta receber

# Seleciona conta a receber e clica em realizar recebimento, coloca o valores do recebimento e desconta do valor da conta a receber
# forma_recebimento varchar(100) not null,
CREATE TABLE Recebimento (
	id_recebimento int not null auto_increment primary key,
    id_conta_receber int not null,
    id_caixa_recebimento int not null,
    data_recebimento date not null,
    #Valor que será recebido
    valor_total_recebimento decimal not null,
    foreign key (id_conta_receber) references Conta_Receber(id_conta_receber),
    foreign key (id_caixa_recebimento) references Caixa (id_caixa)
);

select * from venda_produto;
insert into servico values (null, "Pintura de cabelo", "Pintura com cor escolhida e lavagem", 40, 60);

select * from pessoa limit 200;


#SELECT id_usuario, id_funcionario_usuario, nome_usuario from usuario WHERE nome_usuario = "name" AND senha_usuario = md5("senhatop");


#SELECT Carro.cod_carro, Carro.modelo_carro, Carro.ano_carro, Carro.marca_carro,
#Cliente.nome_cli FROM Cliente, Carro  
#WHERE (Carro.cod_cliente = Cliente.cod_cli);

#SELECT Usuario.id_usuario, Usuario.id_funcionario_usuario, Usuario.nome_usuario, Pessoa.nome_pessoa FROM Usuario, Funcionario, Pessoa WHERE (Usuario.nome_usuario LIKE "%usuario%" AND Usuario.id_funcionario_usuario = Funcionario.id_funcionario AND Funcionario.id_pessoa = Pessoa.id_pessoa) ORDER BY Usuario.nome_usuario;


/*Pegue todos os dados de pessoa, o id do cliente e nome do sexo quando existir um id dessa pessoa em um registro na tabela cliente e quando existir um id de sexo dessa pessoa 
na tabela sexo
select pessoa.*, cliente.id_cliente,
sexo.nome_sexo 
FROM Pessoa JOIN Cliente, Sexo WHERE cliente.id_pessoa = pessoa.id_pessoa AND pessoa.id_sexo = sexo.id_sexo; */


-- insert into pessoa values (null, 1, "Henrique Josias", str_to_date("17/06/2020", '%d/%m/%Y'), "06847859874", "69991547896", true, true);
-- insert into cliente values (null, 1);
-- select * from pessoa;
/*Primeiro pega as pessoas que tem o id na tabela cliente (Ou seja, que são clientes), e depois filtra aquela que contém o cpf igual ao requerido
select pessoa.id_pessoa FROM pessoa JOIN cliente where cliente.id_pessoa = pessoa.id_pessoa AND pessoa.cpf_pessoa = 06847859874;*/
select * from pessoa;
insert into cargo values (null, "Vendedor", 1,1,1,1,1,1,1,1,1);

select * from venda order by id_venda desc limit 100;

select * from conta_receber;

CREATE TABLE `agendamento` (
  `id_agendamento` int NOT NULL AUTO_INCREMENT,
  `id_servico` int NOT NULL,
  `data_agendamento` date NOT NULL,
  `horario_inicio_agendamento` time NOT NULL,
  `horario_termino_agendamento` time NOT NULL,
  `id_funcionario` int NOT NULL,
  `id_cliente` int NOT NULL,
  PRIMARY KEY (`id_agendamento`),
  KEY `id_servico` (`id_servico`),
  KEY `id_funcionario` (`id_funcionario`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `agendamento_ibfk_1` FOREIGN KEY (`id_servico`) REFERENCES `servico` (`id_servico`),
  CONSTRAINT `agendamento_ibfk_2` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionario` (`id_funcionario`),
  CONSTRAINT `agendamento_ibfk_3` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




