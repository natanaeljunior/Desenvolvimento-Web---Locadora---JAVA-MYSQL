using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Media.Imaging;

namespace Aplicacao.Domain
{
    public class Item : BaseModel
    {
        public Item()
        {
        }

        public Item(int id, string nome, int ano, string imagem, bool locado, Categoria categoria, Classe classeItem)
        {
            Id = id;
            Nome = nome;
            Ano = ano;
            Imagem = imagem;
            BitmapImagem = ImageHelper.ResizeImage(Imagem, 168, 250);
            Categoria = categoria;
            ClasseItem = classeItem;
            Locado = locado;
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

        private bool m_itemLocado;

        public bool Locado
        {
            get { return m_itemLocado; }
            set
            {
                m_itemLocado = value;
                NotifyPropertyChanged("Locado");
            }
        }

        public bool Devolvido
        {
            get { return !m_itemLocado; }
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

        private int m_ano;

        public int Ano
        {
            get { return m_ano; }
            set
            {
                m_ano = value;
                NotifyPropertyChanged("Ano");
            }
        }

        private string m_imagem;

        public string Imagem
        {
            get { return m_imagem; }
            set
            {
                m_imagem = value;
                NotifyPropertyChanged("Imagem");
            }
        }

        private BitmapImage m_bitmapImage;

        public BitmapImage BitmapImagem
        {
            get { return m_bitmapImage; }
            set
            {
                m_bitmapImage = value;
                NotifyPropertyChanged("BitmapImagem");
            }
        }

        private Categoria m_categoria;

        public Categoria Categoria
        {
            get { return m_categoria; }
            set
            {
                m_categoria = value;
                NotifyPropertyChanged("Categoria");
            }
        }

        private Classe m_classeItem;

        public Classe ClasseItem
        {
            get { return m_classeItem; }
            set
            {
                m_classeItem = value;
                NotifyPropertyChanged("ClasseItem");
            }
        }

        public string Exibicao
        {
            get { return Nome + " (" + Ano.ToString() + ")"; }
        }
    }
}
