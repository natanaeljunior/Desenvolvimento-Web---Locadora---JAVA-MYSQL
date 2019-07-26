using Aplicacao.Domain;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
using System;
using System.Collections.Generic;
using System.Dynamic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace Aplicacao.Rest
{
    public class Request
    {
        private string m_requestUrl = @"http://localhost:8080/Projeto/api/request/";
        private string m_imageUrl = @"http://localhost:8080/Projeto/";

        private Request()
        {
        }

        private HttpWebRequest WebRequestInfo
        {
            get;
            set;
        }

        private static Request m_requestInstance;

        public static Request Instance
        {
            get { return m_requestInstance ?? (m_requestInstance = new Request()); }
            set { m_requestInstance = value; }
        }

        /// <summary>
        /// Cria uma lista de itens dinâmicamente com base no retorno do json
        /// </summary>
        /// <returns></returns>
        public List<Item> Items()
        {
            WebRequestInfo = WebRequest.CreateHttp(m_requestUrl + @"titulos");
            WebRequestInfo.Method = "GET";

            string resultadoRequest = Response();
            var itens = new List<Item>();

            if (!resultadoRequest.Equals("404"))
            {
                dynamic listaJson = JsonConvert.DeserializeObject<List<ExpandoObject>>(resultadoRequest, new ExpandoObjectConverter());

                foreach (var @object in listaJson)
                {
                    // cria a classe do item
                    Classe classeItem = new Classe((int)@object.titulo.classe.id, (int)@object.titulo.classe.dataClasse, (int)@object.titulo.classe.valorClasse);

                    // obtém a categoria (string -> enum)
                    Categoria categoriaItem;
                    Enum.TryParse(@object.tipoItem, out categoriaItem);

                    itens.Add(new Item((int)@object.idItem,
                                       (string)@object.titulo.nome,
                                       (int)@object.titulo.ano,
                                       m_imageUrl + (string)@object.titulo.imagem,
                                       (bool)@object.itemLocado,
                                       categoriaItem,
                                       classeItem));
                }
            }

            return itens;
        }

        /// <summary>
        /// Cria uma lista de clientes dinâmicamente com base no retorno do json
        /// </summary>
        /// <returns></returns>
        public List<Cliente> Clientes()
        {
            WebRequestInfo = WebRequest.CreateHttp(m_requestUrl + @"clientes");
            WebRequestInfo.Method = "GET";

            string resultadoRequest = Response();
            var clientes = new List<Cliente>();

            if (!resultadoRequest.Equals("404"))
            {
                dynamic listaJson = JsonConvert.DeserializeObject<List<ExpandoObject>>(resultadoRequest, new ExpandoObjectConverter());

                foreach (var @object in listaJson)
                    clientes.Add(new Cliente((int)@object.id, (string)@object.nome));
            }

            return clientes;
        }

        /// <summary>
        /// Obtém a data de devolução de algum item
        /// </summary>
        /// <param name="idItem"></param>
        /// <returns></returns>
        public string VencimentoLocacao(int idItem)
        {
            WebRequestInfo = (HttpWebRequest)WebRequest.Create(m_requestUrl + "informacoesDevolucao");
            WebRequestInfo.ContentType = "application/json";
            WebRequestInfo.Method = "POST";

            using (var streamWriter = new StreamWriter(WebRequestInfo.GetRequestStream()))
            {
                string json = "{\"idItem\":" + idItem + " }";

                streamWriter.Write(json);
                streamWriter.Flush();
                streamWriter.Close();
            }

            string resultadoRequest = Response();

            if (!resultadoRequest.Equals("404"))
            {
                dynamic objetoJson = JsonConvert.DeserializeObject<ExpandoObject>(resultadoRequest, new ExpandoObjectConverter());
                return (string)objetoJson.dtDevolucaoPrevista;
            }

            else
                return resultadoRequest;
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="locacaoObject"></param>
        public string EfetuarLocacao(Locacao locacaoObject)
        {
            WebRequestInfo = (HttpWebRequest)WebRequest.Create(m_requestUrl + "locacao");
            WebRequestInfo.ContentType = "application/json";
            WebRequestInfo.Method = "POST";

            using (var streamWriter = new StreamWriter(WebRequestInfo.GetRequestStream()))
            {
                string json = "{ ";

                // locação
                json += "\"dataLocacao\":" + "\"" + DateTime.Now.ToString("yyyy/MM/dd") + "\"" + ",";

                // devolução
                json += "\"dataDevolucao\":" + "\"" + locacaoObject.Vencimento.ToString("yyyy/MM/dd") + "\"" + ",";

                // valor
                json += "\"valor\":" + locacaoObject.Valor + ",";

                // cliente
                json += "\"idCliente\":" + locacaoObject.LocacaoCliente.Id + ",";

                // item
                json += "\"idItem\":" + locacaoObject.LocacaoItem.Id + " }";

                streamWriter.Write(json);
                streamWriter.Flush();
                streamWriter.Close();
            }

            return Response();
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="idItem"></param>
        public string EfetuarDevolucao(int idItem)
        {
            WebRequestInfo = (HttpWebRequest)WebRequest.Create(m_requestUrl + "devolucao");
            WebRequestInfo.ContentType = "application/json";
            WebRequestInfo.Method = "POST";

            using (var streamWriter = new StreamWriter(WebRequestInfo.GetRequestStream()))
            {
                string json = "{\"idItem\":" + idItem + " }";

                streamWriter.Write(json);
                streamWriter.Flush();
                streamWriter.Close();
            }

            return Response();
        }

        private string Response()
        {
            try
            {
                using (var response = WebRequestInfo.GetResponse())
                {
                    return new StreamReader(response.GetResponseStream()).ReadToEnd().ToString();
                }
            }

            catch (Exception ex)
            {
                return "404";
            }
        }
    }
}
