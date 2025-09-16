# Clubs‑MVC

**Clubs‑MVC** é uma aplicação Web baseada em Spring Boot que demonstra o padrão de arquitetura **Model‑View‑Controller (MVC)**.  
O sistema permite cadastrar e autenticar usuários, criar clubes com informações detalhadas e associar eventos a esses clubes.  
O front‑end utiliza o motor de templates **Thymeleaf**, enquanto a persistência é feita com **Spring Data JPA** e **PostgreSQL**.  
A segurança é implementada com **Spring Security**.

---

## 🚀 Funcionalidades

- Autenticação e registro de usuários (Spring Security);
- Criação, edição, listagem e exclusão de **Clubes**;
- Associação de **Eventos** a clubes;
- Gestão de papéis de usuário (**Role**: ADMIN, USER);
- Integração com banco de dados relacional (PostgreSQL);
- Camada de visualização construída com **Thymeleaf**.

---

## 🛠️ Tecnologias Utilizadas

- Java 17+
- Spring Boot 3
- Spring Data JPA
- Spring Security
- Thymeleaf
- PostgreSQL
- Maven

---

## 📂 Estrutura do Projeto

```
src/main/java/com/springboot/mvc
 ├── controller   # Controladores MVC (Auth, Club, Event)
 ├── dto          # Objetos de Transferência de Dados (DTOs)
 ├── mapper       # Conversão entre DTOs e entidades
 ├── models       # Entidades JPA (User, Role, Club, Event)
 ├── repository   # Interfaces de persistência
 ├── security     # Configurações de autenticação/autorização
 ├── service      # Interfaces de serviços
 └── service/impl # Implementações de serviços
```

---

## ⚙️ Configuração

1. Clone este repositório:

   ```bash
   git clone https://github.com/MTaier/Clubs-MVC.git
   ```
   
2. Configure o banco de dados PostgreSQL:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/clubs
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update
   ```
   
3. Execute o projeto:
   ```bash
   mvn spring-boot:run
   ```
   
4. A aplicação será iniciada em `http://localhost:8080`. Os seguintes endpoints principais estarão disponíveis:
  - `/register` – tela de registro de novos usuários e criação da conta.  
  - `/login` – tela de autenticação personalizada.  
  - `/clubs` – lista de todos os clubes cadastrados; acessível sem login graças à configuração de segurança.  
  - `/clubs/new` – formulário para criação de um novo clube (necessita autenticação).  
  - `/clubs/{id}` – detalhamento de um clube específico, incluindo eventos associados.  
  - `/events` – lista de todos os eventos cadastrados.  
  - `/events/{clubId}/new` – criação de evento associado a um clube.

> A configuração de segurança define que as páginas de listagem de clubes, login e registro são públicas, enquanto outras rotas exigem autenticação. É possível utilizar as credenciais definidas em `spring.security.user.name` e `spring.security.user.password` para um acesso rápido em ambientes de desenvolvimento.

---


## 📌 Contribuição

Contribuições são bem-vindas!  
Abra uma issue ou envie um pull request para sugerir melhorias.

---

## 👨‍💻 Autor

Projeto desenvolvido por **Marcio Taier**.  
[LinkedIn](https://www.linkedin.com/in/marciotaier/)
