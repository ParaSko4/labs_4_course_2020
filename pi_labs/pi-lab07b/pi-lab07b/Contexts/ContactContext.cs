using pi_lab07b.Models;
using System.Data.Entity;

namespace pi_lab07b.Contexts
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