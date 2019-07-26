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
    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-light border-bottom shadow-sm">
        <h5 class="my-0 mr-md-auto font-weight-normal text-dark">Locadora</h5>
        <nav class="my-2 my-md-0 mr-md-3 navbar-dark">
            <a class="p-2 text-dark" href="index.jsp">Home</a>
            <a class="p-2 text-dark " href="filmes.jsp">Títulos</a>
            <a class="p-2 text-dark active" href="precos.jsp">Preços</a>
        </nav>
    </div>

    <div class="netflix-background">
        <div class="container mb-5 mt-5">
            <h1 class="display-4 text-center text-white" id="inicioSite"><i class="far fa-money-bill-alt"></i> Nossos Preços</h1>

            <div class="pricing card-deck flex-column flex-md-row mb-3 my-5">

                <div class="card card-pricing text-center px-3 mb-4">
                    <span class="h6 w-60 mx-auto px-4 py-1 rounded-bottom bg-info text-white shadow-sm">Comum</span>
                    <div class="bg-transparent card-header pt-4 border-0">
                        <h1 class="h1 font-weight-normal text-info text-center mb-0" data-pricing-value="25">R$<span class="price">25</span><span class="h6 text-muted ml-2">/ por filme</span></h1>
                    </div>
                    <div class="card-body pt-0">
                        <ul class="list-unstyled mb-4">
                            <li>Ótima qualidade</li>
                            <li>Devolução em até duas semanas</li>
                            <li>Bom atendimento</li>
                        </ul>
                        <button type="button" class="btn btn-outline-secondary mb-3">Ver filmes</button>
                    </div>
                </div>

                <div class="card card-pricing popular shadow text-center px-3 mb-4">
                    <span class="h6 w-60 mx-auto px-4 py-1 rounded-bottom bg-primary text-white shadow-sm">Raro</span>
                    <div class="bg-transparent card-header pt-4 border-0">
                        <h1 class="h1 font-weight-normal text-primary text-center mb-0" data-pricing-value="30">R$<span class="price">30</span><span class="h6 text-muted ml-2">/ por filme</span></h1>
                    </div>
                    <div class="card-body pt-0">
                        <ul class="list-unstyled mb-4">
                            <li>Ótima qualidade</li>
                            <li>Devolução em até uma semana</li>
                            <li>Bom atendimento</li>
                        </ul>
                        <a href="#" target="_blank" class="btn btn-primary mb-3">Ver filmes</a>
                    </div>
                </div>

                <div class="card card-pricing text-center px-3 mb-4">
                    <span class="h6 w-60 mx-auto px-4 py-1 rounded-bottom bg-warning text-white shadow-sm">Lançamento</span>
                    <div class="bg-transparent card-header pt-4 border-0">
                        <h1 class="h1 font-weight-normal text-warning text-center mb-0" data-pricing-value="45">$<span class="price">45</span><span class="h6 text-muted ml-2">/ por filme</span></h1>
                    </div>
                    <div class="card-body pt-0">
                        <ul class="list-unstyled mb-4">
                            <li>Ótima qualidade</li>
                            <li>Devolução em até três dias</li>
                            <li>Bom atendimento</li>
                        </ul>
                        <button type="button" class="btn btn-outline-secondary mb-3">Ver filmes</button>
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
</body>