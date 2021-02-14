using pi_lab3.Models;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web.Mvc;
using System.Text.Json;
using System.Text;
using System.Diagnostics;

namespace pi_lab3.Controllers
{
    public class DictController : Controller
    {
        private List<ContactModel> contacts;

        private int maxId = 0;

        private const string path = @"D:\Apps\Shatrauka\3\user.json";
        private const string homeUrl = "http://172.16.193.234:10222/3/Dict/Index";

        private void ReadJson()
        {
            string json = System.IO.File.ReadAllText(path, Encoding.Default);

            if (json.Length > 5)
            {
                contacts = JsonSerializer.Deserialize<List<ContactModel>>(json);
                maxId = contacts.Max(x => x.Id);
            }
            else
            {
                contacts = new List<ContactModel>();
            }
        }

        private void SaveJson()
        {
            System.IO.File.WriteAllText(path, JsonSerializer.Serialize(contacts));
        }

        public ActionResult Index()
        {
            ReadJson();
            return View(contacts);
        }

        public ActionResult Add()
        {
            return View();
        }

        public ActionResult AddSave(string name, string number)
        {
            ReadJson();
            contacts.Add(new ContactModel(++maxId, name, number));
            SaveJson();

            return Redirect(homeUrl);
        }

        public ActionResult Update(int id)
        {
            ReadJson();
            return View(contacts.Find(x => x.Id == id));
        }

        public ActionResult UpdateSave(int id, string name, string number)
        {
            ReadJson();

            var contact = contacts.Find(x => x.Id == id);
            contact.Name = name;
            contact.Number = number;

            SaveJson();

            return Redirect(homeUrl);
        }

        public ActionResult Delete(int id)
        {
            ViewBag.id = id;

            return View();
        }

        public ActionResult DeleteSave(int id)
        {
            ReadJson();
            contacts.Remove(contacts.Find(x => x.Id == id));
            SaveJson();

            return Redirect(homeUrl);
        }
    }
}