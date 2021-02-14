using pi_lab07c.Models;
using System.Collections.Generic;
using System.ServiceModel;

namespace pi_lab07c
{
    [ServiceContract]
    public interface IContactService
    {
        [OperationContract]
        List<Contacts> GetDict();

        [OperationContract]
        void AddDict(Contacts contact);

        [OperationContract]
        void UpdDict(int id, Contacts contact);

        [OperationContract]
        void DelDict(int id);
    }
}