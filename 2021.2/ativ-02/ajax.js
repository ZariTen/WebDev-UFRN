/*
 * Função AJAX base do tipo assíncrona.
 * type é o tipo de objeto que você quer recuperar.
 * value é o valor do parâmetro para filtrar os resultados dos tipos 2, 3 e 4.
 * [Importante!] Você não pode, em nenhuma hipótese, alterar a função xhttpAssincrono.
 */
function xhttpAssincrono(callBackFunction, type, value) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
      // Chama a função em callback e passa a resposta da requisição
      callBackFunction(this.responseText);
    }
  };
  // Path para a requisição AJAX.
  var url = "http://jsonplaceholder.typicode.com/";
  switch (type) {
    case 1:
      url += "users";
      break;
    case 2:
      url += "posts?userId=" + value;
      break;
    case 3:
      url += "todos?userId=" + value;
      break;
    case 4:
      url += "comments?postId=" + value;
      break;
  }
  xhttp.open("GET", url, true);
  xhttp.send();
}

var selectUsusarios = document.getElementById("usuarios");
var listaUsuarios = [];

//Adiciona um nome no select
function addToSelect(name) {
  var option = document.createElement("option");
  option.text = name;
  selectUsusarios.add(option);
  listaUsuarios.push(name);
}

//Adquire nome do usuário a partir do JSON
function getUserFromJson(json) {
  var parsed = JSON.parse(json);
  for (var k in parsed) {
    addToSelect(parsed[k].name);
  }
}

xhttpAssincrono(getUserFromJson, 1, 0);

var radioPost = document.getElementById("postsradio");
var radioTodos = document.getElementById("todosradio");
var optionsDiv = document.getElementById("todosOptionsDiv");
var listaPost = document.getElementById("tarefasPosts");

//Se mudar usuário, regerar listas
selectUsusarios.onchange = () => {
  if (radioPost.checked) {
    generatePosts();
  } else if (radioTodos.checked) {
    generateTodos();
  }
};

radioTodos.onchange = generateTodos;

function generateTodos() {
  document.getElementById("titleSelected").innerHTML = "Tarefas do usuário";
  optionsDiv.style.display = "inline";
  listaPost.innerHTML = "";
  if (selectUsusarios.value != "Selecione um usuário") {
    var index = listaUsuarios.findIndex(
      (name) => name == selectUsusarios.value
    );
    xhttpAssincrono(adicionarTarefas, 3, index + 1);
  }
}

radioPost.onchange = generatePosts;

function generatePosts() {
  optionsDiv.style.display = "none";
  document.getElementById("titleSelected").innerHTML = "Posts do usuário";
  if (selectUsusarios.value != "Selecione um usuário") {
    var index = listaUsuarios.findIndex(
      (name) => name == selectUsusarios.value
    );
    xhttpAssincrono(adicionarPost, 2, index + 1);
  }
}

function adicionarPost(json) {
  var parsed = JSON.parse(json);
  listaPost.innerHTML = "";
  for (k in parsed) {
    var posts = document.createElement("li");
    posts.appendChild(document.createTextNode(parsed[k].title));
    listaPost.appendChild(posts);
  }
}

var alltaskradio = document.getElementById("alltaskradio");
var finishedradio = document.getElementById("finishedradio");
var notfinishedradio = document.getElementById("notfinishedradio");

alltaskradio.onchange =
  finishedradio.onchange =
  notfinishedradio.onchange =
    generateTodos;

function adicionarTarefas(json) {
  var parsed = JSON.parse(json);
  listaPost.innerHTML = "";
  for (k in parsed) {
    var posts = document.createElement("li");
    if (alltaskradio.checked) {
      //Mostrar todas tarefas
      posts.appendChild(
        document.createTextNode(
          "Concluída: " + parsed[k].completed + " - " + parsed[k].title
        )
      );
      listaPost.appendChild(posts);
    } else if (finishedradio.checked) {
      //Mostrar apenas tarefas completas
      if (parsed[k].completed) {
        posts.appendChild(
          document.createTextNode(
            "Concluída: " + parsed[k].completed + " - " + parsed[k].title
          )
        );
        listaPost.appendChild(posts);
      }
    } else if (notfinishedradio.checked) {
      //Mostrar tarefas incompletas
      if (!parsed[k].completed) {
        posts.appendChild(
          document.createTextNode(
            "Concluída: " + parsed[k].completed + " - " + parsed[k].title
          )
        );
        listaPost.appendChild(posts);
      }
    }
  }
}
