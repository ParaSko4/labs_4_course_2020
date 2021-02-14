using pi_lab07c.Models;
using System.Data.Entity;

namespace pi_lab07c.Contexts
{
    //[DbConfigurationType(typeof(MySql.Data.Entity.MySqlEFConfiguration))]
    public class ContactContext : DbContext
    {
        public DbSet<Contacts> contacts { get; set; }

        public ContactContext() : base("sqliteConnection")
        {

        }
    }
}