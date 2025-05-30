# 🐝 BeeStore - E-commerce Simples (Em Desenvolvimento)

BeeStore é um projeto de e-commerce simples desenvolvido com **Java + Spring Boot**, voltado para fins educacionais e práticos. O objetivo é aplicar os conceitos de API REST, autenticação com Spring Security, persistência com JPA e boas práticas de arquitetura (MVC).

---

## 🚧 Status do Projeto

🔨 Em desenvolvimento  
Ainda não está funcional, mas a estrutura está sendo construída e organizada.

---

## 🎯 Objetivo do Projeto

- Criar um sistema onde clientes possam visualizar produtos, adicionar ao carrinho e finalizar compras.
- Administradores podem gerenciar produtos e pedidos.
- Simular um fluxo real de e-commerce com autenticação, histórico de pedidos e painel de controle básico.

---

## 📚 Funcionalidades Planejadas

### 👤 Cliente:
- Cadastro e login
- Listagem de produtos
- Adição ao carrinho
- Finalizar compra (simulação)
- Acompanhar pedidos

### 🛠️ Administrador:
- Cadastro, edição e exclusão de produtos
- Listagem de pedidos
- Atualização de status do pedido

---

## 🧪 Tecnologias Planejadas

- **Java 21**
- **Spring Boot**
- **Spring Data JPA (Hibernate)**
- **Spring Security (com BCrypt)**
- **PostgreSQL**
- **Lombok**
- **Swagger (documentação)**
- **Maven**
-  Front-end com React

---

## 🗃️ Estrutura Inicial de Pastas (prevista)

\`\`\`
src/main/java/com/seuusuario/beestore
├── controller/
├── service/
├── repository/
├── model/
├── dto/
├── config/
└── BeeStoreApplication.java
\`\`\`

---

## 🔒 Segurança

- As senhas serão criptografadas com BCrypt
- A autenticação será feita com Spring Security
- (opcional) JWT poderá ser adicionado futuramente

---

## 📝 Requisitos do Sistema

### Requisitos Funcionais:
- RF01 - Cadastro de clientes
- RF02 - Login/autenticação
- RF03 - Listagem de produtos
- RF04 - Adição ao carrinho
- RF05 - Finalização de pedidos
- RF06 - Simulação de pagamento
- RF07 - Histórico de pedidos
- RF08 - CRUD de produtos (admin)
- RF09 - Atualização de status dos pedidos (admin)

### Requisitos Não Funcionais:
- RNF01 - Interface responsiva e amigável
- RNF02 - Senhas com hash seguro (BCrypt)
- RNF03 - Arquitetura MVC
- RNF04 - Banco de dados relacional
- RNF05 - API RESTful

---

## 🧑‍💻 Desenvolvedor

**Gustavo Souza Lopes**  
Canal: [O Dev Estranho](https://youtube.com/@ODevEstranho)  
Projeto de estudo e prática pessoal.
