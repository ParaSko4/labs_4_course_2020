using System;
using System.Web.Mvc;
using pi_lab05b.Filters;

namespace pi_lab05b.Controllers
{
    public class AResearchController : Controller
    {
        [Action]
        public string AA()
        {
            return "<br/>AA<br/>";
        }

        [Result]
        public string AR()
        {
            return "<br/>AR<br/>";
        }

        [Exeption]
        public string AE()
        {
            throw new Exception("err");
        }
    }
}