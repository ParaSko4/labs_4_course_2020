using InterfaceDLL;
using Newtonsoft.Json;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace JsonDataMethods
{
    public class JsonClass : IPhoneDictionary
    {
        private int maxId = 0;
        private string path = @"\\172.16.193.234\Apps\Shatrauka\3\user.json";

        public JsonClass() { }

        public JsonClass(string path)
        {
            this.path = path;
        }

        public List<Contacts> contacts { get; set; }

        public void Add(Contacts contact)
        {
            int v = ++maxId;
            contact.id = v;
            contacts.Add(contact);
        }

        public void Delete(int id)
        {
            var item = contacts.Find(x => x.id == id);
            contacts.Remove(item);
        }

        public void LoadData()
        {
            string json = File.ReadAllText(path, Encoding.Default);

            if (json.Length != 0)
            {
                contacts = JsonConvert.DeserializeObject<List<Contacts>>(json);
            }
            else
            {
                contacts = new List<Contacts>();
            }

            maxId = contacts.Max(x => x.id);
        }

        public void SaveChanges()
        {
            File.WriteAllText(path, JsonConvert.SerializeObject(contacts));
        }

        public void Update(Contacts contact)
        {
            var item = contacts.Find(x => x.id == contact.id);

            item.name = contact.name;
            item.number = contact.number;

            SaveChanges();
        }

        public Contacts Find(int id)
        {
            return contacts.Find(x => x.id == id);
        }
    }
}
