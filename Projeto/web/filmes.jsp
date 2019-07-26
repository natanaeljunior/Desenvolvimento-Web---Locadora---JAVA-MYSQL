<%@page import="model.domain.Titulo"%>
<%@page import="model.application.APLCadastrarTitulo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="pt-br">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="css/trabalho.css">
<link rel="stylesheet" href="css/zoom.css">

<!-- Script do trabalho / jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/js/trabalho.js"></script>

<!-- Font Awesome -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">

<title>Locadora IFES</title>
</head>

<body>

    <!-- Navbar -->
    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 bg-light border-bottom shadow-sm">
        <h5 class="my-0 mr-md-auto font-weight-normal text-dark">Locadora</h5>
        <nav class="my-2 my-md-0 mr-md-3 navbar-dark">
            <a class="p-2 text-dark" href="index.jsp">Home</a>
            <a class="p-2 text-dark active" href="filmes.jsp">Títulos</a>
            <a class="p-2 text-dark" href="precos.jsp">Preços</a>
        </nav>
    </div>

    <!-- Carousel -->
    <div id="carouselSite" class="carousel slide" data-ride="carousel">
      
            <!-- Bloco 1 - Visualização do número de imagens do carousel -->
            <ol class="carousel-indicators">
                <li data-target="#carouselSite" data-slide-to="0" class="active"></li>
                <li data-target="#carouselSite" data-slide-to="1"></li>
            </ol>
        
            <!-- Bloco 2 - Imagens e textos do carousel -->
            <div class="carousel-inner">
        
                <div class="carousel-item active">
                <img src="img/venom-slide.jpg" class="img-fluid img-responsive d-block">
        
                <div class="carousel-caption d-none d-md-block text-light">
                    <h1>Venom</h1>
                    <p class="lead">Assista este incrível filme</p>
                </div>
        
                </div>
        
                <div class="carousel-item">
                <img src="img/guerra-infinita-slide.jpg" class="img-fluid img-responsive d-block">
        
                <div class="carousel-caption d-none d-md-block text-light">
                    <h1>Vingadores: Guerra Infinita</h1>
                    <p class="lead">Assista este incrível filme</p>
                </div>
        
                </div>
            </div>
        
            <!-- Bloco 3 - Navegação do carousel -->
            <a class="carousel-control-prev" href="#carouselSite" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon"></span>
                <span class="sr-only">Anterior</span>
            </a>
        
            <a class="carousel-control-next" href="#carouselSite" role="button" data-slide="next">
                <span class="carousel-control-next-icon"></span>
                <span class="sr-only">Anterior</span>
            </a>
        
    </div>

    <div class="netflix-background">
        <div class="container">

            <h2 class="display-3 text-center my-5 text-white"><i class="fas fa-video"></i> Todos os filmes</h2>
    
            <!-- Barra de pesquisa -->
            <div class="input-group text-center mx-auto" style="width: 50%">
                <div class="input-group-prepend">
                    <select class="custom-select" id="inputGroupSelect02">
                        <option selected value="1">Filme</option>
                        <option value="2">Ator</option>
                        <option value="3">Diretor</option>
                    </select>
                </div>
        
                <input type="text" id="input-pesquisa" class="form-control" onkeyup="funcaoPesquisar()" placeholder="Pesquisa...">
    
            </div>
    
            <!-- Lista de filmes -->
            
            <ul class="list-unstyled my-5" id="lista-filmes">
                
    
                    
                        <%
                           
                              String  spageid=request.getParameter("page");
                              int total=2;  
                                if(spageid==null){
                                    spageid="1";
                                    
                                }
                                
                               List<Titulo> currentTitulos = APLCadastrarTitulo.listaPaginacao(Integer.parseInt(spageid), total);   

                        //    List<Titulo> currentTitulos = APLCadastrarTitulo.findTitles();

                            for (Titulo titulos : currentTitulos) 
                            {
                                
                                out.print("<li class='media justify-content-center'>");
                                out.print("<div class='hovereffect col-2'>");
                                   out.print("<img class='img-responsive' style='border-top-left-radius: 40px; border-bottom-right-radius: 40px' src='"+titulos.getImagem()+"' alt='" + titulos.getNome() + "' height='100%'>");
                                    out.print("<div class='overlay'>");
                                        out.print("<h2>VER COMPLETO</h2>");
                                        out.print("<a class='info video-btn' href='#' data-toggle='modal' data-src='https://www.youtube.com/embed/8MG2v1MK7Lw' data-target='#modalVideo'>Ver Trailer</a>");
                                    out.print("</div>");
                                out.print("</div>");
    
                      out.print("<div class='media-body col-5 ml-3' id='conteudo-filme'>");
    
                          out.print("<a class='mb-1' id='"+titulos.getNome()+"' href='http://www.google.com/search?q="+titulos.getNome()+"' target='_blank'>"+titulos.getNome()+" &nbsp; </a>");
                        if (APLCadastrarTitulo.verificarDisponibilidade(titulos) == false){
                           out.print("<span id='disponibilidade' class='badge badge-pill badge-danger'>INDISPONIVEL</span>");
                        } else{
                             out.print("<span id='disponibilidade' class='badge badge-pill badge-success'>DISPONIVEL</span>"); 
                            
                        }   
                       
                        out.print("<p style='font-size: 12px' class='my-1 lead text-muted'> Categoria: "+titulos.getCategoria().name()+"</p>");
                        
                        out.print("<p class='my-3 text-white'>"+titulos.getSinopse()+"</p>");
                        out.print("<button type='button' class='btn btn-primary' data-toggle='modal' data-target='#exampleModal' "
                                + "data-nome='"+titulos.getNome()+"'"
                                + "data-image='"+titulos.getImagem()+"'"
                                + "data-ano='"+titulos.getAno()+"'"
                                + "data-diretor='"+titulos.getDiretor().getnomeDiretor()+"'"
                                + "data-classe='"+titulos.getClasse().getNomeClasse()+"'"
                                + "data-sinopse='"+titulos.getSinopse()+"'"  
                                + "data-categoria='"+titulos.getCategoria()+"'"   
                                + "data-elenco='"+titulos.getNomeAtores()+"'"   
                                + ">Ver título</button>");
                        
                        out.print(" </div>");
                         out.print(" </li>");
                         out.print(" <hr width='60%' style='background: white'>");
                      
                            }
                        %>    

               
            </ul>
    
            <!-- Paginação -->
            <div class="row">
                <div class="col-12 my-3">
                    <nav >
                        <ul class="pagination justify-content-center">
                                                       <%
                               int sizeList = APLCadastrarTitulo.findTitles().size();
                               for (int i = 0; i <= (sizeList/2)+1; i++) {
                                 out.print("<li class='page-item'>");
                                out.print("<a class='page-link' href='filmes.jsp?page="+(i+1)+"#conteudo-filme'>"+(i+1)+"</a>");
                                out.print("</li>  ");
                                   }
                           %>
                        </ul>
                    </nav>
                    
                </div>
            </div>
        </div>
        
    </div>

    <!-- Modal (Títulos) -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <img src="img/star.png">&nbsp;</img>
                   <h5 class="modal-title" id="exampleModalLabel"></h5>
                    <span id="disp" class="badge badge-pill badge-danger ml-2 mt-2"></span>
                    
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>

                <div class="modal-body">

                    <div class="row ml-2">

                        <div class="col-6">
                            <!-- Imagem -->
                            <img id="imgTitulo" style="border-top-left-radius: 40px; border-bottom-right-radius: 40px; height:350px;" 
                                 src="/img/filme-venom.jpg" alt="Venom (2018)">
                        </div>

                        <div class="col-6">
                            
                            <!-- Título (Brasil) -->
                            <p class="p-class-modal text-muted ml-3 m-1">Título: 
                                <a style="color: black; font-size: 14px;" id="nomeTitulo" ></a>
                            </p>

                            <!-- Elenco -->
                            <p class="p-class-modal text-muted ml-3 m-1">Elenco: 
                                <a  style="color: black; font-size: 14px;" id="elenco"> </a>
                            
                            </p>

                            <!-- Diretores -->
                            <p class="p-class-modal text-muted ml-3 m-1">Diretores: 
                                <a style="color: black; font-size: 14px;" target="_blank" id="diretorTitulo"></a>
                            </p>

                            <!-- Ano -->
                            <p class="p-class-modal text-muted ml-3 m-1">Ano: 
                                <a style="color: black; font-size: 14px;" id="anoTitulo"></a>
                            </p>
         
                            <!-- Classe -->
                            <p class="p-class-modal text-muted ml-3 m-1">Classe: 
                                <a style="color: black; font-size: 14px;" class="badge badge-pill badge-warning my-auto" id="classeTitulo"></a>
                            </p>

                            <!-- Categorias -->
                            <p class="p-class-modal text-muted ml-3 m-1" ">Categorias: 
                                <a style="color: black; font-size: 14px " id="categoria"></a>
                               
                            </p>


                        </div>
                                    
 
                    </div>

                    <div class="row mt-3">
                        <h5 class="display-5 ml-3" >SINOPSE</h5> 
                    </div>
                   
                    <div class="row">
                        <p class="lead ml-3" style="font-size: 0.8rem; font-weight: bold"><span class="badge badge-pill badge-danger my-auto">14</span> 
                            <strong> Não recomendado para menores de 14 anos</strong></p> 
                    

                        <!-- Sinopse -->
                       
                        <p  id="sinopse" class="p-class-modal text-muted ml-3"> </p>
                    </div>

                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Fechar</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal (Trailer) -->
    <div class="modal fade" id="modalVideo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-class" role="document">
            <div class="modal-content">

            <div class="modal-body modal-body-class">
                <button type="button" class="close close-class" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>        
                
                <div class="embed-responsive embed-responsive-16by9">
                <iframe class="embed-responsive-item" src="" id="video" allowscriptaccess="always"></iframe>
                </div>
                
            </div>

            </div>
        </div>
    </div> 

    <!-- Jumbotron -->
    <div class="jumbotron jumbotron-fluid my-5">
        <div class="container">
        
            <div class="row">
                <div class="col-12 text-center">
    
                <h1 class="display-4">Quem somos?</h1>
                <p class="lead">O Looke é a forma mais simples de assistir a filmes. Nosso serviço de assinatura conta com vasto número de títulos, e o mais legal: você pode alugar os lançamentos dentro da mesma plataforma sem sair de casa.</p>
                <hr>
    
                </div>
    
            </div>
    
                <div class="row text-center">
                <h3 class="display-6 mx-auto mb-3">Social</h3>
                <div class="col-12">
                    <!--Facebook-->
                    <button type="button" class="btn btn-dark btn-circle btn-xl" style="background: rgb(59, 89, 152)"><i class="fab fa-facebook-f"></i></button>
        
                    <!--Twitter-->
                    <button type="button" class="btn btn-dark btn-circle btn-xl  ml-2" style="background: rgb(29, 202, 255)"><i class="fab fa-twitter"></i></button>
                </div>
    
            </div>
    
        </div>
    </div>
      
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  
    
    
    <script>
        /////// ALTERAR TÍTULO   
         $('#exampleModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); 
        var nomeTitulo = button.data('nome');
        var pathImage = button.data('image');
        var ano = button.data('ano');
        var diretor = button.data('diretor');
        var classe = button.data('classe');
        var sinopse = button.data('sinopse');
        var elenco = button.data('elenco');
        var categoria = button.data('categoria');
  
        var modal = $(this);
        modal.find('.modal-title').text(''+ nomeTitulo);
        modal.find('#nomeTitulo').text(nomeTitulo);
        $('#exampleModal #imgTitulo').attr("src", pathImage);
        modal.find('#anoTitulo').text(ano);
        modal.find('#diretorTitulo').text(diretor);
        modal.find('#classeTitulo').text(classe);
        modal.find('#sinopse').text(sinopse);
        modal.find('#categoria').text(categoria);
        modal.find('#elenco').text(elenco);
        modal.find('#disp').text(modal.find('#disponibilidade').value);
});  
    </script>
    
</body>
    