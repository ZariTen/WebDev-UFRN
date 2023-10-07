<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Projeto 01 - Cadastro</title>
    <link rel="stylesheet" type="text/css" href="css/cadastro.css">
</head>
<body>
    <h1>Cadastrar Contato</h1>
    <form action="cadastroContatoServlet" method="post">
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" required><br><br>

        <label for="telefoneCelular">Telefone Celular:</label>
        <input type="text" id="telefoneCelular" name="telefoneCelular" required><br><br>

        <label for="telefoneResidencial">Telefone Residencial:</label>
        <input type="text" id="telefoneResidencial" name="telefoneResidencial"><br><br>

        <label for="email">E-mail:</label>
        <input type="email" id="email" name="email" required><br><br>

        <label for="dataNascimento">Data de Nascimento:</label>
        <input type="date" id="dataNascimento" name="dataNascimento" required><br><br>

        <input type="submit" value="Enviar">
        <input type="reset" value="Limpar">
    </form>
</body>
</html>