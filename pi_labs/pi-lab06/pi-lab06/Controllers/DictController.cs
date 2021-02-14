using System.Web.Mvc;
using InterfaceDLL;
using JsonDataMethods;
using Ninject;
using pi_lab06.App_Start;
using SQLiteDataMethods;

namespace pi_lab06.Controllers
{
    public class DictController : Controller
    {
        string homeUrl = @"http://localhost:60065/Dict/Index";

        IPhoneDictionary repo;

        public DictController(IPhoneDictionary r)
        {
            repo = r;

            repo.LoadData();
        }

        public ActionResult Index()
        {
            return View(repo.contacts);
        }

        public ActionResult Add()
        {
            return View();
        }

        public ActionResult AddSave(string name, string number)
        {
            repo.Add(new Contacts(name, number));
            repo.SaveChanges();

            return Redirect(homeUrl);
        }

        public ActionResult Update(int id)
        {
            return View(repo.Find(id));
        }

        public ActionResult UpdateSave(int id, string name, string number)
        {
            var upContact = repo.Find(id);

            upContact.name = name;
            upContact.number = number;

            repo.SaveChanges();

            return Redirect(homeUrl);
        }

        public ActionResult Delete(int id)
        {
            ViewBag.id = id;

            return View();
        }

        public ActionResult DeleteSave(int id)
        {
            repo.Delete(id);
            repo.SaveChanges();

            return Redirect(homeUrl);
        }
    }
}