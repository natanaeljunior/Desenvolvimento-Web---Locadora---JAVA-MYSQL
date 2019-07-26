package model.application;

import dao.GenericDao;
import java.util.List;
import model.domain.ReturnType;
import model.domain.Usuario;

public class APLLoginUsuario
{
    private static GenericDao<Usuario> m_daoInstance;
    
    private static GenericDao<Usuario> getDaoInstance()
    {
        if(m_daoInstance == null)
            m_daoInstance = new GenericDao<Usuario>();
        
        return m_daoInstance;
    }
    
    public static ReturnType verificarUsuario(String emailUsuario, String senhaUsuario)
    {
        String sql = "FROM Usuario user WHERE user.login = '" + emailUsuario + "' AND user.senha = '" + senhaUsuario+"'";
        List<Usuario> usuarioList = getDaoInstance().findQuery(sql, Usuario.class);
        
        if(usuarioList.size() > 0)
            return ReturnType.SUCESSO;
        else
            return ReturnType.ERRO;
    }
}
