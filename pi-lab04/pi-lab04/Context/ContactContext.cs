using pi_lab04.Models;
using System.Data.Entity;

namespace pi_lab04.Context
{
    public class ContactContext : DbContext
    {
        public DbSet<Contacts> contacts { get; set; }

        public ContactContext() : base("DefaultConnection")
        {

        }
    }
}