# Lumind

Aplicativo desktop em JavaFX para acompanhar a rotina de crianças com Transtorno do Espectro Autista (TEA). A ideia é reunir num mesmo sistema o trabalho dos profissionais de saúde e os registros do dia a dia feitos pelos responsáveis.

O projeto nasceu na disciplina de Programação Orientada a Objetos, com arquitetura MVC. Esta versão é a refatoração feita para a disciplina de Design de Software, em que aplicamos quatro padrões de projeto do GoF: **Singleton**, **Factory Method**, **Observer** e **Memento**.

## Perfis de acesso

| Perfil | O que pode fazer |
|---|---|
| Administrador | Gerenciar administradores e profissionais de saúde |
| Profissional de saúde | Cadastrar responsáveis e pacientes TEA, gerenciar atividades e feedbacks |
| Responsável | Acessar relatórios e eventos |
| Pessoa com TEA | Entrar na área do paciente (por enquanto só a tela de boas-vindas; as atividades e feedbacks ainda vão aparecer ali) |

## Padrões de projeto

### Singleton

**Problema:** o usuário logado era repassado pelo construtor de quase todas as telas, mesmo nas que só precisavam encaminhá-lo para a próxima.

**Solução:** a classe `SessaoUsuario` guarda o usuário logado em uma instância única. Qualquer tela consulta com `SessaoUsuario.getInstancia().getUsuarioLogado()`. A sessão começa no login e é encerrada sempre que a tela de login volta a ser exibida.

`HistoricoAtividade` também é um Singleton, porque o histórico de desfazer precisa continuar existindo quando o usuário troca de tela.

### Factory Method

**Problema:** o `LoginController` conhecia os quatro menus e usava um `switch` para decidir qual abrir conforme o tipo do usuário.

**Solução:**

- `MenuUsuario` é a interface do produto (método `mostrar()`).
- `MenuCreator` é o criador abstrato, com o factory method `criarMenu(Stage)`.
- Há um criador concreto para cada tipo de usuário, e cada um devolve o seu menu (`MenuAdministradorController`, `MenuProfissionalController`, `MenuResponsavelController` e `MenuPessoaTeaController`).

O login só chama `MenuCreator.para(tipo).abrirMenu(stage)`. Para incluir um novo tipo de usuário, basta criar o menu, um criador novo e registrá-lo no mapa de `MenuCreator`.

### Observer

**Problema:** quem grava os dados não tinha como avisar as telas de que algo mudou.

**Solução:** `ArquivoAtividade` é o sujeito. Ele mantém uma lista de `Observador` e avisa todos depois de cada gravação bem-sucedida. A `AtividadeListaController` se inscreve quando abre e cancela a inscrição ao sair da tela. Assim a tabela se recarrega sozinha quando os dados mudam, como acontece ao desfazer uma operação.

### Memento

**Problema:** não havia como recuperar uma atividade alterada ou excluída por engano.

**Solução:**

- `Atividade` é o originador. O método `salvarEstado()` cria um `Atividade.Memento`, que é imutável e só a própria `Atividade` consegue ler.
- `HistoricoAtividade` é o zelador e guarda os mementos em uma pilha.
- A tela de lista de atividades tem o botão **Desfazer**, que reverte a última alteração ou exclusão.

Se o ID de uma atividade excluída já tiver sido reaproveitado por uma nova, a restaurada recebe outro ID livre, para não sobrescrever nada.

Limitações: o histórico fica só em memória (some ao fechar o programa) e o cadastro de uma nova atividade não pode ser desfeito.

## Como executar

**Requisito:** JDK 24, que é a versão configurada no `pom.xml`. O Maven não precisa estar instalado, o projeto já traz o Maven Wrapper.

**Pelo IntelliJ**

1. Abra a pasta `CRUD_Responsavel` como projeto Maven.
2. Espere as dependências serem baixadas.
3. Execute o método `main` da classe `UsuarioApp`.

**Pelo terminal**

```
cd CRUD_Responsavel
./mvnw clean javafx:run
```

No Windows, use `mvnw.cmd clean javafx:run`.

## Usuários para teste

Na primeira execução, se não houver nenhum usuário cadastrado, o sistema cria dois:

| Perfil | E-mail | Senha |
|---|---|---|
| Administrador | admin@admin.com | admin123 |
| Profissional de saúde | prof@prof.com | prof123 |

Responsáveis e pacientes TEA não têm usuário padrão. Entre como profissional, cadastre-os em **Gerenciar Responsáveis** e **Gerenciar Pacientes TEA** e depois faça login com o e-mail e a senha cadastrados.

## Armazenamento dos dados

Não há banco de dados. Os dados são gravados em arquivos `.dat` (`usuarios.dat`, `Atividade.dat`, `Relatorio.dat`, `Evento.dat` e `Feedback.dat`) por serialização de objetos Java. Os arquivos são criados na pasta de onde o programa é executado. Para começar do zero, basta apagá-los.

## Estrutura do código

O código fica em `CRUD_Responsavel/src/main/java/com/example/crud_responsavel/`. Cada módulo (`Administrador`, `Atividade`, `Evento`, `Feedback`, `PessoaTea`, `Profissional`, `Relatorio` e `Responsavel`) segue a mesma organização: a classe de modelo, a classe `Arquivo...` que cuida da persistência e a pasta `Controllers` com as telas.

Na raiz do pacote ficam as classes compartilhadas: `UsuarioApp` (início da aplicação), `LoginController`, `Usuario`, `Tipo`, `ArquivoUsuario` e as classes dos padrões `SessaoUsuario`, `MenuUsuario`, `MenuCreator` e `Observador`.
