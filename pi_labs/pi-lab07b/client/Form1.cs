using client.WS;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Runtime.Serialization.Formatters.Soap;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Xml;
using System.Xml.Serialization;

namespace client
{
    public partial class Form1 : Form
    {
        WS.ContactsServiceSoapClient client;
        private List<Contacts> contacts;

        int index = 0;

        public Form1()
        {
            InitializeComponent();
            client = new WS.ContactsServiceSoapClient();
            LoadData();
        }

        private async void button1_ClickAsync(object sender, EventArgs e)
        {
            WS.Contacts contact = new WS.Contacts(nameBox.Text, numberBox.Text);
            client.AddDict(contact);
            LoadData();
        }

        private void updatebutton_Click(object sender, EventArgs e)
        {
            client.UpdDict(contacts[index].id, contacts[index]);
            LoadData();
        }

        private void deleteButton_Click(object sender, EventArgs e)
        {
            client.DelDict(contacts[index].id);
            LoadData();
        }

        private void LoadData()
        {
            contacts = new List<Contacts>(client.GetDict());
            dataGridView1.DataSource = contacts;
        }

        private void dataGridView1_CurrentCellChanged(object sender, EventArgs e)
        {
            if (dataGridView1.CurrentCell != null)
            {
                index = dataGridView1.CurrentCell.RowIndex;
            }
        }
    }
}
