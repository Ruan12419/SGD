var limiteForm = true;

export function CriaRequest() {
    try {
      var request = new XMLHttpRequest();
    } catch (IEAtual) {
      try {
        var request = new ActiveXObject("Msxml2.XMLHTTP");
      } catch(IEAntigo) {
        try {
          var request = new ActiveXObject("Microsoft.XMLHTTP");
        } catch(falha) {
          var request = false;
        }
      }
    }
    if (!request) {
      alert("Seu Navegador não suporta Ajax!");
    } else {
      return request;
    }
}

function enviarForm(){
    var alunoCPF = document.getElementById("alunorg").value
    var alunonome = document.getElementById("alunonome").value
    var alunocurso = document.getElementById("alunocurso").value
    var quartodorm = document.getElementById("quarto-disponivel").value
    alert(quartodorm);

    var form = document.getElementById("formPost")
    var xhr = CriaRequest();
    xhr.open(form.method, form.action, true);
    xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
    var j = {
        "alunoCPF": "" + alunoCPF + "",
        "alunoNome": "" + alunonome +"",
        "alunoCurso": "" + alunocurso +""

    };
    xhr.send(JSON.stringify(j));

    alugaQuarto(quartodorm, alunoCPF);

}

function alugaQuarto(quartoNum, alunoCPF) {
  var xhr = CriaRequest();

  var body = "---/SGD/cadastro/cadastrar?quartoNumero=" + quartoNum + "&alunoCPF=" + alunoCPF;
  xhr.open("Put", body, true);
  xhr.send();
}

function receberForm(){

  if(limiteForm){
    var form = document.getElementById("formGet");
    var xhr = CriaRequest();
    xhr.open(form.method, form.action, true);
    xhr.send();

    xhr.onreadystatechange = function() {
      if(xhr.readyState === 4 && xhr.status === 200) {
        json = JSON.parse(xhr.responseText);
        console.log(json);

        var table = document.getElementById("tabela");
        table.style.display = "inline-table";
        var numOfRows = table.rows.length;
        var numOfCols = table.rows[numOfRows-1].cells.length;
        var nomeRow = table.insertRow(numOfRows);
        //nomeRow.innerHTML = "Nome do aluno: ";
        var cpfRow = table.insertRow(table.rows.length);
        //rgRow.innerHTML = "RG do Aluno: ";
        var cursoRow = table.insertRow(table.rows.length);
        //cursoRow.innerHTML = "Curso: ";
        var quartoRow = table.insertRow(table.rows.length);
        //quartoRow.innerHTML = "Quarto: ";
        var registroRow = table.insertRow(table.rows.length);
        //registroRow.innerHTML = "Data de Registro: ";



        for (var i = 0; i < json.numberOfElements; i++) {
          for (var j = 0; j < 1; j++) {
            nomeCell = nomeRow.insertCell(j);
            nomeCell.innerHTML = json.content.at(i).alunoNome;
            cpfCell = cpfRow.insertCell(j);
            cpfCell.innerHTML = json.content.at(i).alunoCPF;
            cursoCell = cursoRow.insertCell(j);
            cursoCell.innerHTML = json.content.at(i).alunoCurso;
            quartoCell = quartoRow.insertCell(j);
            quartoCell.innerHTML = json.content.at(i).quartoDorm;
            registroCell = registroRow.insertCell(j);
            var dataReg = json.content.at(i).registrationDate
            var data = "";
            data += dataReg[8] + dataReg[9] + "/" + dataReg[5] + dataReg[6] + "/" + dataReg[0] + dataReg[1] + dataReg[2] + dataReg[3];
            data += " ";
            for(var k = 11; k < 19; k++){
              data += dataReg[k];
            }
            registroCell.innerHTML = data;

          }

        }
        nomeLabelCell = nomeRow.insertCell(0);
        nomeLabelCell.innerHTML = "Nome do Aluno: ";
        cpfLabelCell = cpfRow.insertCell(0);
        cpfLabelCell.innerHTML = "CPF do Aluno: ";
        cursoLabelCell = cursoRow.insertCell(0);
        cursoLabelCell.innerHTML = "Curso: ";
        quartoLabelCell = quartoRow.insertCell(0);
        quartoLabelCell.innerHTML = "Quarto: ";
        registroLabelCell = registroRow.insertCell(0);
        registroLabelCell.innerHTML = "Data de Registro: ";

      }
    };
    limiteForm = false;
  } else {
    alert("Os dados já foram recebidos!");
  }
}
function mudaCorBtn(){
  for (var i = 0; i < document.getElementsByClassName("botao").length; i++) {
    document.getElementsByClassName("botao")[i].style.backgroundColor = "black";
  }
}
function voltaCor(){
  for (var i = 0; i < document.getElementsByClassName("botao").length; i++) {
    document.getElementsByClassName("botao")[i].style.backgroundColor = "steelblue";
  }
}


window.onload = function populaSelect() {
  const popula = document.getElementById("quarto-disponivel");
  var xhr = CriaRequest();
  xhr.open("Get", "---/SGD/cadastro/CDSTRD", true);
  xhr.send();

  xhr.onreadystatechange = function() {
    if(xhr.readyState === 4 && xhr.status === 200) {
      json = JSON.parse(xhr.responseText);
      console.log(json);

      var contentList = [];

      for (var i = 0; i < json.numberOfElements; i++) {
        contentList[i] = json.content.at(i).quartoNumero;
      }
      contentList.sort();
      contentList.forEach((quartoNum) => {
        option = new Option(quartoNum, quartoNum.toUpperCase());
        popula.options[popula.options.length] = option;
      });

    }
  }

}
