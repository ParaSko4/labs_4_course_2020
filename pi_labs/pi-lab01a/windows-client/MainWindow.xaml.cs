using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
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
using System.Text.Json;

namespace windows_client
{
    /// <summary>
    /// Логика взаимодействия для MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {

        class ReciveData
        {
            public int x { get; set; }
            public int y { get; set; }
        }

        public MainWindow()
        {
            InitializeComponent();
        }

        async Task RunAsync(Uri uri, HttpContent json)
        {
            HttpClient client = new HttpClient();
            var getResult = client.PostAsync(uri, json);
            MessageBox.Show(await getResult.Result.Content.ReadAsStringAsync());
        }

        async private void Button_Click(object sender, RoutedEventArgs e)
        {
            Uri uri = new Uri("http://localhost:44321/shv/postxy");
            HttpContent content = new StringContent(JsonSerializer.Serialize(new ReciveData { x = Int32.Parse(val1.Text), y = Int32.Parse(val2.Text) }), Encoding.UTF8, "application/json");

            await RunAsync(uri, content);
        }
    }
}
