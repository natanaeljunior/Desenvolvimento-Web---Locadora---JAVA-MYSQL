<%-- 
    Document   : ator
    Created on : 24/03/2019, 13:22:39
    Author     : Isaquee
--%>

<%@page import="model.application.APLCadastrarAtor"%>
<%@page import="java.util.List"%>
<%@page import="model.domain.Ator"%>
<%@page import="model.domain.Ator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Painel Administrativo</title>

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
  
  <!-- Custom fonts for this template-->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet" type="text/css">

  <!-- Custom styles for this template-->
  <link href="css/sb-admin-2.min.css" rel="stylesheet" type="text/css">
  <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet" type="text/css">
  <link href="css/custom.css" rel="stylesheet" type="text/css">

</head>

<body id="page-top" onload="onLoadPage()">

  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.jsp">
        <div class="sidebar-brand-icon rotate-n-15">
          <img src="admin/img/film.svg"/>
        </div>
        <div class="sidebar-brand-text mx-3">Locadora<sup>ADMIN</sup></div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">
      <!-- Nav Item - Dashboard -->
      <li class="nav-item active">
        <a class="nav-link" href="admin/index.jsp">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Painel Administrativo</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">
      <!-- Heading -->
      <div class="sidebar-heading">
        Filme
      </div>
      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link" href="titulo.jsp" >
          <i class="fas fa-film"></i>
          <span>Título </span><span class="badge badge-dark right mx-3">57</span>
        </a>
      </li>
      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link selected-item-menu" href="ator.jsp" >
          <i class="far fa-user"></i>
          <span>Ator</span><span class="badge badge-dark right mx-3"><%=APLCadastrarAtor.findActors().size()%></span>
        </a>
      </li>
      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link" href="diretor.jsp" >
          <i class="fas fa-user-tie"></i>
          <span>Diretor</span><span class="badge badge-dark right mx-3">19</span>
        </a>
      </li>

      </li>
  <!-- Nav Item - Pages Collapse Menu -->
  <li class="nav-item">
    <a class="nav-link" href="classe.jsp" >
      <i class="fas fa-compact-disc"></i>
      <span>Classe</span><span class="badge badge-dark mx-3 ">3</span>
    </a>
  </li>
 <!-- Nav Item - Pages Collapse Menu -->
 <li class="nav-item">
  <a class="nav-link" href="item.jsp" >
    <i class="fas fa-compact-disc"></i>
    <span>Item</span><span class="badge badge-dark mx-3 ">3</span>
  </a>
</li>
 <!-- Divider -->
 <hr class="sidebar-divider my-0">
 <!-- Nav Item - Pages Collapse Menu -->
 <li class="nav-item">
   <a class="nav-link" href="cliente.jsp" >
     <i class="fas fa-compact-disc"></i>
     <span>Cliente</span><span class="badge badge-dark mx-3 ">3</span>
   </a>
 </li>
 <li class="nav-item">
  <a class="nav-link" href="locacao.jsp" >
    <i class="fas fa-compact-disc"></i>
    <span>Locação</span><span class="badge badge-dark mx-3 ">3</span>
  </a>
</li>
<li class="nav-item">
  <a class="nav-link" href="devolucao.jsp" >
    <i class="fas fa-compact-disc"></i>
    <span>Devolução</span><span class="badge badge-dark mx-3 ">3</span>
  </a>
</li>

      <!-- Divider -->
      <hr class="sidebar-divider d-none d-md-block">

      <!-- Sidebar Toggler (Sidebar) -->
      <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>

    </ul>
    <!-- End of Sidebar -->


<div id="content-wrapper" class="d-flex flex-column">
  <div id="content">
      <!-- Topbar -->
      <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

        <!-- Sidebar Toggle (Topbar) -->
        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
          <i class="fa fa-bars"></i>
        </button>

        <!-- Topbar Search -->
        <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
          <div class="input-group">
            <input type="text" class="form-control bg-light border-0 small" placeholder="Pesquisar por ..." aria-label="Search" aria-describedby="basic-addon2">
            <div class="input-group-append">
              <button class="btn btn-primary" type="button">
                <i class="fas fa-search fa-sm"></i>
              </button>
            </div>
          </div>
        </form>

        <!-- Topbar Navbar -->
        <ul class="navbar-nav ml-auto">

          <!-- Nav Item - Search Dropdown (Visible Only XS) -->
          <li class="nav-item dropdown no-arrow d-sm-none">
            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <i class="fas fa-search fa-fw"></i>
            </a>
            <!-- Dropdown - Messages -->
            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
              <form class="form-inline mr-auto w-100 navbar-search">
                <div class="input-group">
                  <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                  <div class="input-group-append">
                    <button class="btn btn-primary" type="button">
                      <i class="fas fa-search fa-sm"></i>
                    </button>
                  </div>
                </div>
              </form>
            </div>
          </li>

          <!-- Nav Item - User Information -->
          <li class="nav-item dropdown no-arrow">
            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <span class="mr-2 d-none d-lg-inline text-gray-600 small">Funcionario 01</span>
              <img class="img-profile rounded-circle" src="https://source.unsplash.com/QAB-WJcbgJk/60x60">
            </a>
            <!-- Dropdown - User Information -->
            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
              <a class="dropdown-item" href="#">
                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                Perfil
              </a>
              <div class="dropdown-divider"></div>
              <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                Sair
              </a>
            </div>
          </li>

        </ul>

      </nav>
      <!-- End of Topbar -->
  
