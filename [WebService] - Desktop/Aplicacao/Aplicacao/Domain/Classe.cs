using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Aplicacao.Domain
{
    public class Classe : BaseModel
    {
        public Classe(int id, int diasDevolucao, int valor)
        {
            Id = id;
            Valor = valor;
            Devolucao = diasDevolucao;
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

        private int m_devolucao;

        public int Devolucao
        {
            get { return m_devolucao; }
            set
            {
                m_devolucao = value;
                NotifyPropertyChanged("Devolucao");
            }
        }

        private DateTime m_vencimento;

        public DateTime Vencimento
        {
            get { return m_vencimento; }
            set
            {
                m_vencimento = value;
                NotifyPropertyChanged("Vencimento");
            }
        }

        private int m_valor;

        public int Valor
        {
            get { return m_valor; }
            set
            {
                m_valor = value;
                NotifyPropertyChanged("Valor");
            }
        }
    }
}
