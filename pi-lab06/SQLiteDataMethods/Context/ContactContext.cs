using InterfaceDLL;
using System.Data.Entity;

namespace SQLiteDataMethods.Context
{
    class ContactContext : DbContext
    {
        public DbSet<Contacts> contacts { get; set; }

        public ContactContext() : base("DefaultConnection")
        {
            var instance = System.Data.Entity.SqlServer.SqlProviderServices.Instance;
        }
    }
}
