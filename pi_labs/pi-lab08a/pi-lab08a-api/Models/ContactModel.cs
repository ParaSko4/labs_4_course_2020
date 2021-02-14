namespace pi_lab08a_api.Models
{
    public class ContactModel
    {
        public int id { get; set; }
        public string name { get; set; }
        public string number { get; set; }

        public ContactModel() { }
        public ContactModel(int id, string name, string number)
        {
            this.id = id;
            this.name = name;
            this.number = number;
        }
        public ContactModel(string name, string number)
        {
            this.name = name;
            this.number = number;
        }
    }
}
