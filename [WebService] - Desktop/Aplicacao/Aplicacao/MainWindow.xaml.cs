using Aplicacao.Domain;
using Aplicacao.Rest;
using GalaSoft.MvvmLight.Command;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Drawing;
using System.Drawing.Drawing2D;
using System.Drawing.Imaging;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Aplicacao
{
    /// <summary>
    /// Interação lógica para MainWindow.xam
    /// </summary>
    public partial class MainWindow : Window
    {
        private CollectionViewSource m_itemsCollection;

        public MainWindow()
        {
            InitializeComponent();

            var Items = new ObservableCollection<Item>(Request.Instance.Items());

            m_itemsCollection = new CollectionViewSource
            {
                Source = Items
            };

            m_itemsCollection.Filter += FiltroCategoria;
            DataContext = this;
        }

        public ICollectionView CollectionItems
        {
            get { return m_itemsCollection.View; }
        }

        private void FiltroCategoria(object sender, FilterEventArgs e)
        {
            Item item = (Item)e.Item;

            // categoria
            if ((Categoria)Categorias.SelectedIndex == Categoria.TODOS)
                e.Accepted = true;
            else if (item.Categoria == (Categoria)Categorias.SelectedIndex)
                e.Accepted = true;
            else
                e.Accepted = false;

            // nome
            if (string.IsNullOrEmpty(Filtro.Text))
                return;
            
            if(!(item.Nome.ToUpper().Contains(Filtro.Text.ToUpper())))
                e.Accepted = false;
        }

        private void OnSearchClicked(object sender, EventArgs e)
        {
            m_itemsCollection?.View.Refresh();
        }

        private void OnSelectChange(object sender, SelectionChangedEventArgs e)
        {
            m_itemsCollection?.View.Refresh();
        }

        private ICommand m_openWindowLocacao;

        public ICommand AbrirLocacaoCommand
        {
            get { return (m_openWindowLocacao ?? (m_openWindowLocacao = new RelayCommand<int>((obj) => AbrirLocacao(obj)))); }
        }

        private void AbrirLocacao(int idItem)
        {
            var window = new LocarWindow
            {
                Owner = this
            };

            window.IniciarLocacao((m_itemsCollection.Source as ObservableCollection<Item>).FirstOrDefault(entry => entry.Id == idItem));
            window.ShowDialog();

            ReloadItens();
        }

        private ICommand m_openWindowDevolucao;

        public ICommand AbrirDevolucaoCommand
        {
            get { return (m_openWindowDevolucao ?? (m_openWindowDevolucao = new RelayCommand<int>((obj) => AbrirDevolucao(obj)))); }
        }

        private void AbrirDevolucao(int idItem)
        {
            var window = new DevolucaoWindow
            {
                Owner = this
            };

            window.IniciarItem((m_itemsCollection.Source as ObservableCollection<Item>).FirstOrDefault(entry => entry.Id == idItem));
            window.ShowDialog();

            ReloadItens();
        }

        private void ReloadItens()
        {
            (m_itemsCollection.Source as ObservableCollection<Item>).Clear();

            foreach (var item in Request.Instance.Items())
                (m_itemsCollection.Source as ObservableCollection<Item>).Add(item);
        }
    }
}
