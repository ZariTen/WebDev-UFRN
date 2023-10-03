<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Projeto 01 - Login</title>
<link rel="stylesheet" type="text/css" href="estilo.css">
</head>
<body>
	<div>
		<form action="telaLoginServlet" method="post" id="formLogin">
			<input type="text" name="usuario" placeholder="Nome de usuário">
			<input type="password" name="senha" placeholder="Senha">
			<input type="submit" name="enviar" value="Logar">
		</form>
	</div>
</body>
</html>