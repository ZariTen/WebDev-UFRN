var botoes = document.getElementsByClassName("button");
var painel = document.getElementById("visor");

var firstNumber = null;
var secondNumber = null;
var operation = "";

document.querySelectorAll(".button").forEach((botao) => {
  botao.addEventListener("click", () => {
    if (
      painel.innerHTML == "0" ||
      painel.innerHTML == "+" ||
      painel.innerHTML == "-" ||
      painel.innerHTML == "*" ||
      painel.innerHTML == "/"
    ) {
      if (botao.textContent == ",") {
        return;
      }
      painel.innerHTML = botao.textContent;
    } else {
      if (painel.innerHTML.includes(".") && botao.textContent == ",") {
        return;
      }
      if (botao.textContent == ",") {
        painel.innerHTML += ".";
        return;
      }
      painel.innerHTML += botao.textContent;
    }
  });
});

document.querySelectorAll(".button-special").forEach((botao) => {
  botao.addEventListener("click", () => {
    operation = botao.textContent;
    if (firstNumber == null) {
      firstNumber = painel.innerHTML;
    }
    secondNumber = null;

    painel.innerHTML = botao.textContent;
  });
});

document.querySelectorAll(".button-rect").forEach((botao) => {
  botao.addEventListener("click", () => {
    if (botao.textContent == "0" && painel.innerHTML == "0") {
      return;
    } else if (botao.textContent == "0") {
      painel.innerHTML += 0;
    }

    if (botao.textContent == "=") {
      if (secondNumber == null) {
        secondNumber = painel.innerHTML;
      }
      painel.innerHTML = equal().toPrecision(6);
      firstNumber = painel.innerHTML;
    }
  });
});

document.getElementById("button-ac").addEventListener("click", () => {
  firstNumber = null;
  secondNumber = null;
  operation = "";
  painel.innerHTML = 0;
});

function equal() {
  firstNumber = parseFloat(firstNumber);
  secondNumber = parseFloat(secondNumber);
  switch (operation) {
    case "+":
      return firstNumber + secondNumber;
    case "-":
      return firstNumber - secondNumber;
    case "*":
      return firstNumber * secondNumber;
    case "/":
      return firstNumber / secondNumber;
    default:
      return "ERROR";
  }
}
