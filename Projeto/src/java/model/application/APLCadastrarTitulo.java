package model.application;

import dao.GenericDao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import model.domain.ReturnType;
import model.domain.Titulo;
import model.domain.Diretor;
import model.domain.Categoria;
import model.domain.Classe;
import model.domain.Item;
import model.domain.TipoItem;

public class APLCadastrarTitulo
{
    private static int page = 1;
    private static GenericDao<Titulo> m_daoInstance;
    
    private static GenericDao<Titulo> getDaoInstance()
    {
        if(m_daoInstance == null)
            m_daoInstance = new GenericDao<Titulo>();
        
        return m_daoInstance;
    }
    
    public static ReturnType deletarTitulo(String idTitulo)
    {
        if(idTitulo.equals(""))
          return ReturnType.ERRO;
        
        Long id = Long.parseLong(idTitulo);
        
        if(id == null)
            return ReturnType.ERRO;
        
        ReturnType typeVerificao = verificarExclusao(id);
        
        if(typeVerificao != ReturnType.SUCESSO)
            return typeVerificao;
        
        if(getDaoInstance().findById(Titulo.class, id) == null)
            return ReturnType.ERRO; 
        
        getDaoInstance().remove(Titulo.class, id);

        return ReturnType.SUCESSO;
    }
    
    public static ReturnType inserirTitulo(Long idTitulo, String nomeTitulo, String pathImage, int anoTitulo, ArrayList atores, Diretor diretor, String sinopse, Categoria categoria, Classe classe)
    {
        Titulo tituloInsercao = criarTitulo(idTitulo, nomeTitulo, pathImage, anoTitulo, atores, diretor, sinopse, categoria, classe);
        
        if(tituloInsercao == null)
            return ReturnType.ERRO;
        
        getDaoInstance().saveOrUpdate(tituloInsercao);
        
        return ReturnType.SUCESSO;
    }
    
    public static boolean verificarDisponibilidade(Titulo titulo)
    {
        return titulo.getItems().stream().anyMatch(x-> !x.getItemLocado());
    }
    
    private static ReturnType verificarExclusao(Long idTitulo)
    {
        List<Item> itens = APLCadastrarItem.findItems();
        
        for(Item i : itens)
        {
            if(Objects.equals(i.getTitulo().getId(), idTitulo))
                return ReturnType.TITULO_LINKADO;
        }
        
        return ReturnType.SUCESSO;
    }

    private static Titulo criarTitulo(Long idTitulo, String nomeTitulo, String pathImage, int anoTitulo, ArrayList atores, Diretor diretor, String sinopse, Categoria categoria, Classe classe)
    {
        return new Titulo(idTitulo, nomeTitulo,pathImage, anoTitulo, sinopse, categoria, classe, diretor, new HashSet<>(atores));
    }
    
    public static List<Titulo> findTitles()
    {
        return getDaoInstance().findQuery(Titulo.class);
    }
    
    public static Titulo findTitle(Long idTitulo)
    {
        return getDaoInstance().findById(Titulo.class, idTitulo);
    }
    
      public static List<Titulo> listaPaginacao(int pageid, int total)
    {
      return getDaoInstance().findPagination(pageid, total, Titulo.class);
    }

    public static int getPage() {
        return page;
    }

    public static void setPage(int page) {
        APLCadastrarTitulo.page = page;
    }
     

  
}
