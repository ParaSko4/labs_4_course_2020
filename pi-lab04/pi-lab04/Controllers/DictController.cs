using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Text;
using pi_lab04.Context;
using pi_lab04.Models;
using System.Data.Entity;

namespace pi_lab04.Controllers
{
    public class DictController : Controller
    {
        private const string homeUrl = "http://172.16.193.234:10222/4/Dict/Index";

        ContactContext db;

        public DictController()
        {
            db = new ContactContext();
            db.contacts.Load();
        }

        public ActionResult Index()
        {
            return View(db.contacts.ToList());
        }

        public ActionResult Add()
        {
            return View();
        }

        public ActionResult AddSave(string name, string number)
        {
            db.contacts.Add(new Contacts(name, number));
            db.SaveChanges();

            return Redirect(homeUrl);
        }

        public ActionResult Update(int id)
        {
            return View(db.contacts.Find(id));
        }

        public ActionResult UpdateSave(int id, string name, string number)
        {
            var upContact = db.contacts.Find(id);

            upContact.name = name;
            upContact.number = number;

            db.SaveChanges();

            return Redirect(homeUrl);
        }

        public ActionResult Delete(int id)
        {
            ViewBag.id = id;

            return View();
        }

        public ActionResult DeleteSave(int id)
        {
            db.contacts.Remove(db.contacts.Find(id));
            db.SaveChanges();

            return Redirect(homeUrl);
        }
    }
}