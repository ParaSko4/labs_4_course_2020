using pi_lab07c.Contexts;
using pi_lab07c.Models;
using System.Collections.Generic;
using System.Data.Entity;
using System.Data.Entity.Migrations;
using System.Diagnostics;
using System.Linq;

namespace pi_lab07c
{
    public class ContactService : IContactService
    {
        ContactContext db = new ContactContext();

        public void AddDict(Contacts contact)
        {
            db.contacts.Add(contact);
            db.SaveChanges();
        }

        public void DelDict(int id)
        {
            db.contacts.Remove(db.contacts.Find(id));
            db.SaveChanges();
        }

        public List<Contacts> GetDict()
        {
            return db.contacts.AsNoTracking().ToList();
        }

        public void UpdDict(int id, Contacts contact)
        {
            if (db.contacts.Find(id) != null)
            {
                Debug.WriteLine(contact.id + " " + contact.name + " " + contact.number);
                db.contacts.AddOrUpdate(contact);
                //db.Entry(contact).State = EntityState.Modified;
                db.SaveChanges();
            }
        }
    }
}
