// Linkando um vídeo a um modal
$(document).ready(function(){
    var $videoSrc;  
    $('.video-btn').click(function() {
        $videoSrc = $(this).data( "src" );
    });

    $('#modalVideo').on('shown.bs.modal', function (e) {
        $("#video").attr('src',$videoSrc + "?rel=0&amp;showinfo=0&amp;modestbranding=1&amp;autoplay=1" ); 
    })
    
    $('#modalVideo').on('hide.bs.modal', function (e) {
        $("#video").attr('src',$videoSrc); 
    })
});

// Botão de ajuda
$(function() {
    $('[data-toggle="popover"]').popover()
})


function funcaoPesquisar() 
{
  // Declare variables
  var input, filter, ul, li, a, i, txtValue, hr, contadorFilmes = 0;

  input = document.getElementById('input-pesquisa');
  filter = input.value.toUpperCase();

  ul = document.getElementById("lista-filmes");
  li = ul.getElementsByTagName('li');
  
  hr = document.getElementById("lista-filmes").getElementsByTagName("hr");

  for (i = 0; i < li.length; i++)
  {
      a = li[i].getElementsByTagName("div")[2].getElementsByTagName("a")[0];
      
      txtValue = a.textContent || a.innerText;

      if (txtValue.toUpperCase().indexOf(filter) > -1)
        li[i].style.display = "";
        
      else
      {
          li[i].style.display = "none";
          hr[i].style.display = "none";
          contadorFilmes = contadorFilmes + 1;
      }
  }

  if (contadorFilmes == 0)
    mostrarLinhas();
}

function mostrarLinhas()
{
    var i = 0;
    ul = document.getElementById("lista-filmes").getElementsByTagName("hr");

    for(i = 0; i < ul.length; i ++)
        ul[i].style.display = "";
}