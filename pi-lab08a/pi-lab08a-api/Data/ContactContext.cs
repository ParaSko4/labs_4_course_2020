using Microsoft.EntityFrameworkCore;
using pi_lab08a_api.Models;

namespace pi_lab08a_api.Data
{
    public class ContactContext : DbContext
    {
        public DbSet<ContactModel> contacts { get; set; }

        public ContactContext()
        {
            Database.EnsureCreated();
        }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseMySql("server=localhost;UserId=root;Password=1244;database=dblab7;");
        }
    }
}
