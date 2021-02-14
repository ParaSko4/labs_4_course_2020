using pi_lab07c_client.ServiceReference;
using System;
using System.Collections.Generic;
using System.Net;
using System.Windows.Forms;

namespace pi_lab07c_client
{
    public partial class Form1 : Form
    {
        private ContactServiceClient client;
        private List<Contacts> contacts;

        int index = 0;

        public Form1()
        {
            InitializeComponent();
            client = new ContactServiceClient();
            LoadData();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Contacts contact = new Contacts(nameBox.Text, numberBox.Text);
            client.AddDict(contact);
            LoadData();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            client.UpdDict(contacts[index].idk__BackingField, contacts[index]);
            LoadData();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            client.DelDict(contacts[index].idk__BackingField);
            LoadData();
        }

        private void dataGridView1_CurrentCellChanged(object sender, EventArgs e)
        {
            if (dataGridView1.CurrentCell != null)
            {
                index = dataGridView1.CurrentCell.RowIndex;
            }
        }

        private void LoadData()
        {
            contacts = new List<Contacts>(client.GetDict());
            dataGridView1.DataSource = contacts;
        }
    }
}
