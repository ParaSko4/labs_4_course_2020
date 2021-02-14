using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using pi_lab05b.Models;
using System.Text.Json;
using System.IO;

namespace pi_lab05b.Controllers
{
    public class CHResearchController : Controller
    {
        [HttpGet]
        [OutputCache(Duration = 5)]
        public string AD()
        {
            return DateTime.Now.ToString();
        }

        [HttpPost]
        [OutputCache(Duration = 7)]
        public string AP()
        {
            string date = DateTime.Now.ToString();
            if (HttpContext.Request.InputStream.Length != 0 )
            {
                var obj = JsonSerializer.Deserialize<ReciveData>(new StreamReader(HttpContext.Request.InputStream, HttpContext.Request.ContentEncoding).ReadToEnd());
                return date + " \n" + obj.x + " " + obj.y;
            }
            return date;
        }
    }
}