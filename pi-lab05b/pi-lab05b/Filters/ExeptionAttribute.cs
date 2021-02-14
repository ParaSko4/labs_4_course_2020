using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace pi_lab05b.Filters
{
    public class ExeptionAttribute : FilterAttribute, IExceptionFilter
    {
        public void OnException(ExceptionContext filterContext)
        {
            filterContext.HttpContext.Response.Write("Exeption filter executed");
            filterContext.ExceptionHandled = true;
        }
    }
}