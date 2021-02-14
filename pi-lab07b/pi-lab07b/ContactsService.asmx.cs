using pi_lab07b.Contexts;
using pi_lab07b.Models;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web.Services;

namespace pi_lab07b
{
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    public class ContactsService : WebService
    {
        private ContactContext db = new ContactContext();

        [WebMethod(EnableSession = true)]
        public List<Contacts> GetDict()
        {
            return db.contacts.ToList();
        }

        [WebMethod(EnableSession = true)]
        public void AddDict(Contacts contact)
        {
            db.contacts.Add(contact);
            db.SaveChanges();
        }

        [WebMethod(EnableSession = true)]
        public void UpdDict(int id, Contacts contact)
        {
            contact.id = id;
            if (db.contacts.Count(x => x.id == id) > 0)
            {
                db.Entry(contact).State = EntityState.Modified;
                db.SaveChanges();
            }
        }

        [WebMethod(EnableSession = true)]
        public void DelDict(int id)
        {
            db.contacts.Remove(db.contacts.Find(id));
            db.SaveChanges();
        }
    }
}
