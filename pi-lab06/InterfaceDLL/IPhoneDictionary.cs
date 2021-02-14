using System.Collections.Generic;

namespace InterfaceDLL
{
    public interface IPhoneDictionary
    {
        List<Contacts> contacts { get; }

        void Add(Contacts contact);
        void Update(Contacts contact);
        void Delete(int id);

        Contacts Find(int id);

        void SaveChanges();
        void LoadData();
    }
}
