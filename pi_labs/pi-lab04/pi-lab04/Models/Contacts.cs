using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace pi_lab04.Models
{
    public class Contacts
    {
        [Key]
        public int id { get; set; }
        public string name { get; set; }
        public string number { get; set; }

        public Contacts()
        {

        }

        public Contacts(string nam, string num)
        {
            name = nam;
            number = num;
        }

        public Contacts(int id, string nam, string num)
        {
            this.id = id;
            name = nam;
            number = num;
        }
    }
}