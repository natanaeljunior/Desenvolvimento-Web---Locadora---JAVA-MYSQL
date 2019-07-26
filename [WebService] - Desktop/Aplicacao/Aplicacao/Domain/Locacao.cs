using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Aplicacao.Domain
{
    public class Locacao : BaseModel
    {
        public Locacao()
        {
        }

        public Locacao(Item locacaoItem)
        {
            LocacaoItem = locacaoItem;
            Vencimento = DateTime.Today.AddDays(LocacaoItem.ClasseItem.Devolucao);
            Valor = locacaoItem.ClasseItem.Valor;
        }

        private Item m_locacaoItem;

        public Item LocacaoItem
        {
            get { return m_locacaoItem; }
            set
            {
                m_locacaoItem = value;
                NotifyPropertyChanged("LocacaoItem");
            }
        }

        private Cliente m_locacaoCliente;

        public Cliente LocacaoCliente
        {
            get { return m_locacaoCliente; }
            set
            {
                m_locacaoCliente = value;
                NotifyPropertyChanged("LocacaoCliente");
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
