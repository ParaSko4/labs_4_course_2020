using pi_lab07a.Models;
using System.Data.Entity;

namespace pi_lab07a.Context
{
    [DbConfigurationType(typeof(MySql.Data.Entity.MySqlEFConfiguration))]
    public class ContactContext : DbContext
    {
        public DbSet<Contacts> contacts { get; set; }

        public ContactContext() : base("DefaultConnection")
        {

        }
    }
}