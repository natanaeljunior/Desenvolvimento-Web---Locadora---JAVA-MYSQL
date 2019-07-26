<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link href="css/custom.css" rel="stylesheet">
        
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <title>Login</title>
        
        
        <!-- Variáveis de controle do reload -->
        <% 
          boolean isReloadFunction = false;
          String messageReload = "";
          String typeFunctionReload = "";

          if(session.getAttribute("isReloadFunction") != null)
              isReloadFunction = (Boolean)session.getAttribute("isReloadFunction");

          if(session.getAttribute("messageReload") != null)
              messageReload = (String)session.getAttribute("messageReload");

          if(session.getAttribute("typeFunctionReload") != null)
              typeFunctionReload = (String)session.getAttribute("typeFunctionReload");
        %>
    </head>

    <body onload="onLoadPage()">
        <section class="netflix-background d-flex h-100">
            <div class="container my-auto">
                <div class="row">

                        <div class="col-md-4 login-sec my-4">
                            <h2 class="text-center">Entre agora</h2>

                            <form class="login-form" action="/Projeto/SRVLLoginUsuario">

                                <div class="form-group">
                                    <input type="text" name="email" class="form-control" required>
                                    <label for="exampleInputEmail1" class="form-control-placeholder" for="name">Email</label>
                                </div>
                                
                                <div class="form-group">
                                    <input type="password" name="senha" class="form-control" required>
                                    <label for="exampleInputPassword1" class="form-control-placeholder" for="name">Senha</label>
                                </div>
                                
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input type="checkbox" class="form-check-input">
                                        <small>Lembrar-me</small>
                                    </label>

                                    <button type="submit" class="btn btn-login float-right">Acessar</button>
                                </div>

                            </form>

                            <div class="copy-text">Seja bem-vindo(a)! <i class="fa fa-heart"></i></div>
                        </div>

                        <!-- Imagem / Texto -->
                        <div class="col-md-8 banner-sec">
                            <div class="banner-text">
                                <h2>Administração Locadora</h2>
                                <p>Acesse o painel de administração com as credenciais de um funcionário. Com isso você poderá gerenciar todo o conteúdo disponível no site.</p>
                            </div>	
                        </div>
                </div>
            </div>
        </section>
        
        <!-- Bootstrap notify -->
        <script src="js/bootstrap-notify.js"></script>
        <script src="js/bootstrap-notify.min.js"></script>
        
        <script>
            function onLoadPage()
            {
              if("<%=isReloadFunction%>" === "true")
              {
                   $.notify({
                      message: "<%=messageReload%>",
                  },
                  {
                      offset: 50,
                      delay: 2000,
                      type: "<%=typeFunctionReload%>",
                      allow_dismiss: true,
                      newest_on_top: true
                  });

                  <% session.setAttribute("isReloadFunction", false); %>
              }
            }
        </script>
    </body>
</html>
