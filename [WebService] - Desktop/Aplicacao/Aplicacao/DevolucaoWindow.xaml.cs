using Aplicacao.Domain;
using Aplicacao.Rest;
using GalaSoft.MvvmLight.Command;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace Aplicacao
{
    /// <summary>
    /// Lógica interna para DevolucaoWindow.xaml
    /// </summary>
    public partial class DevolucaoWindow : Window, INotifyPropertyChanged
    {
        private string m_dataDevolucao;

        public DevolucaoWindow()
        {
            InitializeComponent();
            DataContext = this;
        }

        private ICommand m_submitDevolucao;

        public ICommand SubmitDevolucaoCommand
        {
            get { return (m_submitDevolucao ?? (m_submitDevolucao = new RelayCommand(SubmitDevolucao))); }
        }

        public void SubmitDevolucao()
        {
            MessageBox.Show(Request.Instance.EfetuarDevolucao(ItemDevolucao.Id));
            Close();
        }

        private Item m_itemDevolucao;

        public Item ItemDevolucao
        {
            get { return m_itemDevolucao; }
            set
            {
                m_itemDevolucao = value;
                NotifyPropertyChanged("ItemDevolucao");
            }
        }

        public string DataDevolucaoPrevista
        {
            get { return m_dataDevolucao; }
        }

        public void IniciarItem(Item itemDevolucao)
        {
            ItemDevolucao = itemDevolucao;
            m_dataDevolucao = Request.Instance.VencimentoLocacao(itemDevolucao.Id);
        }

        public event PropertyChangedEventHandler PropertyChanged;

        private void NotifyPropertyChanged([CallerMemberName] string propertyName = "")
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
    }
}
