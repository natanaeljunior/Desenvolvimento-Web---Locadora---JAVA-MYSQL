using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Aplicacao.Domain
{
    public class Cliente : BaseModel
    {
        public Cliente()
        {
        }

        public Cliente(int id, string nome)
        {
            Id = id;
            Nome = nome;
        }

        private int m_id;

        public int Id
        {
            get { return m_id; }
            set
            {
                m_id = value;
                NotifyPropertyChanged("Id");
            }
        }

        private string m_nome;

        public string Nome
        {
            get { return m_nome; }
            set
            {
                m_nome = value;
                NotifyPropertyChanged("Nome");
            }
        }
    }
}
