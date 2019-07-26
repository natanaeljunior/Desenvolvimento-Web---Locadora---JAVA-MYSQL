<!doctype html>
<html lang="pt-br">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="css/trabalho.css">
    <link rel="stylesheet" href="css/header.css">

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
          <a class="p-2 text-dark active" href="index.jsp">Home</a>
          <a class="p-2 text-dark " href="filmes.jsp">Títulos</a>
          <a class="p-2 text-dark" href="precos.jsp">Preços</a>
      </nav>
    </div>
      
    <!-- Vídeo header -->
    <header>
      <div class="overlay"></div>
      <video playsinline="playsinline" autoplay="autoplay" preload="none" muted="muted" loop="loop">
        <source src="img/header-video.webm" type="video/webm">
      </video>
      <div class="container h-100">
        <div class="d-flex h-100 text-center align-items-center">
          <div class="w-100 text-white">
            <h1 class="display-3">Seja bem-vindo</h1>
            <p class="lead mb-0">Locadora de filmes desenvolvida para a disciplina de Desenvolvimento Web</p>
            <a  href="filmes.jsp" class="btn btn-danger mt-3" role="button" style="opacity: 0.8" >Locar filmes »</a>
          </div>
        </div>
      </div>
    </header>

    <!-- Conteúdo do site -->
    <div class="netflix-background">
      <div class="container">
        
        <div class="row">
          <div class="col-12 text-center my-5">

            <h1 class="display-3 text-white" id="inicioSite"><i class="far fa-flag"></i> Saiba mais</h1>
            <p class="text-white">Fique por dentro dos últimos filmes adicionados para a locação e aproveite nossas promoções. Veja abaixo o conteúdo disponível no site.</p>

          </div>
        </div>

        <!-- Informações iniciais -->
        <div class="row marketing">
          <div class="col-lg-4">
            <div class="round-div mx-auto" style="background-image: url(img/diretor-carousel.jpg)"></div>
            <h2 class="mt-4 text-white">Diretores</h2>
            <p class="text-white">Acompanhe todos os diretores cadastrados e veja suas melhores produções.</p>
            <p><a class="btn btn-secondary mt-5" href="#" role="button">Veja mais »</a></p>
          </div>
          <div class="col-lg-4">
            <div class="round-div mx-auto" style="background-image: url(img/ator-carousel.jpg)"></div>
            <h2 class="mt-4 text-white">Atores</h2>
            <p class="text-white">Saiba mais sobre as melhores atuações dos filmes, conheça os nomes de seus atores preferidos.</p>
            <p><a class="btn btn-secondary mt-4" href="#" role="button">Veja mais »</a></p>
          </div>
          <div class="col-lg-4">
            <div class="round-div mx-auto" style="background-image: url(img/filme-carousel.png)"></div>
            <h2 class="mt-4 text-white">Títulos</h2>
            <p class="text-white">Veja quais são os filmes disponíveis para locação, além de visualizar seus trailers. Aproveite também nossas promoções para locação.</p>
            <p><a class="btn btn-secondary" href="#" role="button">Veja mais »</a></p>
          </div>
        </div>
        
        <hr>

        <!-- Cards -->
        <h1 class="text-center my-5 display-4 text-white"><i class="fas fa-play"></i> Últimos filmes</h1>
        <div class="row justify-content-sm-center">
          <div class="col-sm-6 col-md-3">
            <div class="card">
              <img class="card-img-top" src="img/card-piratas-do-caribe.jpg">
              <div class="card-body">
                <h5 class="class-title">Piratas do Caribe : A Vingança de Salazar</h5>
                <p class="card-text">O capitão Salazar é a nova pedra no sapato do capitão Jack Sparrow...</p>
                <a class="btn btn-primary video-btn" href="#" data-toggle="modal" data-src="https://www.youtube.com/embed/H8d1pD49JOk" data-target="#modalVideo">Ver Trailer</a>
              </div>
            </div>
          </div>

          <div class="col-sm-6 col-md-3">
            <div class="card">
              <img class="card-img-top" src="img/card-onda.jpg">
              <div class="card-body">
                <h5 class="class-title">A 5ª Onda</h5>
                <p class="card-text">Uma série de ataques alienígenas ameaça a raça humana. Separada da família, uma adolescente...</p>
                <a class="btn btn-primary video-btn" href="#" data-toggle="modal" data-src="https://www.youtube.com/embed/BxfyCAMWgGQ" data-target="#modalVideo">Ver Trailer</a>
              </div>
            </div>
          </div>

          <div class="col-sm-6 col-md-3">
            <div class="card">
              <img class="card-img-top" src="img/card-alita-anjo.jpg">
              <div class="card-body">
                <h5 class="class-title">Alita: Anjo de Combate</h5>
                <p class="card-text">Uma ciborgue é descoberta por um cientista. Ela não tem memórias de sua criação, mas possui... </p>
                <a class="btn btn-primary video-btn" href="#" data-toggle="modal" data-src="https://www.youtube.com/embed/UgrCecj-XNU" data-target="#modalVideo">Ver Trailer</a>
              </div>
            </div>
          </div>

          <div class="col-sm-6 col-md-3">
              <div class="card">
                <img class="card-img-top" src="img/card-aquaman.jpg">
                <div class="card-body">
                  <h5 class="class-title">Aquaman</h5>
                  <p class="card-text">Aquaman aprende que não pode fazer tudo sozinho quando começa uma jornada com Mera em busca...</p>
                  <a class="btn btn-primary video-btn" href="#" data-toggle="modal" data-src="https://www.youtube.com/embed/90bG43JcwRE" data-target="#modalVideo">Ver Trailer</a>
                </div>
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

    <!-- Modal -->
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

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>