<div class="container-fluid">


  <div class="card shadow mb-4 " >

    <div class="card-header py-3">
      <h6 class="m-0 font-weight-bold text-primary">
        Atores
        
        <a class=" btn btn-success ml-1" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
          <i class="fas fa-plus"></i>
        </a>
      </h6>
    </div>

    <div class="collapse" id="collapseExample">
        <form data-toggle="validator" action="/Projeto/SRVLCadastrarAtor">
            <input type="hidden" name="operacao" value="inserirator">
            
            <div class="form row justify-content-center">
              <input type="text" name="nome" data-error="Por favor, informe um e-mail correto." required class="form-control form-control-user  col-3 my-2 mx-2" id="exampleInputEmail" placeholder="Nome Ator ">
            </div>
            
            <div class="form row justify-content-center">
                <button type="submit" class="btn btn-success col-3 my-2 " style="padding:5px; margin-bottom: 15px">Cadastrar</button>
            </div>
        
        </form>
    </div>

    <div class="card-body">
      <div class="table-responsive">
          
        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
          <thead>
            <tr>
              <th>Nome</th>
              <th>CRUD</th>
            </tr>
          </thead>
   
          <tbody>
              
            <%
                List<Ator> currentActors = APLCadastrarAtor.findActors();
                
                for(Ator actor : currentActors)
                {
                    out.print("<tr>");
                            
                    // Nome do ator
                    out.print("<td>");
                        out.print(actor.getNomeAtor());
                    out.print("</td>");
                    
                   // Botões C.R.U.D
                   out.print("<td>");
                       out.print("<div class='btn-group' role='group'>");
                       out.print("<button type='button' class='btn btn-warning' data-toggle='modal' data-target='#alterarModal' "
                               + "data-id='"+actor.getId()+"' "
                               + "data-nome='"+actor.getNomeAtor()+"'"
                               + "><i class='far fa-edit'></i></button>");
                          out.print("<button type='button' class='btn btn-danger' data-toggle='modal' data-target='#excluirModal' onclick='setDados(" + actor.getId() + ")'><i class='fas fa-trash-alt'></i></button>");
                       out.print("</div>");
                    out.print("</td>");                 
                   out.print("</tr>");
                }
            %>
            
          </tbody>
        </table>
          
      </div>
    </div>
  </div>
</div>
  </div>

  <footer class="sticky-footer bg-white">
    <div class="container my-auto">
      <div class="copyright text-center my-auto">
        <span>Copyright &copy; Desenvolvimento WEB 2019</span>
      </div>
    </div>
  </footer>
</div>

</div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

  <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Tem certeza que quer sair?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
          <a class="btn btn-primary" href="${pageContext.request.contextPath}/SRVLLoginUsuario?funcaoSair=1" onclick="">Sair</a>
        </div>
      </div>
    </div>
  </div>

  <!-- Logout Confirmacao Excluir-->
  <div class="modal fade" id="excluirModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Tem certeza que quer Exluir Ator?</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
            <a class="btn btn-primary" id="campo" >Excluir</a>
          </div>
        </div>
      </div>
    </div>

    <!-- Logout Confirmacao Alterar-->
  <div class="modal fade" id="alterarModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Alterar Ator</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <form data-toggle="validator" action="/Projeto/SRVLCadastrarAtor">
            <input type="hidden" name="operacao" value="alterarAtor">
            <input id="idAtor" type="hidden" name="id" >
              <div class="form row justify-content-center">
                <input name="nome" id="altNomeAtor" type="text"  style="padding:5px; margin-bottom: 15px;" required class="form-control form-control-user col-8 my-2 mx-2" >
                <button type="submit" class="btn btn-warning col-3 my-2 " style="padding:5px; margin-bottom: 15px;"><i class="fas fa-edit"></i>Alterar</button>
              </div>
              </form>
          <div class="modal-footer " >
          
          </div>
        </div>
      </div>
    </div>

  <!-- Bootstrap core JavaScript-->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <script src="vendor/datatables/jquery.dataTables.min.js"></script>
  <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="js/demo/datatables-demo.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.js"></script>

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
      
      function setDados(id)
      {
          document.getElementById('campo').href = "${pageContext.request.contextPath}/SRVLCadastrarAtor?operacao=removerAtor&id=" + id;
      }
     
         $('#alterarModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var id = button.data('id'); // Extract info from data-* attributes
        var nomeAtor = button.data('nome');
  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this);
        modal.find('.modal-title').text('Ator de Id: '+id);
        modal.find('#idAtor').val(id);
        modal.find('#altNomeAtor').val(nomeAtor);
});
  </script>
</body>

</html>
