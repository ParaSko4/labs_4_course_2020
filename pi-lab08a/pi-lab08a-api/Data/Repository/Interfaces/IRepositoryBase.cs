using System;
using System.Collections.Generic;

namespace pi_lab08a_api.Data.Repository.Interfaces
{
    public interface IRepositoryBase<T> : IDisposable where T : class
    {
        IEnumerable<T> GetAll();
        T Get(int id);
        void Add(T item);
        void Update(int id, T item);
        void Delete(int id);
    }
}
