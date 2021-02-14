using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace pi_lab05a.Controllers
{
    public class CResearchController : Controller
    {
        public string C01()
        {
            string result;

            result = "Method : " + HttpContext.Request.HttpMethod + ";\n";
            result += "URI : " + HttpContext.Request.Url + ";\n";
            result += "Headers : " + HttpContext.Request.Headers + ";\n";

            string postBody = new StreamReader(HttpContext.Request.InputStream, HttpContext.Request.ContentEncoding).ReadToEnd();
            if (postBody.Length != 0)
            {

                result += "POST body : " + postBody + ";\n";
            }

            if (HttpContext.Request.QueryString.AllKeys.Length != 0)
            {
                result += "QueryParams : ";
                foreach (var item in HttpContext.Request.QueryString.AllKeys)
                {
                    result += item + " ";
                }
                result += ";\n";
            }

            return result;
        }

        public string C02()
        {
            string result;

            result = "Status Code : " + HttpContext.Response.StatusCode + ";\n";
            result += "Headers : " + HttpContext.Request.Headers + ";\n";

            string postBody = new StreamReader(HttpContext.Request.InputStream, HttpContext.Request.ContentEncoding).ReadToEnd();
            if (postBody.Length != 0)
            {

                result += "POST body : " + postBody + ";\n";
            }

            return result;
        }
    }
}