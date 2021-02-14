using InterfaceDLL;
using SQLiteDataMethods.Context;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;

namespace SQLiteDataMethods
{
    public class SQLiteClass : IPhoneDictionary
    {
        private ContactContext db;

        public SQLiteClass()
        {
            db = new ContactContext();
        }

        public List<Contacts> contacts
        { 
            get
            {
                return db.contacts.ToList();
            }
        }

        public void Add(Contacts contact)
        {
            db.contacts.Add(contact);
        }

        public void Delete(int id)
        {
            db.contacts.Remove(db.contacts.Find(id));
        }

        public Contacts Find(int id)
        {
            return db.contacts.Find(id);
        }

        public void LoadData()
        {
            db.contacts.Load();
        }

        public void SaveChanges()
        {
            db.SaveChanges();
        }

        public void Update(Contacts contact)
        {
            var upContact = db.contacts.Find(contact.id);

            upContact.name = contact.name;
            upContact.number = contact.number;

            SaveChanges();
        }
    }
}
