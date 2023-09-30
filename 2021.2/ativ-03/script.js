var artista = "";
var musica = "";

function xhttpAssincrono(callBackFunction) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
      // Chama a função em callback e passa a resposta da requisição
      callBackFunction(this.responseText);
    }
  };
  // Path para a requisição AJAX.
  var url = "https://api.lyrics.ovh/v1/";
  url += artista + "/" + musica;
  xhttp.open("GET", url, true);
  xhttp.send();
}

//Carrega músicas do localStorage e adicionam na Div
var musicasCacheDiv = document.getElementById("musicasCache");
function carregarMusicasStorage(){
  musicasCacheDiv.innerHTML = "";
  Object.keys(localStorage).forEach(key => {
        let musicasNome = localStorage.getItem(key);
        let musicasArtista = musicasNome.split("|").forEach(musicaCache =>{
           musicasCacheDiv.innerHTML += "<li>" + key + " - " + musicaCache + "</li>";
        });


  });

}

//Chama ao iniciar site
carregarMusicasStorage();

//Adiciona letra da música na div
var musicaDiv = document.getElementById("letramusicaDiv");
function adicionarLetraDiv(letra) {
  letraJson = JSON.parse(letra);
  musicaDiv.innerHTML = "<pre>" + letraJson.lyrics + "</pre>";

}

//Adiciona música no localStorage, caso artista (key) já exista, adiciona música no value
function adicionarMusicaStorage(){
  let key = artista.toLowerCase();
  let value = musica.toLowerCase();
  if(localStorage.getItem(key) === null){
     localStorage.setItem(key,value);
  } else {
     let musicas = localStorage.getItem(key);
     if(!musicas.includes(value)){
        localStorage.setItem(key, musicas + " | " + value);
     }

  }
}

const artistaInput = document.getElementById("artistaInput");
const musicaInput = document.getElementById("musicaInput");

//Pesquisa a letra da música ao apertar botão
function pesquisarLetra(){
  artista = artistaInput.value;
  musica = musicaInput.value;
  if(artista == "" || musica == "") { return;}
  xhttpAssincrono(adicionarLetraDiv);
  adicionarMusicaStorage();
  carregarMusicasStorage();
}
