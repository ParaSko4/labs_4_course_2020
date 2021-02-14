using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace pi_lab3.Models
{
    public class ContactModel
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Number { get; set; }

        public ContactModel()
        {

        }

        public ContactModel(int id, string nam, string num)
        {
            Id = id;
            Name = nam;
            Number = num;
        }
    }
}