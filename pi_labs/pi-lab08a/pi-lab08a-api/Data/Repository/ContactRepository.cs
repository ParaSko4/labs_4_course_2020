using Microsoft.EntityFrameworkCore;
using pi_lab08a_api.Data.Repository.Interfaces;
using pi_lab08a_api.Models;
using System;
using System.Collections.Generic;
using System.Linq;

namespace pi_lab08a_api.Data.Repository
{
    public class ContactRepository : IRepositoryBase<ContactModel>
    {
        private ContactContext db;

        public ContactRepository()
        {
            db = new ContactContext();
        }

        public void Add(ContactModel item)
        {
            db.contacts.Add(item);
            db.SaveChanges();
        }

        public void Delete(int id)
        {
            db.contacts.Remove(db.contacts.Find(id));
            db.SaveChanges();
        }

        public ContactModel Get(int id)
        {
            return db.contacts.Find(id);
        }

        public IEnumerable<ContactModel> GetAll()
        {
            return db.contacts.ToList();
        }

        public void Update(int id, ContactModel item)
        {
            if (db.contacts.Any(o => o.id == id))
            {
                item.id = id;
                db.Entry(item).State = EntityState.Modified;
                db.SaveChanges();
            }
        }

        private bool disposed = false;

        public virtual void Dispose(bool disposing)
        {
            if (!this.disposed)
            {
                if (disposing)
                {
                    db.Dispose();
                }
                this.disposed = true;
            }
        }

        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }
    }
}
