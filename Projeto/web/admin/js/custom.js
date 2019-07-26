function funcaoDependente() 
{
  var selected, end, tel, cpf, socio;

  selected = document.getElementById('selecaoSocioDependente');

  console.log(selected.selectedIndex);
  
  end = document.getElementById('inputEndereco');
  tel = document.getElementById('inputTelefone');
  cpf = document.getElementById('inputCpf');
  socio = document.getElementById('selectSocio');


  if(selected.selectedIndex == 1)
  {
    end.style.display = "none";
    tel.style.display = "none";
    cpf.style.display = "none";
    socio.style.display = "";
  }

  else
  {   
    end.style.display = "";
    tel.style.display = "";
    cpf.style.display = "";
    socio.style.display = "none";
  }
}