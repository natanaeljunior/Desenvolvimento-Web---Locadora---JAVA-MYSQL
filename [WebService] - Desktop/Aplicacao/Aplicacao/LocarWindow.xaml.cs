using Aplicacao.Domain;
using Aplicacao.Rest;
using Aplicacao.Validator;
using GalaSoft.MvvmLight.Command;
using System;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Runtime.CompilerServices;
using System.Text.RegularExpressions;
using System.Windows;
using System.Windows.Input;

namespace Aplicacao
{
    /// <summary>
    /// Lógica interna para LocarWindow.xaml
    /// </summary>
    public partial class LocarWindow : Window, INotifyPropertyChanged
    {
        public LocarWindow()
        {
            InitializeComponent();

            ObjetoLocacao = new Locacao();
            Clientes = new ObservableCollection<Cliente>(Request.Instance.Clientes());

            DataContext = this;
        }

        public void IniciarLocacao(Item item)
        {
            ObjetoLocacao = new Locacao(item);
        }

        private Locacao m_locacao;

        public Locacao ObjetoLocacao
        {
            get { return m_locacao; }
            set
            {
                m_locacao = value;
                NotifyPropertyChanged("ObjetoLocacao");
            }
        }

        public ObservableCollection<Cliente> Clientes
        {
            get;
            set;
        }

        private ICommand m_submitLocacao;

        public ICommand SubmitLocacaoCommand
        {
            get { return (m_submitLocacao ?? (m_submitLocacao = new RelayCommand(SubmitLocacao))); }
        }

        public void SubmitLocacao()
        {
            MessageBox.Show(Request.Instance.EfetuarLocacao(ObjetoLocacao));
            Close();
        }

        public event PropertyChangedEventHandler PropertyChanged;

        private void NotifyPropertyChanged([CallerMemberName] string propertyName = "")
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }

        private void NumberValidation(object sender, TextCompositionEventArgs e)
        {
            Regex validation = new Regex("[^0-9]+");

            e.Handled = validation.IsMatch(e.Text);
        }
    }
}
