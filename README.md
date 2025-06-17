# PetStoreApi

````markdown
# PetStore API Test Suite

Este repositório contém uma suíte de testes automatizados em Java + RestAssured para a API pública do Swagger PetStore. Os testes cobrem:

1. POST /store/order – Cadastrar um novo pedido de pet com sucesso  
2. GET /pet/{petId} – Buscar pet inexistente e validar erro 404  
3. PUT /pet – Criar e, em seguida, atualizar dados de um pet existente  
4. GET /pet/findByStatus** – Listar pets com status `pending` e validar resultado  

---

## 📋 Pré-requisitos

Antes de rodar os testes, você precisa ter:

* Java 11+  
  Verifique com:
  java --version

* Maven
  No Windows, recomendamos instalar via Chocolatey (veja abaixo).
````
---

## ⚙️ Instalando o Maven (Windows + Chocolatey)

1. Abra o **PowerShell** como **Administrador**
2. Rode:

   ```powershell
   choco install maven -y
   refreshenv
   ```
3. Confira a versão do Maven:

   ```powershell
   mvn -version
   ```

> Se não estiver no Windows ou sem Chocolatey, instale pelo site oficial:
> [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)

---

## 📂 Estrutura do Projeto

```
petstore-tests/
├── pom.xml
└── src
    └── test
        └── java
            └── com
                └── example
                    └── petstore
                        └── PetStoreApiTests.java
```

* **pom.xml** — define dependências (JUnit 5, RestAssured, Hamcrest) e plugin Surefire
* **src/test/java/...** — seus testes JUnit anotados com `@Test`

---

## ▶️ Como rodar os testes

1. **Clone** o repositório:

   ```bash
   git clone https://github.com/seu-usuario/petstore-tests.git
   cd petstore-tests
   ```
2. **Execute** a suíte de testes:

   ```bash
   mvn test
   ```

   ou, no Windows:

   ```powershell
   mvn.cmd test
   ```

Você verá no console o resultado dos 4 cenários. Em caso de sucesso, terá:

```
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

---



